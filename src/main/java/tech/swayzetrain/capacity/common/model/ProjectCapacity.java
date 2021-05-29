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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.swayzetrain.capacity.common.enums.Role;

@Entity
@Table(name = "Project_Capacity")
@JsonInclude(Include.NON_NULL)
public class ProjectCapacity extends DailyCapacity {
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "Project_Capacity_Id", columnDefinition="VARCHAR2(36)", updatable = false, nullable = false)
    private UUID projectCapacityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Project_Id")
	private Project project;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Role", columnDefinition="VARCHAR2(25)")
	private Role role;

}
