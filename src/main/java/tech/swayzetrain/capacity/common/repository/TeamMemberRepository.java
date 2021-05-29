package tech.swayzetrain.capacity.common.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.swayzetrain.capacity.common.model.Team;
import tech.swayzetrain.capacity.common.model.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, UUID> {
	
	public TeamMember findByTeamMemberIdAndTeam(UUID teamMemberId, Team team);
	
	public List<TeamMember> findByTeam(Team team);

}
