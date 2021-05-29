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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Project")
@JsonInclude(Include.NON_NULL)
public class Project {
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "Project_Id", columnDefinition="VARCHAR2(36)", updatable = false, nullable = false)
    private UUID projectId;
	
	@Column(name = "Name", columnDefinition="VARCHAR2(75)")
	private String name;
	
	@OneToMany(mappedBy="project")
	private List<ProjectCapacity> projectCapacity;
	
	@Column(name = "Created_Date", columnDefinition="TIMESTAMP(6)")
	private LocalDateTime createdDate;
	
	@Column(name = "Modified_Date", columnDefinition="TIMESTAMP(6)")
	private LocalDateTime modifiedDate;

}
