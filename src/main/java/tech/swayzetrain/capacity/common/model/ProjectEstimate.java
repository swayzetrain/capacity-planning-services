package tech.swayzetrain.capacity.common.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "Project_Estimate")
@JsonInclude(Include.NON_NULL)
public class ProjectEstimate {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Null(groups = { New.class }, message = "projectEstimateId must be null for this transaction.")
	@Column(name = "Project_Estimate_Id", columnDefinition = "VARCHAR2(36)", updatable = false, nullable = false)
	private UUID projectEstimateId;

	@Null(groups = { New.class }, message = "project must be null for this transaction.")
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Project_Id")
	private Project project;

	@NotNull(groups = { New.class }, message = "role must not be null for this transaction.")
	@Enumerated(EnumType.STRING)
	@Column(name = "Role", columnDefinition = "VARCHAR2(25)")
	private Role role;

	@NotNull(groups = { New.class }, message = "hours must not be null for this transaction.")
	@Column(name = "Hours", columnDefinition = "NUMBER(7,2)")
	private BigDecimal hours;

	@Null(groups = { New.class }, message = "createdDate must be null for this transaction.")
	@Column(name = "Created_Date", columnDefinition = "TIMESTAMP(6)")
	private LocalDateTime createdDate;

	@Null(groups = { New.class }, message = "modifiedDate must be null for this transaction.")
	@Column(name = "Modified_Date", columnDefinition = "TIMESTAMP(6)")
	private LocalDateTime modifiedDate;

	public UUID getProjectEstimateId() {
		return projectEstimateId;
	}

	public void setProjectEstimateId(UUID projectEstimateId) {
		this.projectEstimateId = projectEstimateId;
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

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
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
	
	public interface New{}
}
