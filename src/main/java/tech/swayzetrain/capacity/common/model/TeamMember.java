package tech.swayzetrain.capacity.common.model;

import java.time.LocalDateTime;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.swayzetrain.capacity.common.enums.Role;

@Entity
@Table(name = "Team_Member")
@JsonInclude(Include.NON_NULL)
public class TeamMember {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Null(groups= {New.class, Update.class}, message = "teamMemberId must be null for this transaction")
	@Column(name = "Team_Member_Id", columnDefinition = "VARCHAR2(36)", updatable = false, nullable = false)
	private UUID teamMemberId;

	@NotNull(groups= {New.class}, message = "name must not be null for this transaction")
	@Column(name = "Name", columnDefinition = "VARCHAR2(75)")
	private String name;

	@NotNull(groups= {New.class}, message = "role must not be null for this transaction")
	@Enumerated(EnumType.STRING)
	@Column(name = "Role", columnDefinition = "VARCHAR2(25)")
	private Role role;

	@JsonManagedReference
	@OneToMany(mappedBy = "teamMember")
	private List<TeamMemberCapacity> teamMemberCapacity;

	@Null(groups= {New.class, Update.class}, message = "team must be null for this transaction")
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Team_Id")
	private Team team;

	@Null(groups= {New.class, Update.class}, message = "createdDate must be null for this transaction")
	@Column(name = "Created_Date", columnDefinition = "TIMESTAMP(6)")
	private LocalDateTime createdDate;

	@Null(groups= {New.class, Update.class}, message = "modifiedDate must be null for this transaction")
	@Column(name = "Modified_Date", columnDefinition = "TIMESTAMP(6)")
	private LocalDateTime modifiedDate;

	public UUID getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(UUID teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<TeamMemberCapacity> getTeamMemberCapacity() {
		return teamMemberCapacity;
	}

	public void setTeamMemberCapacity(List<TeamMemberCapacity> teamMemberCapacity) {
		this.teamMemberCapacity = teamMemberCapacity;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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
	
	public interface Update{}
}
