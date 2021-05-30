package tech.swayzetrain.capacity.common.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.swayzetrain.capacity.common.model.TeamMember;
import tech.swayzetrain.capacity.common.model.TeamMemberCapacity;

public interface TeamMemberCapacityRepository extends JpaRepository<TeamMemberCapacity, UUID> {
	
	public TeamMemberCapacity findByTeamMemberAndTeamMemberCapacityIdOrderByDateAsc(TeamMember teamMember, UUID teamMemberCapacityId);
	
	public List<TeamMemberCapacity> findByTeamMemberOrderByDateAsc(TeamMember teamMember);
	
	public List<TeamMemberCapacity> findByTeamMemberAndDateBetweenOrderByDateAsc(TeamMember teamMember, LocalDate startDate, LocalDate EndDate);

}
