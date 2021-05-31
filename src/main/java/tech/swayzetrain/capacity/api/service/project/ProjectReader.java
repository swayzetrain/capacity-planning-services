package tech.swayzetrain.capacity.api.service.project;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.common.exception.ObjectNotFoundException;
import tech.swayzetrain.capacity.common.model.Project;
import tech.swayzetrain.capacity.common.repository.ProjectRepository;

@Service
public class ProjectReader {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project getProjectByKey(UUID projectId) {
		Project project = projectRepository.findByProjectId(projectId);
		
		if(null == project) {
			throw new ObjectNotFoundException(String.format("No Project exists by projectId, %s", projectId.toString()));
		}
		
		return project;
	}
	
	public ResponseEntity<Project> getProjectResponseByKey(UUID projectId) {
		Project project = getProjectByKey(projectId);
		
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
	
	public ResponseEntity<List<Project>> retrieveAllProjects() {
		List<Project> projects = projectRepository.findAll();
		
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

}
