package tech.swayzetrain.capacity.api.service.teammember.capacity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.api.service.teammember.TeamMemberReader;
import tech.swayzetrain.capacity.common.model.TeamMemberCapacity;
import tech.swayzetrain.capacity.common.repository.TeamMemberCapacityRepository;

@Service
public class TeamMemberCapacityWriter {
	
	@Autowired
	private TeamMemberCapacityRepository teamMemberCapacityRepository;
	
	@Autowired
	private TeamMemberReader teamMemberReader;
	
	public ResponseEntity<TeamMemberCapacity> createTeamMemberCapacity(UUID teamId, UUID teamMemberId, TeamMemberCapacity teamMemberCapacity) {
		teamMemberCapacity.setTeamMember(teamMemberReader.retrieveTeamMemberByTeamAndKey(teamId, teamMemberId));
		
		teamMemberCapacity.setModifiedDate(LocalDateTime.now());
		
		TeamMemberCapacity presistedTeamMemberCapacity = teamMemberCapacityRepository.save(teamMemberCapacity);
		
		return new ResponseEntity<>(presistedTeamMemberCapacity, HttpStatus.OK);
	}

}
