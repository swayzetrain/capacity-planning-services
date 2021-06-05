package tech.swayzetrain.capacity.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.swayzetrain.capacity.api.service.team.member.TeamMemberReader;
import tech.swayzetrain.capacity.api.service.team.member.TeamMemberWriter;
import tech.swayzetrain.capacity.common.model.TeamMember;
import tech.swayzetrain.capacity.common.utility.SharedUtility;

@RestController
@RequestMapping("/v1/teams/{teamId}/members")
public class TeamMemberController {
	
	@Autowired
	private TeamMemberReader teamMemberReader;
	
	@Autowired
	private TeamMemberWriter teamMemberWriter;
	
	private SharedUtility sharedUtility = new SharedUtility();
	
	@GetMapping("/{teamMemberId}")
	public ResponseEntity<TeamMember> getTeamMemberById(@PathVariable("teamId") String teamId, @PathVariable("teamMemberId") String teamMemberId) {
		return teamMemberReader.retrieveTeamMemberResponseByTeamAndKey(sharedUtility.uuidFromString(teamId), sharedUtility.uuidFromString(teamMemberId));
	}
	
	@GetMapping
	public ResponseEntity<List<TeamMember>> getTeamMemberByTeam(@PathVariable("teamId") String teamId) {
		return teamMemberReader.retrieveTeamMembersResponseByTeam(sharedUtility.uuidFromString(teamId));
	}
	
	@PostMapping
	public ResponseEntity<TeamMember> postTeamMember(@PathVariable("teamId") String teamId, @Validated(TeamMember.New.class) @RequestBody TeamMember teamMember) {
		return teamMemberWriter.createTeamMember(sharedUtility.uuidFromString(teamId), teamMember);
	}
	
	@PatchMapping("/{teamMemberId}")
	public ResponseEntity<TeamMember> patchTeamMember(@PathVariable("teamId") String teamId, @PathVariable("teamMemberId") String teamMemberId, @Validated(TeamMember.Update.class) @RequestBody TeamMember teamMember) {
		return teamMemberWriter.updateExistingTeamMember(sharedUtility.uuidFromString(teamId), sharedUtility.uuidFromString(teamMemberId), teamMember);
	}

}
