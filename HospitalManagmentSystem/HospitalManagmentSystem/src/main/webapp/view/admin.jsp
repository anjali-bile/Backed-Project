<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <title>Admin Dashboard - Hospital Management</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    body {
      background-color: #f8f9fa;
    }

    .sidebar {
      height: 100vh;
      background-color: #343a40;
    }

    .sidebar a {
      color: #fff;
      text-decoration: none;
      padding: 15px;
      display: block;
    }

    .sidebar a:hover {
      background-color: #495057;
    }

    .content {
      padding: 20px;
    }

    .form-container {
      margin-top: 50px;
      padding: 30px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    #addDoctorForm {
      display: none;
    }
  </style>
</head>

<body>
  <div class="container-fluid">
    <div class="row">
      <!-- Sidebar -->
      <nav class="col-md-2 sidebar d-flex flex-column">
        <h4 class="text-white text-center my-3">Admin Panel</h4>
        <a href="#" id="dashboardLink">Dashboard</a>
        <a href="#" id="addDoctorLink">Add Doctor</a>
        <a href="#" id="searchDoctorLink">Search Doctor</a>
        <a href="#">View Patients</a>
        <a href="#">Appointments</a>
        <a href="#">Hospital Info</a>
        <a href="/userLogOut">Logout</a>
      </nav>

      <!-- Main Content -->
      <main class="col-md-10 content">
        <h2>Welcome, Admin</h2>
        <p class="lead">Here's a quick overview of hospital status:</p>

        <!-- Search Doctor Form -->
        <div id="searchDoctorForm" style="display: none; margin-top: 20px;">
          <input type="text" id="doctorNameInput" name="doctorName" placeholder="Enter doctor name"
            class="form-control" style="max-width: 300px; display: inline-block;" />
          <button id="searchDoctorBtn" class="btn btn-primary ms-2">Search</button>

          <!-- Result area -->
          <div id="searchResult" style="margin-top: 15px;"></div>
        </div>

        <!-- Summary Cards -->
        <div id="summaryCards">
          <div class="row">
            <div class="col-md-4">
              <div class="card text-bg-primary mb-3">
                <div class="card-body">
                  <h5 class="card-title">Total Doctors</h5>
                  <p id="doctorCount" class="card-text display-6">Loading...</p>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card text-bg-success mb-3">
                <div class="card-body">
                  <h5 class="card-title">Total Patients</h5>
                  <p id="patientCount" class="card-text display-6">Loading...</p>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card text-bg-warning mb-3">
                <div class="card-body">
                  <h5 class="card-title">Appointments Today</h5>
                  <p id="appointmentCount" class="card-text display-6">Loading...</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Add Doctor Form -->
        <div id="addDoctorForm" class="form-container">
          <h3 class="text-center mb-4">Add Doctor</h3>

          <div id="messageContainer" style="display: none;"></div>

          <form action="/addDoctor" method="POST">
            <div class="mb-3">
              <label for="email" class="form-label">Email Address</label>
              <input type="email" class="form-control" id="email" name="email" required />
            </div>
            <div class="mb-3">
              <label for="mobNo" class="form-label">Mobile Number</label>
              <input type="text" class="form-control" id="mobNo" name="mobNo" required />
            </div>
            <div class="mb-3">
              <label for="userName" class="form-label">User Name</label>
              <input type="text" class="form-control" id="userName" name="userName" required />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Password</label>
              <input type="password" class="form-control" id="password" name="password" required />
            </div>
            <div class="mb-3 text-center">
              <button type="submit" class="btn btn-primary">Add Doctor</button>
            </div>
          </form>
        </div>

        <!-- Dashboard Content -->
        <div id="dashboardContent" class="mt-5">
          <h4>Recent Appointments</h4>
          <table class="table table-bordered">
            <thead class="table-light">
              <tr>
                <th>#</th>
                <th>Patient</th>
                <th>Doctor</th>
                <th>Date</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>Ravi Kumar</td>
                <td>Dr. Mehta</td>
                <td>2025-05-06</td>
                <td><span class="badge bg-success">Confirmed</span></td>
              </tr>
            </tbody>
          </table>
        </div>
      </main>
    </div>
  </div>

  <!-- Bootstrap JS Bundle -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

  <script>
    const addDoctorLink = document.getElementById('addDoctorLink');
    const dashboardLink = document.getElementById('dashboardLink');
    const searchDoctorLink = document.getElementById('searchDoctorLink');

    const addDoctorForm = document.getElementById('addDoctorForm');
    const dashboardContent = document.getElementById('dashboardContent');
    const searchDoctorForm = document.getElementById('searchDoctorForm');
    const summaryCards = document.getElementById('summaryCards');

    // Show Add Doctor Form
    addDoctorLink.addEventListener('click', (e) => {
      e.preventDefault();
      addDoctorForm.style.display = 'block';
      dashboardContent.style.display = 'none';
      searchDoctorForm.style.display = 'none';
      summaryCards.style.display = 'none';
    });

    // Show Dashboard (default view)
    dashboardLink.addEventListener('click', (e) => {
      e.preventDefault();
      addDoctorForm.style.display = 'none';
      dashboardContent.style.display = 'block';
      searchDoctorForm.style.display = 'none';
      summaryCards.style.display = 'block';
    });

    // Show Search Doctor Form
    searchDoctorLink.addEventListener('click', (e) => {
      e.preventDefault();
      addDoctorForm.style.display = 'none';
      dashboardContent.style.display = 'none';
      searchDoctorForm.style.display = 'block';
      summaryCards.style.display = 'none';
      // Clear previous results and input
      document.getElementById('doctorNameInput').value = '';
      document.getElementById('searchResult').innerHTML = '';
    });

    // Fetch counts and update dashboard
    fetch('/countAppoitment')
      .then((res) => res.text())
      .then((count) => {
        document.getElementById('appointmentCount').textContent = count;
      })
      .catch(() => {
        document.getElementById('appointmentCount').textContent = 'N/A';
      });

    fetch('/countDoctor')
      .then((res) => res.text())
      .then((count) => {
        document.getElementById('doctorCount').textContent = count;
      })
      .catch(() => {
        document.getElementById('doctorCount').textContent = 'N/A';
      });

    fetch('/countPatient')
      .then((res) => res.text())
      .then((count) => {
        document.getElementById('patientCount').textContent = count;
      })
      .catch(() => {
        document.getElementById('patientCount').textContent = 'N/A';
      });

    // Search Doctor Button Click - stepwise display of info
    document.getElementById('searchDoctorBtn').onclick = function (e) {
      e.preventDefault();
      const name = document.getElementById('doctorNameInput').value.trim();
      const result = document.getElementById('searchResult');

      if (!name) {
        result.innerHTML = '<div class="alert alert-warning">Please enter a doctor name.</div>';
        return;
      }

      result.textContent = 'Searching...';

      fetch('/getDoctorByName?doctorName=' + encodeURIComponent(name))
        .then(res => {
          const contentType = res.headers.get('content-type') || '';
          if (contentType.includes('application/json')) {
            return res.json();
          } else {
            return res.text();
          }
        })
        .then(data => {
          if (typeof data === 'string') {
            result.innerHTML = `<div class="alert alert-info">${data}</div>`;
          } else if (data.email || data.mobNo || data.userName) {
            // Stepwise display closure
            let step = 0;
            const doctorData = data;

            function showStep() {
              if (step === 0) {
                result.innerHTML = `
                  <p><strong>Email:</strong> ${doctorData.email || 'N/A'}</p>
                  <button id="nextBtn" class="btn btn-primary">Next</button>
                `;
              } else if (step === 1) {
                result.innerHTML = `
                  <p><strong>Mobile Number:</strong> ${doctorData.mobNo || 'N/A'}</p>
                  <button id="nextBtn" class="btn btn-primary">Next</button>
                `;
              } else if (step === 2) {
                result.innerHTML = `
                  <p><strong>Username:</strong> ${doctorData.userName || 'N/A'}</p>
                  <button id="restartBtn" class="btn btn-secondary">Search Again</button>
                `;
              }

              const nextBtn = document.getElementById('nextBtn');
              const restartBtn = document.getElementById('restartBtn');

              if (nextBtn) {
                nextBtn.addEventListener('click', () => {
                  step++;
                  showStep();
                });
              }

              if (restartBtn) {
                restartBtn.addEventListener('click', () => {
                  result.innerHTML = '';
                  document.getElementById('doctorNameInput').value = '';
                  document.getElementById('doctorNameInput').focus();
                });
              }
            }

            showStep();
          } else {
            result.innerHTML = '<div class="alert alert-warning">No data found.</div>';
          }
        })
        .catch(() => {
          result.innerHTML = '<div class="alert alert-danger">Error fetching data.</div>';
        });
    };

    // Default view on page load
    dashboardLink.click();
  </script>
</body>

</html>
