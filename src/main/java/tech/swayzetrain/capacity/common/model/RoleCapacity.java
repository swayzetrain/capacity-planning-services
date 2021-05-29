package tech.swayzetrain.capacity.common.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.swayzetrain.capacity.common.enums.Role;

@JsonInclude(Include.NON_NULL)
public class RoleCapacity {

	private Role role;
	private List<DailyCapacity> dailyCapacity;
	private List<MonthlyCapacity> monthlyCapacity;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<DailyCapacity> getDailyCapacity() {
		return dailyCapacity;
	}

	public void setDailyCapacity(List<DailyCapacity> dailyCapacity) {
		this.dailyCapacity = dailyCapacity;
	}

	public List<MonthlyCapacity> getMonthlyCapacity() {
		return monthlyCapacity;
	}

	public void setMonthlyCapacity(List<MonthlyCapacity> monthlyCapacity) {
		this.monthlyCapacity = monthlyCapacity;
	}

}
