package tech.swayzetrain.capacity.api.service.team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.common.enums.Role;
import tech.swayzetrain.capacity.common.exception.CapacityProcessingException;
import tech.swayzetrain.capacity.common.model.DailyCapacity;
import tech.swayzetrain.capacity.common.model.MonthlyCapacity;
import tech.swayzetrain.capacity.common.model.RoleCapacity;
import tech.swayzetrain.capacity.common.model.Team;
import tech.swayzetrain.capacity.common.model.TeamMemberCapacity;

@Service
public class TeamCapacityService {

	@Autowired
	private TeamReader teamReader;

	public ResponseEntity<List<RoleCapacity>> getTeamRoleCapacity(UUID teamId, LocalDate startDate, LocalDate endDate) {
		List<RoleCapacity> roleCapacityList = Collections.synchronizedList(new ArrayList<>());

		for (Role role : Role.values()) {
			if(Role.TEAM.equals(role)) {
				continue;
			}
			
			RoleCapacity roleCapacity = new RoleCapacity();
			roleCapacity.setRole(role);
			roleCapacity.setDailyCapacity(Collections.synchronizedList(new ArrayList<DailyCapacity>()));

			roleCapacityList.add(roleCapacity);
		}

		Team team = teamReader.retrieveTeamByKey(teamId);

		team.getTeamMembers().stream().forEach(teamMember ->
			teamMember.getTeamMemberCapacity().stream()
			.filter(tmc -> null == startDate || tmc.getDate().compareTo(startDate) >= 0)
			.filter(tmc -> null == endDate || tmc.getDate().compareTo(endDate) <= 0)
			.forEach(teamMemberCapacity -> {
				List<DailyCapacity> dailyCapacityList = null;

				dailyCapacityList = getDailyCapacityByRole(roleCapacityList, teamMember.getRole());
				
				if(null == dailyCapacityList) {
					throw new CapacityProcessingException(String.format("Exception encountered when trying to build a capacity report for role, %s", teamMember.getRole().toString()));
				}
				
				addCapacityToDailyRoleCapacity(dailyCapacityList, teamMemberCapacity);
			}));

		return new ResponseEntity<>(roleCapacityList, HttpStatus.OK);
	}
	

	public ResponseEntity<List<RoleCapacity>> getTeamRoleCapacitySummary(UUID teamId, LocalDate startDate, LocalDate endDate) {
		List<RoleCapacity> roleCapacityList = Collections.synchronizedList(new ArrayList<>());
		
		for (Role role : Role.values()) {
			RoleCapacity roleCapacity = new RoleCapacity();
			roleCapacity.setRole(role);
			roleCapacity.setMonthlyCapacity(Collections.synchronizedList(new ArrayList<MonthlyCapacity>()));

			roleCapacityList.add(roleCapacity);
		}
		
		Team team = teamReader.retrieveTeamByKey(teamId);

		
		team.getTeamMembers().stream().forEach(teamMember ->
			teamMember.getTeamMemberCapacity().stream()
			.filter(tmc -> null == startDate || tmc.getDate().compareTo(startDate) >= 0)
			.filter(tmc -> null == endDate || tmc.getDate().compareTo(endDate) <= 0)
			.forEach(teamMemberCapacity -> {
				List<MonthlyCapacity> monthlyCapacityList = null;
				List<MonthlyCapacity> monthlyTeamCapacityList = null;
				
				monthlyCapacityList = getMonthlyCapacityByRole(roleCapacityList, teamMember.getRole());
				monthlyTeamCapacityList = getMonthlyCapacityByRole(roleCapacityList, Role.TEAM);
				
				if(null == monthlyCapacityList) {
					throw new CapacityProcessingException(String.format("Exception encountered when trying to build a capacity report for role, %s", teamMember.getRole().toString()));
				}
				
				if(null == monthlyTeamCapacityList) {
					throw new CapacityProcessingException(String.format("Exception encountered when trying to build a capacity report for role, %s", Role.TEAM.toString()));
				}


				addCapacityToMonthlyRoleCapacity(monthlyCapacityList, teamMemberCapacity);
				addCapacityToMonthlyRoleCapacity(monthlyTeamCapacityList, teamMemberCapacity);
			}));

		return new ResponseEntity<>(roleCapacityList, HttpStatus.OK);
	}
	
	public List<DailyCapacity> getDailyCapacityByRole(List<RoleCapacity> roleCapacityList, Role role) {
		List<DailyCapacity> dailyCapacityList = null;
		
		for (RoleCapacity roleCapacity : roleCapacityList) {
			if (roleCapacity.getRole().equals(role)) {
				dailyCapacityList = roleCapacity.getDailyCapacity();
			}
		}
		
		return dailyCapacityList;
	}
	
	public List<MonthlyCapacity> getMonthlyCapacityByRole(List<RoleCapacity> roleCapacityList, Role role) {
		List<MonthlyCapacity> monthlyCapacityList = null;
		
		for (RoleCapacity roleCapacity : roleCapacityList) {
			if (roleCapacity.getRole().equals(role)) {
				monthlyCapacityList = roleCapacity.getMonthlyCapacity();
			}
		}
		
		return monthlyCapacityList;
	}
	
	public void addCapacityToDailyRoleCapacity(List<DailyCapacity> dailyCapacityList, TeamMemberCapacity teamMemberCapacity) {
		Boolean dailyCapacityAdded = false;
		
		for (DailyCapacity capacity : dailyCapacityList) {
			if (capacity.getDate().isEqual(teamMemberCapacity.getDate())) {
				capacity.setHours(capacity.getHours().add(teamMemberCapacity.getHours()));
				dailyCapacityAdded = true;
			}
		}

		if (Boolean.FALSE.equals(dailyCapacityAdded)) {
			DailyCapacity dailyCapacity = new DailyCapacity();
			dailyCapacity.setHours(teamMemberCapacity.getHours());
			dailyCapacity.setDate(teamMemberCapacity.getDate());

			dailyCapacityList.add(dailyCapacity);
		}
	}
	
	public void addCapacityToMonthlyRoleCapacity(List<MonthlyCapacity> monthlyCapacityList, TeamMemberCapacity teamMemberCapacity) {
		Boolean monthlyRoleCapacityAdded = false;
		
		for (MonthlyCapacity capacity : monthlyCapacityList) {
			if (capacity.getDate().getMonth().equals(teamMemberCapacity.getDate().getMonth()) && capacity.getDate().getYear() == teamMemberCapacity.getDate().getYear()) {
				capacity.setHours(capacity.getHours().add(teamMemberCapacity.getHours()));
				monthlyRoleCapacityAdded = true;
			}
		}

		if (Boolean.FALSE.equals(monthlyRoleCapacityAdded)) {
			MonthlyCapacity monthlyCapacity = new MonthlyCapacity();
			monthlyCapacity.setHours(teamMemberCapacity.getHours());
			monthlyCapacity.setDate(teamMemberCapacity.getDate().minusDays(teamMemberCapacity.getDate().getDayOfMonth()-1));

			monthlyCapacityList.add(monthlyCapacity);
		}
	}

}
