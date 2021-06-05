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
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Team")
@JsonInclude(Include.NON_NULL)
public class Team {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Null(groups= {New.class}, message = "teamId must be null for this transaction")
	@Column(name = "Team_Id", columnDefinition = "VARCHAR2(36)", updatable = false, nullable = false)
	private UUID teamId;

	@NotNull(groups= {New.class}, message = "name must not be null for this transaction")
	@Column(name = "Name", columnDefinition = "VARCHAR2(75)")
	private String name;

	@Null(groups= {New.class}, message = "teamMembers must be null for this transaction")
	@JsonManagedReference
	@OneToMany(mappedBy = "team")
	private List<TeamMember> teamMembers;

	@Column(name = "Created_Date", columnDefinition = "TIMESTAMP(6)")
	@Null(groups= {New.class}, message = "createdDate must be null for this transaction")
	private LocalDateTime createdDate;

	@Column(name = "Modified_Date", columnDefinition = "TIMESTAMP(6)")
	@Null(groups= {New.class}, message = "modifiedDate must be null for this transaction")
	private LocalDateTime modifiedDate;

	public UUID getTeamId() {
		return teamId;
	}

	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
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
