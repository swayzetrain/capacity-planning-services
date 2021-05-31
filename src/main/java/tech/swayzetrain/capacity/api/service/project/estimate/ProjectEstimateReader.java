package tech.swayzetrain.capacity.api.service.project.estimate;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.api.service.project.ProjectReader;
import tech.swayzetrain.capacity.common.exception.ObjectNotFoundException;
import tech.swayzetrain.capacity.common.model.Project;
import tech.swayzetrain.capacity.common.model.ProjectEstimate;
import tech.swayzetrain.capacity.common.repository.ProjectEstimateRepository;

@Service
public class ProjectEstimateReader {
	
	@Autowired
	private ProjectEstimateRepository projectEstimateRepository;
	
	@Autowired
	private ProjectReader projectReader;
	
	public ProjectEstimate retrieveProjectEstimateByProjectAndKey(UUID projectId, UUID projectEstimateId) {

		Project project = projectReader.getProjectByKey(projectId);

		ProjectEstimate projectEstimate = projectEstimateRepository.findByProjectEstimateIdAndProject(projectEstimateId, project);

		if (null == projectEstimate) {
			throw new ObjectNotFoundException(String.format("No Project Estimate exists by projectEstimateId, %s, for Project, %s",
					projectEstimateId.toString(), projectId.toString()));
		}

		return projectEstimate;
	}

	public ResponseEntity<ProjectEstimate> retrieveProjectEstimateResponseByProjectAndKey(UUID projectId, UUID projectEstimateId) {

		ProjectEstimate projectEstimate = retrieveProjectEstimateByProjectAndKey(projectId, projectEstimateId);

		return new ResponseEntity<>(projectEstimate, HttpStatus.OK);
	}

	public ResponseEntity<List<ProjectEstimate>> retrieveProjectEstimatesResponseByProject(UUID projectId) {
		Project project = projectReader.getProjectByKey(projectId);

		List<ProjectEstimate> projectEstimates = projectEstimateRepository.findByProject(project);

		return new ResponseEntity<>(projectEstimates, HttpStatus.OK);
	}

}
