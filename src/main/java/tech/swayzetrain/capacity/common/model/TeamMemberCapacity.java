package tech.swayzetrain.capacity.common.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Team_Member_Capacity")
@JsonInclude(Include.NON_NULL)
public class TeamMemberCapacity extends DailyCapacity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "Team_Member_Capacity_Id", columnDefinition = "VARCHAR2(36)", updatable = false, nullable = false)
	private UUID teamMemberCapacityId;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Team_Member_Id")
	private TeamMember teamMember;

	@Column(name = "Modified_Date", columnDefinition = "TIMESTAMP(6)")
	@Null(groups = { New.class }, message = "modifiedDate must be null for this transaction.")
	private LocalDateTime modifiedDate;

	public UUID getTeamMemberCapacityId() {
		return teamMemberCapacityId;
	}

	public void setTeamMemberCapacityId(UUID teamMemberCapacityId) {
		this.teamMemberCapacityId = teamMemberCapacityId;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
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
