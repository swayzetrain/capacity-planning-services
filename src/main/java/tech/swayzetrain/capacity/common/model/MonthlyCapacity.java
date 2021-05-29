package tech.swayzetrain.capacity.common.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MonthlyCapacity {

	public MonthlyCapacity() {

	}

	public MonthlyCapacity(BigDecimal hours, LocalDate date) {
		this.hours = hours;
		this.date = date;
	}

	private BigDecimal hours;

	@JsonFormat(pattern = "MMM yyyy")
	private LocalDate date;

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
