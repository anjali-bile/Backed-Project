package com.fbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Slots {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long slotsId;

	@Column(nullable = false)
	private String startTime;

	@Column(nullable = false)
	private String endTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "timeOffId")
	@JsonIgnore
	private DoctorsTimeOff doctorTimeOff;

	public Slots() {
		// TODO Auto-generated constructor stub
	}

	public long getSlotsId() {
		return slotsId;
	}

	public void setSlotsId(long slotsId) {
		this.slotsId = slotsId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public DoctorsTimeOff getDoctorTimeOff() {
		return doctorTimeOff;
	}

	public void setDoctorTimeOff(DoctorsTimeOff doctorTimeOff) {
		this.doctorTimeOff = doctorTimeOff;
	}

	@Override
	public String toString() {
		return "Slots [slotsId=" + slotsId + ", startTime=" + startTime + ", endTime=" + endTime + ", doctorTimeOff="
				+ doctorTimeOff + "]";
	}

}
