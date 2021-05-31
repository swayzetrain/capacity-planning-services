package tech.swayzetrain.capacity.common.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.swayzetrain.capacity.common.model.Project;
import tech.swayzetrain.capacity.common.model.ProjectEstimate;

public interface ProjectEstimateRepository extends JpaRepository<ProjectEstimate, UUID> {
	
	public ProjectEstimate findByProjectEstimateIdAndProject(UUID projectEstimateId, Project project);
	
	public List<ProjectEstimate> findByProject(Project project);

}
