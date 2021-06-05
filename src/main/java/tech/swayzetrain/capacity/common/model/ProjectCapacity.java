package tech.swayzetrain.capacity.common.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.swayzetrain.capacity.common.enums.Role;

@Entity
@Table(name = "Project_Capacity")
@JsonInclude(Include.NON_NULL)
public class ProjectCapacity extends DailyCapacity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Null(groups = { New.class }, message = "projectCapacityId must be null for this transaction")
	@Column(name = "Project_Capacity_Id", columnDefinition = "VARCHAR2(36)", updatable = false, nullable = false)
	private UUID projectCapacityId;

	@Null(groups = { New.class }, message = "project must be null for this transaction")
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Project_Id")
	private Project project;

	@NotNull(groups = { New.class }, message = "role must not be null for this transaction")
	@Enumerated(EnumType.STRING)
	@Column(name = "Role", columnDefinition = "VARCHAR2(25)")
	private Role role;

	public UUID getProjectCapacityId() {
		return projectCapacityId;
	}

	public void setProjectCapacityId(UUID projectCapacityId) {
		this.projectCapacityId = projectCapacityId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public interface New{}

}
