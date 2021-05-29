package tech.swayzetrain.capacity.common.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.swayzetrain.capacity.common.model.Team;

public interface TeamRepository extends JpaRepository<Team, UUID> {
	
	public Team findByTeamId(UUID teamId);

}
