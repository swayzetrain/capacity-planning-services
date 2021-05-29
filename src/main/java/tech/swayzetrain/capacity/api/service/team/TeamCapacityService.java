package tech.swayzetrain.capacity.api.service.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.swayzetrain.capacity.common.enums.Role;
import tech.swayzetrain.capacity.common.model.Capacity;
import tech.swayzetrain.capacity.common.model.RoleCapacity;
import tech.swayzetrain.capacity.common.model.Team;

@Service
public class TeamCapacityService {
	
	@Autowired
	private TeamReader teamReader;
	
	public ResponseEntity<List<RoleCapacity>> getTeamRoleCapacity(UUID teamId) {
		List<RoleCapacity> roleCapacityList = Collections.synchronizedList(new ArrayList<>());
		
		for(Role role: Role.values()) {
			roleCapacityList.add(new RoleCapacity(role, Collections.synchronizedList(new ArrayList<Capacity>())));
		}
		
		Team team = teamReader.retrieveTeamByKey(teamId);
		
		team.getTeamMembers().stream().forEach(teamMember -> {
			teamMember.getTeamMemberCapacity().stream().forEach(teamMemberCapacity -> {
				List<Capacity> capacityList = null;
				Boolean capacityAdded = false;
				
				for(RoleCapacity roleCapacity: roleCapacityList) {
					if(roleCapacity.getRole().equals(teamMember.getRole())) {
						capacityList = roleCapacity.getCapacity();
					}
				}
				
				if(null == capacityList) {
					System.out.println("Ugh!!!!!!!");
				}
				
				for(Capacity capacity: capacityList) {
					if(capacity.getDate().isEqual(teamMemberCapacity.getDate())) {
						capacity.setHours(capacity.getHours().add(teamMemberCapacity.getHours()));
						capacityAdded = true;
					}
				}
				
				if(!capacityAdded) {
					capacityList.add(teamMemberCapacity);
				}
			});
		});
		
		
		return new ResponseEntity<>(roleCapacityList, HttpStatus.OK);
	}

}
