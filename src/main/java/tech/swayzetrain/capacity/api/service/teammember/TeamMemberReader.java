package tech.swayzetrain.capacity.api.service.teammember;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.api.service.team.TeamReader;
import tech.swayzetrain.capacity.common.exception.ObjectNotFoundException;
import tech.swayzetrain.capacity.common.model.Team;
import tech.swayzetrain.capacity.common.model.TeamMember;
import tech.swayzetrain.capacity.common.repository.TeamMemberRepository;

@Service
public class TeamMemberReader {

	@Autowired
	private TeamMemberRepository teamMemberRepository;

	@Autowired
	private TeamReader teamReader;
	
	public TeamMember retrieveTeamMemberByTeamAndKey(UUID teamId, UUID teamMemberId) {

		Team team = teamReader.retrieveTeamByKey(teamId);

		TeamMember teamMember = teamMemberRepository.findByTeamMemberIdAndTeam(teamMemberId, team);

		if (null == teamMember) {
			throw new ObjectNotFoundException(String.format("No Team Member exists by teamMemberId, %s, on Team, %s",
					teamMemberId.toString(), teamId.toString()));
		}

		return teamMember;
	}

	public ResponseEntity<TeamMember> retrieveTeamMemberResponseByTeamAndKey(UUID teamId, UUID teamMemberId) {

		TeamMember teamMember = retrieveTeamMemberByTeamAndKey(teamId, teamMemberId);

		return new ResponseEntity<>(teamMember, HttpStatus.OK);
	}

	public ResponseEntity<List<TeamMember>> retrieveTeamMembersResponseByTeam(UUID teamId) {
		Team team = teamReader.retrieveTeamByKey(teamId);

		List<TeamMember> teamMembers = teamMemberRepository.findByTeam(team);

		return new ResponseEntity<>(teamMembers, HttpStatus.OK);
	}

	

}
