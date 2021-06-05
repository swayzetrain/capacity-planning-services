package tech.swayzetrain.capacity.common.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Project")
@JsonInclude(Include.NON_NULL)
public class Project {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Null(groups = { New.class }, message = "projectId must be null for this transaction")
	@Column(name = "Project_Id", columnDefinition = "VARCHAR2(36)", updatable = false, nullable = false)
	private UUID projectId;

	@NotNull(groups = { New.class }, message = "name must not be null for this transaction")
	@Column(name = "Name", columnDefinition = "VARCHAR2(75)")
	private String name;

	@Null(groups = { New.class }, message = "projectCapacity must be null for this transaction")
	@JsonManagedReference
	@OneToMany(mappedBy = "project")
	private List<ProjectCapacity> projectCapacity;

	@Null(groups = { New.class }, message = "projectCapacity must be null for this transaction")
	@JsonManagedReference
	@OneToMany(mappedBy = "project")
	private List<ProjectEstimate> projectEstimate;

	@Null(groups = { New.class }, message = "createdDate must be null for this transaction")
	@Column(name = "Created_Date", columnDefinition = "TIMESTAMP(6)")
	private LocalDateTime createdDate;

	@Null(groups = { New.class }, message = "modifiedDate must be null for this transaction")
	@Column(name = "Modified_Date", columnDefinition = "TIMESTAMP(6)")
	private LocalDateTime modifiedDate;

	public UUID getProjectId() {
		return projectId;
	}

	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProjectCapacity> getProjectCapacity() {
		return projectCapacity;
	}

	public void setProjectCapacity(List<ProjectCapacity> projectCapacity) {
		this.projectCapacity = projectCapacity;
	}

	public List<ProjectEstimate> getProjectEstimate() {
		return projectEstimate;
	}

	public void setProjectEstimate(List<ProjectEstimate> projectEstimate) {
		this.projectEstimate = projectEstimate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public interface New {
	}
}
