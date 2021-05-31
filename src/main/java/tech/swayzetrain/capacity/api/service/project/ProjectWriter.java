package tech.swayzetrain.capacity.api.service.project;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.common.model.Project;
import tech.swayzetrain.capacity.common.repository.ProjectRepository;

@Service
public class ProjectWriter {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ResponseEntity<Project> createProject(Project project) {
		project.setCreatedDate(LocalDateTime.now());
		project.setModifiedDate(LocalDateTime.now());
		
		Project persistedProject = projectRepository.save(project);
		
		return new ResponseEntity<>(persistedProject, HttpStatus.OK);
	}

}
