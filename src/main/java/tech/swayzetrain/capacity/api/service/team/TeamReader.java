package tech.swayzetrain.capacity.api.service.team;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.common.exception.ObjectNotFoundException;
import tech.swayzetrain.capacity.common.model.Team;
import tech.swayzetrain.capacity.common.repository.TeamRepository;

@Service
public class TeamReader {
	
	@Autowired
	private TeamRepository teamRepository;
	
	public Team retrieveTeamByKey(UUID teamId) {
		Team team = teamRepository.findByTeamId(teamId);
		
		if(null == team) {
			throw new ObjectNotFoundException(String.format("No Team exists by teamId, %s", teamId.toString()));
		}
		
		return team;
	}
	
	public ResponseEntity<Team> retrieveTeamResponseByKey(UUID teamId) {
		Team team = retrieveTeamByKey(teamId);
		
		return new ResponseEntity<>(team, HttpStatus.OK);
	}
	
	public ResponseEntity<List<Team>> retrieveAllTeams() {
		List<Team> teams = teamRepository.findAll();
		
		return new ResponseEntity<>(teams, HttpStatus.OK);
	}
	
}
