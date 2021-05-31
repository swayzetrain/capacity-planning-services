package tech.swayzetrain.capacity.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.swayzetrain.capacity.api.service.project.estimate.ProjectEstimateReader;
import tech.swayzetrain.capacity.api.service.project.estimate.ProjectEstimateWriter;
import tech.swayzetrain.capacity.common.model.ProjectEstimate;
import tech.swayzetrain.capacity.common.utility.SharedUtility;

@RestController
@RequestMapping("/v1/projects/{projectId}/estimates")
public class ProjectEstimateController {
	
	@Autowired
	private ProjectEstimateReader projectEstimateReader;
	
	@Autowired
	private ProjectEstimateWriter projectEstimateWriter;
	
	private SharedUtility sharedUtility = new SharedUtility();
	
	@GetMapping("/{projectEstimateId}")
	public ResponseEntity<ProjectEstimate> getProjectEstimateById(@PathVariable("projectId") String projectId, @PathVariable("projectEstimateId") String projectEstimateId) {
		return projectEstimateReader.retrieveProjectEstimateResponseByProjectAndKey(sharedUtility.uuidFromString(projectId), sharedUtility.uuidFromString(projectEstimateId));
	}
	
	@GetMapping
	public ResponseEntity<List<ProjectEstimate>> getProjectEstimateByProject(@PathVariable("projectId") String projectId) {
		return projectEstimateReader.retrieveProjectEstimatesResponseByProject(sharedUtility.uuidFromString(projectId));
	}
	
	@PostMapping
	public ResponseEntity<ProjectEstimate> postProjectEstimate(@PathVariable("projectId") String projectId, @Validated(ProjectEstimate.New.class) @RequestBody ProjectEstimate projectEstimate) {
		return projectEstimateWriter.createProjectEstimate(sharedUtility.uuidFromString(projectId), projectEstimate);
	}

}
