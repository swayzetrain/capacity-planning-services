package tech.swayzetrain.capacity.api.service.team.member;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.api.service.team.TeamReader;
import tech.swayzetrain.capacity.common.model.Team;
import tech.swayzetrain.capacity.common.model.TeamMember;
import tech.swayzetrain.capacity.common.repository.TeamMemberRepository;
import tech.swayzetrain.capacity.common.utility.SharedUtility;

@Service
public class TeamMemberWriter {
	
	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	@Autowired
	private TeamReader teamReader;
	
	private SharedUtility sharedUtility = new SharedUtility();
	
	public ResponseEntity<TeamMember> createTeamMember(UUID teamId, TeamMember teamMember) {
		teamMember.setTeam(teamReader.retrieveTeamByKey(teamId));
		
		teamMember.setCreatedDate(LocalDateTime.now());
		teamMember.setModifiedDate(LocalDateTime.now());
		
		TeamMember persistedTeamMember = teamMemberRepository.save(teamMember);
		
		return new ResponseEntity<>(persistedTeamMember, HttpStatus.OK);
	}
	
	public ResponseEntity<TeamMember> updateExistingTeamMember(UUID teamId, UUID teamMemberId, TeamMember updatedTeamMember) {
		Team team = teamReader.retrieveTeamByKey(teamId);
		
		TeamMember existingTeamMember = teamMemberRepository.findByTeamMemberIdAndTeam(teamMemberId, team);
		
		sharedUtility.copyNonNullProperties(updatedTeamMember, existingTeamMember);
		
		existingTeamMember.setModifiedDate(LocalDateTime.now());
		
		teamMemberRepository.save(existingTeamMember);
		
		return new ResponseEntity<>(existingTeamMember, HttpStatus.OK);
	}

}
