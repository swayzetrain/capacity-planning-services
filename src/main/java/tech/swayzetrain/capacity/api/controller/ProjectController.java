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

import tech.swayzetrain.capacity.api.service.project.ProjectReader;
import tech.swayzetrain.capacity.api.service.project.ProjectWriter;
import tech.swayzetrain.capacity.common.model.Project;
import tech.swayzetrain.capacity.common.utility.SharedUtility;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {
	
	@Autowired
	private ProjectReader projectReader;
	
	@Autowired
	private ProjectWriter projectWriter;
	
	private SharedUtility sharedUtility = new SharedUtility();
	
	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects() {
		return projectReader.retrieveAllProjects();
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectByKey(@PathVariable("projectId") String projectId) {
		return projectReader.getProjectResponseByKey(sharedUtility.uuidFromString(projectId));
	}
	
	@PostMapping
	public ResponseEntity<Project> postProject(@Validated(Project.New.class)@RequestBody Project project) {
		return projectWriter.createProject(project);
	}

}
