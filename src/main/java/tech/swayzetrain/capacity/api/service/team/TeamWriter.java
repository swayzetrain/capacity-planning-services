package tech.swayzetrain.capacity.api.service.team;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.common.model.Team;
import tech.swayzetrain.capacity.common.repository.TeamRepository;

@Service
public class TeamWriter {
	
	@Autowired
	private TeamRepository teamRepository;
	
	public ResponseEntity<Team> createTeam(Team team) {
		team.setCreatedDate(LocalDateTime.now());
		team.setModifiedDate(LocalDateTime.now());
		
		Team persistedTeam = teamRepository.save(team);
		
		return new ResponseEntity<>(persistedTeam, HttpStatus.OK);
	}

}
