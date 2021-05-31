package tech.swayzetrain.capacity.common.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.swayzetrain.capacity.common.model.Project;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

	public Project findByProjectId(UUID projectId);
	
	public List<Project> findAll();
	
}
