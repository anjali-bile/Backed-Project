package com.fbt.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fbt.entity.Appointment;
import com.fbt.entity.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserService userService;

	public void draftEmail(Appointment appointment) {
		String email = appointment.getEmail();
		String subject = "Appointment Confirmation – Thank You for Choosing Us!";
		User doctor = userService.getDoctorById(appointment.getDoctorId());
		// Format date and time nicely
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

		String formattedDate = appointment.getAppointmentDate().format(dateFormatter);
		String formattedStartTime = appointment.getAppointmentStartTime().format(timeFormatter);
		String formattedEndTime = appointment.getAppointmentEndTime().format(timeFormatter);

		if ("Cash".equalsIgnoreCase(appointment.getPaymentMode())) {

			String body = String.format("Dear %s,\n\n"
					+ "Thank you for choosing Life Line Hospital. We are delighted to confirm your appointment has been successfully booked.\n\n"
					+ "Here are the details of your appointment:\n\n" + "Appointment Date: %s\n" + "Time: %s to %s\n"
					+ "Doctor: Dr. %s\n" + "Payment Mode: %s\n\n"
					+ "Please make sure to arrive at least 10 minutes before your scheduled time. If you have any questions or need to reschedule, do not hesitate to contact us at 123-456-7890 or hospitalsupport@gmail.com.\n\n"
					+ "We look forward to providing you with excellent care and support.\n\n"
					+ "Wishing you good health!\n\n" + "Best regards,\n" + "Life Line Hospital Team\n"
					+ "123-456-7890\n" + "hospitalsupport@gmail.com", appointment.getName(), formattedDate,
					formattedStartTime, formattedEndTime, doctor.getUserName(), // assuming this returns full name or at
																				// least first+last name
					appointment.getPaymentMode());

			sendEmail(email, subject, body);
		}
		String body = String.format("Dear %s,\n\n"
				+ "Thank you for choosing Life Line Hospital. Your appointment has been confirmed.\n\n"
				+ "Appointment Details:\n" + "Date: %s\n" + "Time: %s to %s\n" + "Doctor: Dr. %s\n"
				+ "Payment Mode: %s\n\n"
				+ "Please complete your payment prior to the appointment using the following bank details:\n\n"
				+ "Bank: ABC Bank\n" + "Account Name: Life Line Hospital\n" + "Account Number: 123456789\n"
				+ "IFSC Code: ABCD0123456\n\n" + "You can also scan the QR code below to pay conveniently:\n"
				+ "[Insert QR code here]\n\n"
				+ "Once payment is received, we will send you a confirmation. Please arrive 10 minutes early.\n\n"
				+ "If you have any questions, feel free to contact us at 123-456-7890 or hospitalsupport@gmail.com.\n\n"
				+ "Thank you for trusting us with your healthcare needs.\n\n" + "Best regards,\n"
				+ "Life Line Hospital Team\n" + "123-456-7890\n" + "hospitalsupport@gmail.com", appointment.getName(),
				formattedDate, formattedStartTime, formattedEndTime, doctor.getUserName(), appointment.getPaymentMode());
		sendEmail(email, subject, body);
	
	}

	public void sendEmail(String to, String subject, String body) {
		try {

			SimpleMailMessage mSimpleMailMessage = new SimpleMailMessage();
			mSimpleMailMessage.setTo(to);
			mSimpleMailMessage.setSubject(subject);
			mSimpleMailMessage.setText(body);
			javaMailSender.send(mSimpleMailMessage);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
