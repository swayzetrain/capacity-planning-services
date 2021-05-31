package tech.swayzetrain.capacity.common.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class DailyCapacity {
	
	public DailyCapacity() {
		
	}
	
	public DailyCapacity(BigDecimal hours, LocalDate date) {
		this.hours = hours;
		this.date = date;
	}

	@Column(name = "Hours", columnDefinition = "NUMBER(6,2)")
	private BigDecimal hours;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "C_Date", columnDefinition = "DATE")
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
