<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Doctor Appointment</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      padding: 0;
      font-family: 'Poppins', sans-serif;
      background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
      url('https://thumbs.dreamstime.com/b/illustrate-health-save-your-life-214796625.jpg') no-repeat center center fixed;
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .container {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 30px;
      width: 100%;
      max-width: 1200px;
      position: relative;
    }

    .form-section {
      width: 55%;
      background: white;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
    }

    .alert {
      margin-top: 15px;
      padding: 10px;
      border-radius: 5px;
      display: none;
    }

    .alert-success {
      background-color: #d4edda;
      color: #155724;
    }

    .alert-error {
      background-color: #f8d7da;
      color: #721c24;
    }

    #nextButton {
      display: none;
      background: #38a169;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      margin-top: 10px;
      cursor: pointer;
    }

    #nextButton:hover {
      background: #2f855a;
    }

    .text-section {
      width: 40%;
      color: white;
      text-align: left;
      padding: 20px;
      background-color: rgba(0, 0, 0, 0.5);
      border-radius: 8px;
    }

    .text-section h1 {
      font-size: 32px;
      font-weight: bold;
      margin-bottom: 15px;
    }

    .text-section p {
      font-size: 18px;
      line-height: 1.5;
    }

    h2 {
      text-align: center;
      color: #0077b6;
      margin-bottom: 20px;
    }

    .form-row {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
      justify-content: space-between;
    }

    .form-group {
      flex: 1 1 200px;
      display: flex;
      flex-direction: column;
    }

    label {
      font-weight: 500;
      margin-bottom: 5px;
      color: #333;
    }

    input, select, textarea {
      padding: 10px;
      font-size: 14px;
      border: 1px solid #ccc;
      border-radius: 6px;
    }

    textarea {
      resize: vertical;
    }

    button[type="submit"] {
      background: #0077b6;
      color: white;
      padding: 12px;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      cursor: pointer;
      margin-top: 15px;
      width: 200px;
      align-self: center;
    }

    button[type="submit"]:hover {
      background: #023e8a;
    }

    @media (max-width: 768px) {
      .form-row {
        flex-direction: column;
      }

      .container {
        flex-direction: column;
        align-items: center;
        gap: 50px;
      }

      .text-section, .form-section {
        width: 100%;
      }
    }
  </style>
</head>
<body>
  <div class="container">
    <!-- Text Section -->
    <div class="text-section">
      <h1>Save Your Life</h1>
      <p>Book a doctor's appointment and stay healthy. Get access to the best medical professionals available and take care of your health with ease. Don't wait for symptoms to worsen, book your appointment now!</p>
    </div>

    <!-- Form Section -->
    <div class="form-section">
      <h2>Check Availability (10 AM - 19 PM)</h2>
      <form id="checkAvailabilityForm">
        <div class="form-row">
          <div class="form-group">
            <label for="doctorName">Doctor First Name</label>
            <input type="text" id="doctorName" name="username" placeholder="Enter doctor name" required>
          </div>

          <div class="form-group">
            <label for="date">Date</label>
            <input type="date" id="date" name="date" required>
          </div>

          <div class="form-group">
            <label for="startTime">Start Time</label>
            <input type="time" id="startTime" name="startTime" required>
          </div>

          <div class="form-group">
            <label for="endTime">End Time</label>
            <input type="time" id="endTime" name="endTime" required>
          </div>

          <div class="form-group" style="text-align: center;">
            <button type="submit">Check</button>
          </div>
        </div>
      </form>

      <div id="responseMessage" class="alert"></div>
      <button id="nextButton">Next</button>
    </div>
  </div>

  <script>
    const form = document.getElementById("checkAvailabilityForm");
    const responseMessage = document.getElementById("responseMessage");
    const nextButton = document.getElementById("nextButton");

    form.addEventListener("submit", function(event) {
      event.preventDefault();

      const formData = new FormData(form);

      fetch("/checkDoctorAvailibility", {
        method: "POST",
        body: formData,
      })
      .then(response => response.text())
      .then(data => {
        responseMessage.style.display = "block";
        responseMessage.innerText = data;

        if (data.toLowerCase().includes("available")) {
          responseMessage.className = "alert alert-success";
          nextButton.style.display = "inline-block";
        } else {
          responseMessage.className = "alert alert-error";
          nextButton.style.display = "none";
        }
      })
      .catch(error => {
        responseMessage.className = "alert alert-error";
        responseMessage.innerText = "Error checking availability. Try again.";
        responseMessage.style.display = "block";
        nextButton.style.display = "none";
      });
    });

    nextButton.addEventListener("click", function() {
      // Replace with the actual action (redirect, show new form, etc.)
      alert("Proceeding to the next step...");
      window.location.href = "/appoitmentPage";
    });
  </script>
</body>
</html>
