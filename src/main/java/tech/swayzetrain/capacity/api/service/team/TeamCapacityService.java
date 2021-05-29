package tech.swayzetrain.capacity.api.service.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.common.enums.Role;
import tech.swayzetrain.capacity.common.model.DailyCapacity;
import tech.swayzetrain.capacity.common.model.MonthlyCapacity;
import tech.swayzetrain.capacity.common.model.RoleCapacity;
import tech.swayzetrain.capacity.common.model.Team;

@Service
public class TeamCapacityService {

	@Autowired
	private TeamReader teamReader;

	public ResponseEntity<List<RoleCapacity>> getTeamRoleCapacity(UUID teamId) {
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

		team.getTeamMembers().stream().forEach(teamMember -> {
			teamMember.getTeamMemberCapacity().stream().forEach(teamMemberCapacity -> {
				List<DailyCapacity> dailyCapacityList = null;
				Boolean dailyCapacityAdded = false;

				for (RoleCapacity roleCapacity : roleCapacityList) {
					if (roleCapacity.getRole().equals(teamMember.getRole())) {
						dailyCapacityList = roleCapacity.getDailyCapacity();
					}
				}

				for (DailyCapacity capacity : dailyCapacityList) {
					if (capacity.getDate().isEqual(teamMemberCapacity.getDate())) {
						capacity.setHours(capacity.getHours().add(teamMemberCapacity.getHours()));
						dailyCapacityAdded = true;
					}
				}

				if (!dailyCapacityAdded) {
					DailyCapacity dailyCapacity = new DailyCapacity();
					dailyCapacity.setHours(teamMemberCapacity.getHours());
					dailyCapacity.setDate(teamMemberCapacity.getDate());

					dailyCapacityList.add(dailyCapacity);
				}
			});
		});

		return new ResponseEntity<>(roleCapacityList, HttpStatus.OK);
	}

	public ResponseEntity<List<RoleCapacity>> getTeamRoleCapacitySummary(UUID teamId) {
		List<RoleCapacity> roleCapacityList = Collections.synchronizedList(new ArrayList<>());
		
		for (Role role : Role.values()) {
			RoleCapacity roleCapacity = new RoleCapacity();
			roleCapacity.setRole(role);
			roleCapacity.setMonthlyCapacity(Collections.synchronizedList(new ArrayList<MonthlyCapacity>()));

			roleCapacityList.add(roleCapacity);
		}
		
		Team team = teamReader.retrieveTeamByKey(teamId);

		
		team.getTeamMembers().stream().forEach(teamMember -> {
			teamMember.getTeamMemberCapacity().stream().forEach(teamMemberCapacity -> {
				List<MonthlyCapacity> monthlyCapacityList = null;
				List<MonthlyCapacity> monthlyTeamCapacityList = null;
				Boolean monthlyRoleCapacityAdded = false;
				Boolean monthlyTeamCapacityAdded = false;

				for (RoleCapacity roleCapacity : roleCapacityList) {
					if (roleCapacity.getRole().equals(teamMember.getRole())) {
						monthlyCapacityList = roleCapacity.getMonthlyCapacity();
					}
					
					if(roleCapacity.getRole().equals(Role.TEAM)) {
						monthlyTeamCapacityList = roleCapacity.getMonthlyCapacity();
					}
				}

				for (MonthlyCapacity capacity : monthlyCapacityList) {
					if (capacity.getDate().getMonth().equals(teamMemberCapacity.getDate().getMonth()) && capacity.getDate().getYear() == teamMemberCapacity.getDate().getYear()) {
						capacity.setHours(capacity.getHours().add(teamMemberCapacity.getHours()));
						monthlyRoleCapacityAdded = true;
					}
				}

				if (!monthlyRoleCapacityAdded) {
					MonthlyCapacity monthlyCapacity = new MonthlyCapacity();
					monthlyCapacity.setHours(teamMemberCapacity.getHours());
					monthlyCapacity.setDate(teamMemberCapacity.getDate().minusDays(teamMemberCapacity.getDate().getDayOfMonth()-1));

					monthlyCapacityList.add(monthlyCapacity);
				}
				
				for(MonthlyCapacity capacity : monthlyTeamCapacityList) {
					if (capacity.getDate().getMonth().equals(teamMemberCapacity.getDate().getMonth()) && capacity.getDate().getYear() == teamMemberCapacity.getDate().getYear()) {
						capacity.setHours(capacity.getHours().add(teamMemberCapacity.getHours()));
						monthlyTeamCapacityAdded = true;
					}
				}
				
				if(!monthlyTeamCapacityAdded) {
					MonthlyCapacity monthlyCapacity = new MonthlyCapacity();
					monthlyCapacity.setHours(teamMemberCapacity.getHours());
					monthlyCapacity.setDate(teamMemberCapacity.getDate().minusDays(teamMemberCapacity.getDate().getDayOfMonth()-1));
					
					monthlyTeamCapacityList.add(monthlyCapacity);
				}
			});
		});

		return new ResponseEntity<>(roleCapacityList, HttpStatus.OK);
	}

}
