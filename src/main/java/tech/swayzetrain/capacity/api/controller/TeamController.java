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

import tech.swayzetrain.capacity.api.service.team.TeamCapacityService;
import tech.swayzetrain.capacity.api.service.team.TeamReader;
import tech.swayzetrain.capacity.api.service.team.TeamWriter;
import tech.swayzetrain.capacity.common.model.RoleCapacity;
import tech.swayzetrain.capacity.common.model.Team;
import tech.swayzetrain.capacity.common.utility.SharedUtility;

@RestController
@RequestMapping("/v1/teams")
public class TeamController {
	
	@Autowired
	private TeamReader teamReader;
	
	@Autowired
	private TeamWriter teamWriter;
	
	@Autowired
	private TeamCapacityService teamCapacityService; 
	
	private SharedUtility sharedUtility = new SharedUtility();
	
	@GetMapping
	public ResponseEntity<List<Team>> getTeams() {
		return teamReader.retrieveAllTeams();
	}
	
	@GetMapping("/{teamId}")
	public ResponseEntity<Team> getTeamByKey(@PathVariable("teamId") String teamId) {
		return teamReader.retrieveTeamResponseByKey(sharedUtility.uuidFromString(teamId));
	}
	
	@GetMapping("/{teamId}/capacity")
	public ResponseEntity<List<RoleCapacity>> getTeamRoleCapcityByKey(@PathVariable("teamId") String teamId) {
		return teamCapacityService.getTeamRoleCapacity(sharedUtility.uuidFromString(teamId));
	}
	
	@GetMapping("/{teamId}/capacity/summary")
	public ResponseEntity<List<RoleCapacity>> getTeamRoleCapcitySummaryByKey(@PathVariable("teamId") String teamId) {
		return teamCapacityService.getTeamRoleCapacitySummary(sharedUtility.uuidFromString(teamId));
	}
	
	@PostMapping
	public ResponseEntity<Team> createTeam(@Validated(Team.New.class) @RequestBody Team team) {
		return teamWriter.createTeam(team);
	}

}
