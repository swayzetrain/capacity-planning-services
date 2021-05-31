package tech.swayzetrain.capacity.api.service.project.estimate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.api.service.project.ProjectReader;
import tech.swayzetrain.capacity.common.model.ProjectEstimate;
import tech.swayzetrain.capacity.common.repository.ProjectEstimateRepository;

@Service
public class ProjectEstimateWriter {
	
	@Autowired
	private ProjectEstimateRepository projectEstimateRepository;
	
	@Autowired
	private ProjectReader projectReader;
	
	public ResponseEntity<ProjectEstimate> createProjectEstimate(UUID projectId, ProjectEstimate projectEstimate) {
		projectEstimate.setProject(projectReader.getProjectByKey(projectId));
		
		projectEstimate.setCreatedDate(LocalDateTime.now());
		projectEstimate.setModifiedDate(LocalDateTime.now());
		
		ProjectEstimate persistedProjectEstimate = projectEstimateRepository.save(projectEstimate);
		
		return new ResponseEntity<>(persistedProjectEstimate, HttpStatus.OK);
	}

}
