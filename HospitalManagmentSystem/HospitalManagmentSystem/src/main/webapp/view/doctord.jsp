<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fbt.entity.Appointment" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctor Panel - Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .sidebar {
            height: 100vh;
            background-color: #1379d2;
        }
        .sidebar a {
            color: #f2efef;
            text-decoration: none;
            padding: 15px;
            display: block;
        }
        .sidebar a:hover {
            background-color: #de9611;
        }
        .dashboard {
            background-size: cover;
            background-position: center;
            color: rgb(136, 162, 21);
            min-height: 100vh;
            padding: 20px;
        }
        .table-container {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            margin-top: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .appointments-table {
            display: none;
        }
        .appointments-table.active {
            display: block;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 sidebar d-flex flex-column">
            <h4 class="text-white text-center my-3">Doctor Panel</h4>
            <a href="javascript:void(0);" onclick="showAppointments('today')">Today's Appointments</a>
            <a href="javascript:void(0);" onclick="showAppointments('history')">Appointments History</a>
            <a href="javascript:void(0);" onclick="showAppointments('upcoming')">Upcoming Appointments</a>
            <a href="/doctorTimeOffPage">Schedule Time Off</a>
         
            <a href="/userLogOut">Logout</a>
        </div>

        <!-- Dashboard Content -->
        <div class="col-md-10 dashboard">
            <div class="container table-container">

                <!-- Display Welcome Message -->
                <%
                    String welcomeMessage = (String) session.getAttribute("welcomeMessage");
                    List<Appointment> todaysAppointments = (List<Appointment>) session.getAttribute("todaysAppointments");
                    List<Appointment> allAppointments = (List<Appointment>) session.getAttribute("allAppointments");
                      List<Appointment> upcomingAppointments = (List<Appointment>) session.getAttribute("upcomingAppointments");

                    if (todaysAppointments == null) {
                        todaysAppointments = new ArrayList<>();
                    }
                    if (allAppointments == null) {
                        allAppointments = new ArrayList<>();
                    }
                    if (upcomingAppointments == null) {
                      upcomingAppointments = new ArrayList<>();
                  }
                %>

                <h3><%= welcomeMessage != null ? welcomeMessage : "Welcome to your Dashboard" %></h3>

                <!-- Display Today's Appointments Table -->
                <div id="todayAppointments" class="appointments-table active">
                    <h4>Today's Appointments</h4>
                    <% if (todaysAppointments.isEmpty()) { %>
                        <div class="alert alert-warning mt-3" role="alert">
                            No appointments found for today.
                        </div>
                    <% } else { %>
                        <table class="table table-bordered table-hover mt-3">
                            <thead class="table-dark">
                                <tr>
                                    <th>Patient Name</th>
                                    <th>Gender</th>
                                    <th>Phone</th>
                                    <th>Start Time</th>
                                    <th>End Time</th>
                                    <th>Reason</th>
                                    <th>Description</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Appointment appointment : todaysAppointments) { %>
                                    <tr>
                                        <td><%= appointment.getName() %></td>
                                        <td><%= appointment.getGender() %></td>
                                        <td><%= appointment.getPhone() %></td>
                                        <td><%= appointment.getAppointmentStartTime() %></td>
                                        <td><%= appointment.getAppointmentEndTime() %></td>
                                        <td><%= appointment.getAppointmentReason() %></td>
                                        <td><%= appointment.getProblemdescription() %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    <% } %>
                </div>

                <!-- Display Appointment History Table -->
                <div id="appointmentHistory" class="appointments-table">
                    <h4>Appointments History</h4>
                    <% if (allAppointments.isEmpty()) { %>
                        <div class="alert alert-warning mt-3" role="alert">
                            No appointments found in history.
                        </div>
                    <% } else { %>
                        <table class="table table-bordered table-hover mt-3">
                            <thead class="table-dark">
                                <tr>
                                    <th>Patient Name</th>
                                    <th>Gender</th>
                                    <th>Phone</th>
                                    <th>Start Time</th>
                                    <th>End Time</th>
                                    <th>Reason</th>
                                    <th>Description</th>
                                    <th>Appointment Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Appointment appointment : allAppointments) { %>
                                    <tr>
                                        <td><%= appointment.getName() %></td>
                                        <td><%= appointment.getGender() %></td>
                                        <td><%= appointment.getPhone() %></td>
                                        <td><%= appointment.getAppointmentStartTime() %></td>
                                        <td><%= appointment.getAppointmentEndTime() %></td>
                                        <td><%= appointment.getAppointmentReason() %></td>
                                        <td><%= appointment.getProblemdescription() %></td>
                                        <td><%= appointment.getAppointmentDate() != null ? appointment.getAppointmentDate().toString() : "" %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    <% } %>
                </div>

                <!-- Upcoming  --> 
                <div id="upcomingAppointment" class="appointments-table">
                  <h4>Appointments History</h4>
                  <% if (upcomingAppointments.isEmpty()) { %>
                      <div class="alert alert-warning mt-3" role="alert">
                          No appointments found in history.
                      </div>
                  <% } else { %>
                      <table class="table table-bordered table-hover mt-3">
                          <thead class="table-dark">
                              <tr>
                                  <th>Patient Name</th>
                                  <th>Gender</th>
                                  <th>Phone</th>
                                  <th>Start Time</th>
                                  <th>End Time</th>
                                  <th>Reason</th>
                                  <th>Description</th>
                                  <th>Appointment Date</th>
                              </tr>
                          </thead>
                          <tbody>
                              <% for (Appointment appointment : upcomingAppointments) { %>
                                  <tr>
                                      <td><%= appointment.getName() %></td>
                                      <td><%= appointment.getGender() %></td>
                                      <td><%= appointment.getPhone() %></td>
                                      <td><%= appointment.getAppointmentStartTime() %></td>
                                      <td><%= appointment.getAppointmentEndTime() %></td>
                                      <td><%= appointment.getAppointmentReason() %></td>
                                      <td><%= appointment.getProblemdescription() %></td>
                                      <td><%= appointment.getAppointmentDate() != null ? appointment.getAppointmentDate().toString() : "" %></td>
                                  </tr>
                              <% } %>
                          </tbody>
                      </table>
                  <% } %>
              </div>
<!--  Table -->

            </div>
        </div>
    </div>
</div>

<script>
    function showAppointments(type) {
        // Hide both tables first
        document.getElementById('todayAppointments').classList.remove('active');
        document.getElementById('appointmentHistory').classList.remove('active');
        document.getElementById('upcomingAppointment').classList.remove('active');
        
        // Show the selected table
        if (type === 'today') {
            document.getElementById('todayAppointments').classList.add('active');
        } else if (type === 'history') {
            document.getElementById('appointmentHistory').classList.add('active');
        }
        else if (type === 'upcoming') {
            document.getElementById('upcomingAppointment').classList.add('active');
        }
    }
</script>

</body>
</html>
