<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f0f2f5;
    }

    header {
      background-color: #2d3e50;
      color: white;
      padding: 20px;
      text-align: center;
      font-size: 28px;
      font-weight: bold;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .dashboard {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
      gap: 20px;
      padding: 30px;
    }

    .card {
      background-color: white;
      border-radius: 12px;
      padding: 20px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.08);
      transition: transform 0.2s ease;
    }

    .card:hover {
      transform: translateY(-5px);
    }

    .card h2 {
      margin-top: 0;
      color: #333;
    }

    input, button, select {
      margin: 8px 0;
      padding: 10px;
      width: 100%;
      border-radius: 6px;
      border: 1px solid #ccc;
      font-size: 14px;
      box-sizing: border-box;
    }

    button {
      background-color: #007bff;
      color: white;
      border: none;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    button:hover {
      background-color: #0056b3;
    }

    pre {
      background: #f8f9fa;
      padding: 10px;
      border-radius: 8px;
      overflow-x: auto;
      white-space: pre-wrap;
      word-wrap: break-word;
      max-height: 200px;
    }
  </style>
</head>
<body>

  <header>Admin Dashboard</header>

  <div class="dashboard">

    <!-- Add Doctor -->
    <div class="card">
      <h2>Add Doctor</h2>
      <input type="text" id="newDoctorName" placeholder="Name" required>
      <input type="text" id="newDoctorSpeciality" placeholder="Speciality" required>
      <input type="text" id="newDoctorEmail" placeholder="Email" required>
      <button onclick="addDoctor()">Add Doctor</button>
      <pre id="addDoctorResult">Status will appear here</pre>
    </div>

    <!-- Doctor Section -->
    <div class="card">
      <h2>Search Doctor</h2>
      <input type="text" id="doctorId" placeholder="Doctor ID">
      <button onclick="getDoctorById()">Get Doctor by ID</button>
      <input type="text" id="doctorName" placeholder="Doctor Name">
      <button onclick="getDoctorByName()">Get Doctor by Name</button>
      <pre id="doctorResult">Result will appear here</pre>
    </div>

    <!-- User Section -->
    <div class="card">
      <h2>Search User</h2>
      <input type="text" id="userId" placeholder="User ID">
      <button onclick="getUserById()">Get User by ID</button>
      <input type="text" id="userName" placeholder="User Name">
      <button onclick="getUserByName()">Get User by Name</button>
      <pre id="userResult">Result will appear here</pre>
    </div>

    <!-- Appointments Section -->
    <div class="card">
      <h2>Appointments</h2>
      <button onclick="getTodaysAppointments()">Today's Appointments Count</button>
      <pre id="todayAppointments">Count will appear here</pre>
      <input type="date" id="appointmentDate">
      <button onclick="getAppointmentsByDate()">Get Appointments by Date</button>
      <pre id="appointmentResult">Results will appear here</pre>
    </div>

  </div>

  <script>
    const BASE_URL = "http://localhost:8080/admin"; // Change if needed

    // Add Doctor API
    function addDoctor() {
      const name = document.getElementById('newDoctorName').value.trim();
      const speciality = document.getElementById('newDoctorSpeciality').value.trim();
      const email = document.getElementById('newDoctorEmail').value.trim();

      const doctor = { name, speciality, email };

      fetch(`${BASE_URL}/doctors`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(doctor)
      })
      .then(res => res.json())
      .then(data => {
        document.getElementById('addDoctorResult').textContent = JSON.stringify(data, null, 2);
      })
      .catch(err => {
        document.getElementById('addDoctorResult').textContent = "Error: " + err;
      });
    }

    function getDoctorById() {
      const id = document.getElementById('doctorId').value.trim();
      fetch(`${BASE_URL}/doctors/${id}`)
        .then(res => res.json())
        .then(data => document.getElementById('doctorResult').textContent = JSON.stringify(data, null, 2));
    }

    function getDoctorByName() {
      const name = document.getElementById('doctorName').value.trim();
      fetch(`${BASE_URL}/doctors/search?name=${name}`)
        .then(res => res.json())
        .then(data => document.getElementById('doctorResult').textContent = JSON.stringify(data, null, 2));
    }

    function getUserById() {
      const id = document.getElementById('userId').value.trim();
      fetch(`${BASE_URL}/users/${id}`)
        .then(res => res.json())
        .then(data => document.getElementById('userResult').textContent = JSON.stringify(data, null, 2));
    }

    function getUserByName() {
      const name = document.getElementById('userName').value.trim();
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
