package tech.swayzetrain.capacity.common.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class Capacity {

	@Column(name = "Hours", columnDefinition = "NUMBER(4,2)")
	private BigDecimal hours;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "Date", columnDefinition = "DATE")
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
