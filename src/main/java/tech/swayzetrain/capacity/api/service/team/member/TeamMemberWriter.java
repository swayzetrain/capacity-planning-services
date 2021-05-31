package tech.swayzetrain.capacity.api.service.team.member;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.api.service.team.TeamReader;
import tech.swayzetrain.capacity.common.model.TeamMember;
import tech.swayzetrain.capacity.common.repository.TeamMemberRepository;

@Service
public class TeamMemberWriter {
	
	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	@Autowired
	private TeamReader teamReader;
	
	public ResponseEntity<TeamMember> createTeamMember(UUID teamId, TeamMember teamMember) {
		teamMember.setTeam(teamReader.retrieveTeamByKey(teamId));
		
		teamMember.setCreatedDate(LocalDateTime.now());
		teamMember.setModifiedDate(LocalDateTime.now());
		
		TeamMember persistedTeamMember = teamMemberRepository.save(teamMember);
		
		return new ResponseEntity<>(persistedTeamMember, HttpStatus.OK);
	}

}
