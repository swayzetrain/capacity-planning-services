package tech.swayzetrain.capacity.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.swayzetrain.capacity.api.service.teammember.capacity.TeamMemberCapacityReader;
import tech.swayzetrain.capacity.api.service.teammember.capacity.TeamMemberCapacityWriter;
import tech.swayzetrain.capacity.common.model.TeamMemberCapacity;
import tech.swayzetrain.capacity.common.utility.SharedUtility;

@RestController
@RequestMapping("/v1/teams/{teamId}/members/{teamMemberId}/capacity")
public class TeamMemberCapacityController {
	
	@Autowired
	private TeamMemberCapacityReader teamMemberCapacityReader;
	
	@Autowired
	private TeamMemberCapacityWriter teamMemberCapacityWriter;
	
	private SharedUtility sharedUtility = new SharedUtility();
	
	@GetMapping("/{teamMemberCapacityId}")
	public ResponseEntity<TeamMemberCapacity> getTeamMemberCapacityById(@PathVariable("teamId") String teamId, @PathVariable("teamMemberId") String teamMemberId, @PathVariable("teamMemberCapacityId") String teamMemberCapacityId) {
		return teamMemberCapacityReader.retrieveTeamMemberCapacityByTeamMemberAndKey(sharedUtility.uuidFromString(teamId), sharedUtility.uuidFromString(teamMemberId), sharedUtility.uuidFromString(teamMemberCapacityId));
	}
	
	@GetMapping
	public ResponseEntity<List<TeamMemberCapacity>> getTeamMemberCapacityByTeamMember(@PathVariable("teamId") String teamId, @PathVariable("teamMemberId") String teamMemberId) {
		return teamMemberCapacityReader.retrieveTeamMemberCapacityByTeamMember(sharedUtility.uuidFromString(teamId), sharedUtility.uuidFromString(teamMemberId));
	}
	
	@PostMapping
	public ResponseEntity<TeamMemberCapacity> createTeamMemberCapacity(@PathVariable("teamId") String teamId, @PathVariable("teamMemberId") String teamMemberId, @Validated(TeamMemberCapacity.New.class) @RequestBody TeamMemberCapacity teamMemberCapacity) {
		return teamMemberCapacityWriter.createTeamMemberCapacity(sharedUtility.uuidFromString(teamId), sharedUtility.uuidFromString(teamMemberId), teamMemberCapacity);
	}

}
