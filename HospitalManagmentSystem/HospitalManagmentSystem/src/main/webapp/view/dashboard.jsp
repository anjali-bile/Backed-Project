<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <style>
    body { font-family: Arial; margin: 20px; background: #f4f4f4; }
    .section { background: white; padding: 20px; margin-bottom: 20px; border-radius: 8px; }
    input, button { margin-top: 10px; padding: 5px; }
  </style>
</head>
<body>

  <h1>Admin Dashboard</h1>

  <div class="section">
    <h2>Search Doctor</h2>
    <input type="text" id="doctorId" placeholder="Doctor ID">
    <button onclick="getDoctorById()">Get Doctor by ID</button><br>
    <input type="text" id="doctorName" placeholder="Doctor Name">
    <button onclick="getDoctorByName()">Get Doctor by Name</button>
    <pre id="doctorResult"></pre>
  </div>

  <div class="section">
    <h2>Search User</h2>
    <input type="text" id="userId" placeholder="User ID">
    <button onclick="getUserById()">Get User by ID</button><br>
    <input type="text" id="userName" placeholder="User Name">
    <button onclick="getUserByName()">Get User by Name</button>
    <pre id="userResult"></pre>
  </div>

  <div class="section">
    <h2>Appointments</h2>
    <button onclick="getTodaysAppointments()">Today's Appointments Count</button>
    <pre id="todayAppointments"></pre>
    <input type="date" id="appointmentDate">
    <button onclick="getAppointmentsByDate()">Get Appointments by Date</button>
    <pre id="appointmentResult"></pre>
  </div>

  <script>
    const BASE_URL = "http://localhost:8080/admin"; // adjust if needed

    function getDoctorById() {
      const id = document.getElementById('doctorId').value;
      fetch(`${BASE_URL}/doctors/${id}`)
        .then(res => res.json())
        .then(data => document.getElementById('doctorResult').textContent = JSON.stringify(data, null, 2));
    }

    function getDoctorByName() {
      const name = document.getElementById('doctorName').value;
      fetch(`${BASE_URL}/doctors/search?name=${name}`)
        .then(res => res.json())
        .then(data => document.getElementById('doctorResult').textContent = JSON.stringify(data, null, 2));
    }

    function getUserById() {
      const id = document.getElementById('userId').value;
      fetch(`${BASE_URL}/users/${id}`)
        .then(res => res.json())
        .then(data => document.getElementById('userResult').textContent = JSON.stringify(data, null, 2));
    }

    function getUserByName() {
      const name = document.getElementById('userName').value;
      fetch(`${BASE_URL}/users/search?name=${name}`)
        .then(res => res.json())
        .then(data => document.getElementById('userResult').textContent = JSON.stringify(data, null, 2));
    }

    function getTodaysAppointments() {
      fetch(`${BASE_URL}/appointments/today`)
        .then(res => res.json())
        .then(data => document.getElementById('todayAppointments').textContent = `Today's Appointments: ${data}`);
    }

    function getAppointmentsByDate() {
      const date = document.getElementById('appointmentDate').value;
      fetch(`${BASE_URL}/appointments?date=${date}`)
        .then(res => res.json())
        .then(data => document.getElementById('appointmentResult').textContent = JSON.stringify(data, null, 2));
    }
  </script>

</body>
</html>
