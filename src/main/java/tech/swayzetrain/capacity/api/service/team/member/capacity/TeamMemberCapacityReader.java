package tech.swayzetrain.capacity.api.service.team.member.capacity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.api.service.team.member.TeamMemberReader;
import tech.swayzetrain.capacity.common.exception.InvalidRequestParameterException;
import tech.swayzetrain.capacity.common.exception.ObjectNotFoundException;
import tech.swayzetrain.capacity.common.model.TeamMember;
import tech.swayzetrain.capacity.common.model.TeamMemberCapacity;
import tech.swayzetrain.capacity.common.repository.TeamMemberCapacityRepository;

@Service
public class TeamMemberCapacityReader {
	
	@Autowired
	private TeamMemberCapacityRepository teamMemberCapacityRepository;
	
	@Autowired
	private TeamMemberReader teamMemberReader;
	
	public ResponseEntity<TeamMemberCapacity> retrieveTeamMemberCapacityByTeamMemberAndKey(UUID teamId, UUID teamMemberId, UUID teamMemberCapacityId) {
		TeamMember teamMember = teamMemberReader.retrieveTeamMemberByTeamAndKey(teamId, teamMemberId);
		
		TeamMemberCapacity teamMemberCapacity = teamMemberCapacityRepository.findByTeamMemberAndTeamMemberCapacityIdOrderByDateAsc(teamMember, teamMemberCapacityId);
		
		if(null == teamMemberCapacity) {
			throw new ObjectNotFoundException(String.format("No Capacity exists by teamMemberCapacityId, %s, for Team Member, %s, on Team, %s",
					teamMemberCapacityId.toString(), teamMemberId.toString(), teamId.toString()));
		}
		
		return new ResponseEntity<>(teamMemberCapacity, HttpStatus.OK);
	}
	
	public ResponseEntity<List<TeamMemberCapacity>> retrieveTeamMemberCapacityByTeamMember(UUID teamId, UUID teamMemberId, LocalDate startDate, LocalDate endDate) {
		TeamMember teamMember = teamMemberReader.retrieveTeamMemberByTeamAndKey(teamId, teamMemberId);
		
		List<TeamMemberCapacity> teamMemberCapacity = null;
		
		if(null == startDate && null == endDate) {
			teamMemberCapacity = teamMemberCapacityRepository.findByTeamMemberOrderByDateAsc(teamMember);
		} else if(null != startDate && null != endDate) {
			teamMemberCapacity = teamMemberCapacityRepository.findByTeamMemberAndDateBetweenOrderByDateAsc(teamMember, startDate, endDate);
		} else {
			throw new InvalidRequestParameterException("If a startDate or endDate request parameter is included in the request, then both must be included in the request.");
		}
		
		return new ResponseEntity<>(teamMemberCapacity, HttpStatus.OK);
	}

}
