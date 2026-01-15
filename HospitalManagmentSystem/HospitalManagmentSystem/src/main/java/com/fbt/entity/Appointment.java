package com.fbt.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentpatientid;

//	@NotBlank
//	@Pattern(regexp = "^[a-zA-Z ]+[a-zA-Z0-9]*$", message = "First name not valid")
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String gender;

//	@Size(min = 10, max = 10, message = "Mobile Number Should Be 10 Digit")
//	@Pattern(regexp = "^[0-9]+$", message = "Invalid Mobile Number")
	@Column(nullable = false)
	private long phone;

//	@NotBlank
//	@Pattern(regexp = "^[a-zA-Z ]+[a-zA-Z0-9]*$", message = "City not valid")
	@Column(nullable = false)
	private String address;

	//@Email(message = "Mail not valid")
	private String email;

	@Column(nullable = false)
	private boolean appliedBefore;

	//@NotBlank(message = "Appointment reason is required")
	@Column(nullable = false)
	private String appointmentReason;

	private LocalDate bithdate;

	//@NotNull(message = "DoctorId Is Required")
	private int doctorId;

	private LocalDate appointmentTakenDate;

	@Column(name = "AppointmentTakenTime")
	private String appointmentTakenTime;

	private LocalDate appointmentDate;

	//@NotNull(message = "AppointmentTime Is Requird")
	private LocalTime appointmentStartTime;

	//@NotNull(message = "AppointmentTime Is Requird")
	private LocalTime appointmentEndTime;

	//@NotNull(message = "ProblemDescription Is Requird")
	private String problemdescription;

	private String paymentMode;

	public int getAppointmentpatientid() {
		return appointmentpatientid;
	}

	public void setAppointmentpatientid(int appointmentpatientid) {
		this.appointmentpatientid = appointmentpatientid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAppliedBefore() {
		return appliedBefore;
	}

	public void setAppliedBefore(boolean appliedBefore) {
		this.appliedBefore = appliedBefore;
	}

	public String getAppointmentReason() {
		return appointmentReason;
	}

	public void setAppointmentReason(String appointmentReason) {
		this.appointmentReason = appointmentReason;
	}

	public LocalDate getBithdate() {
		return bithdate;
	}

	public void setBithdate(LocalDate bithdate) {
		this.bithdate = bithdate;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getAppointmentTakenDate() {
		return appointmentTakenDate;
	}

	public void setAppointmentTakenDate(LocalDate appointmentTakenDate) {
		this.appointmentTakenDate = appointmentTakenDate;
	}

	public String getAppointmentTakenTime() {
		return appointmentTakenTime;
	}

	public void setAppointmentTakenTime(String appointmentTakenTime) {
		this.appointmentTakenTime = appointmentTakenTime;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(LocalTime appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public LocalTime getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(LocalTime appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public String getProblemdescription() {
		return problemdescription;
	}

	public void setProblemdescription(String problemdescription) {
		this.problemdescription = problemdescription;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Appointment(int appointmentpatientid, String name, String gender, long phone, String address, String email,
			boolean appliedBefore, String appointmentReason, LocalDate bithdate, int doctorId,
			LocalDate appointmentTakenDate, String appointmentTakenTime, LocalDate appointmentDate,
			LocalTime appointmentStartTime, LocalTime appointmentEndTime, String problemdescription,
			String paymentMode) {
		super();
		this.appointmentpatientid = appointmentpatientid;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.appliedBefore = appliedBefore;
		this.appointmentReason = appointmentReason;
		this.bithdate = bithdate;
		this.doctorId = doctorId;
		this.appointmentTakenDate = appointmentTakenDate;
		this.appointmentTakenTime = appointmentTakenTime;
		this.appointmentDate = appointmentDate;
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.problemdescription = problemdescription;
		this.paymentMode = paymentMode;
	}

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Appointment [appointmentpatientid=" + appointmentpatientid + ", name=" + name + ", gender=" + gender
				+ ", phone=" + phone + ", address=" + address + ", email=" + email + ", appliedBefore=" + appliedBefore
				+ ", appointmentReason=" + appointmentReason + ", bithdate=" + bithdate + ", doctorId=" + doctorId
				+ ", appointmentTakenDate=" + appointmentTakenDate + ", appointmentTakenTime=" + appointmentTakenTime
				+ ", appointmentDate=" + appointmentDate + ", appointmentStartTime=" + appointmentStartTime
				+ ", appointmentEndTime=" + appointmentEndTime + ", problemdescription=" + problemdescription
				+ ", paymentMode=" + paymentMode + "]";
	}
	
	
	
}
