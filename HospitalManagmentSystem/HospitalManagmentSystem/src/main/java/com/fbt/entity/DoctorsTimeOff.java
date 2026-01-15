package com.fbt.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DoctorsTimeOff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long timeoffId;

	@Column(nullable = false)
	private String doctorUserame;

	@Column(nullable = false)
	private boolean dayOff;

	@Column(nullable = false)
	private LocalDate timeOffDate;

	@OneToMany(mappedBy = "doctorTimeOff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Slots> unavailableTimeSlots;

	public long getTimeoffId() {
		return timeoffId;
	}

	public void setTimeoffId(long timeoffId) {
		this.timeoffId = timeoffId;
	}

	public String getDoctorUserame() {
		return doctorUserame;
	}

	public void setDoctorUserame(String doctorUserame) {
		this.doctorUserame = doctorUserame;
	}

	public boolean isDayOff() {
		return dayOff;
	}

	public void setDayOff(boolean dayOff) {
		this.dayOff = dayOff;
	}

	public LocalDate getTimeOffDate() {
		return timeOffDate;
	}

	public void setTimeOffDate(LocalDate timeOffDate) {
		this.timeOffDate = timeOffDate;
	}

	public List<Slots> getUnavailableTimeSlots() {
		return unavailableTimeSlots;
	}

	public void setUnavailableTimeSlots(List<Slots> unavailableTimeSlots) {
		this.unavailableTimeSlots = unavailableTimeSlots;
	}

	@Override
	public String toString() {
		return "DoctorsTimeOff [timeoffId=" + timeoffId + ", doctorUserame=" + doctorUserame + ", dayOff=" + dayOff
				+ ", timeOffDate=" + timeOffDate + ", unavailableTimeSlots=" + unavailableTimeSlots + "]";
	}

	public DoctorsTimeOff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorsTimeOff(long timeoffId, String doctorUserame, boolean dayOff, LocalDate timeOffDate,
			List<Slots> unavailableTimeSlots) {
		super();
		this.timeoffId = timeoffId;
		this.doctorUserame = doctorUserame;
		this.dayOff = dayOff;
		this.timeOffDate = timeOffDate;
		this.unavailableTimeSlots = unavailableTimeSlots;
	}

}
