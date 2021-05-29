package tech.swayzetrain.capacity.common.model;

import java.util.List;

import tech.swayzetrain.capacity.common.enums.Role;

public class RoleCapacity {
	
	public RoleCapacity() {
		
	}
	
	public RoleCapacity(Role role, List<Capacity> capacity) {
		this.role = role;
		this.capacity = capacity;
	}

	private Role role;
	private List<Capacity> capacity;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Capacity> getCapacity() {
		return capacity;
	}

	public void setCapacity(List<Capacity> capacity) {
		this.capacity = capacity;
	}

}
