<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Health Center Demo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js"></script>
    <style>
        /* General Reset */
        *, html, body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            overflow-x: hidden;
            font-family: Helvetica, Arial, sans-serif;
        }

        /* Header */
        header {
            width: 100%;
            height: 3.5em;
            position: absolute;
            top: 0;
            left: 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 1em;
            background: transparent;
            z-index: 10;
            color: white;
        }

        header p {
            font-size: 1.5em;
            text-transform: uppercase;
        }

        header nav a {
            color: white;
            text-decoration: none;
            margin: 0 0.5em;
            font-size: 0.85em;
        }

        header nav button {
            background: #598959;
            border: none;
            padding: 0.5em 1em;
            border-radius: 12px;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        header nav button:hover {
            background: #02de02;
        }

        /* Main Banner */
        .heading {
            height: 30em;
            background-image: url("https://cdn.pixabay.com/photo/2024/03/26/11/57/woman-8656638_1280.jpg");
            background-size: cover;
            background-position: center;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            text-align: center;
            position: relative;
        }

        .inner p {
            font-size: 1.5em;
            margin-bottom: 0.5em;
        }

        .inner h1 {
            font-size: 3em;
            margin-bottom: 0.5em;
        }

        /* Radio Button Styling */
        .radio-container {
            display: flex;
            gap: 10px;
            justify-content: center;
            margin-top: 10px;
        }

        .radio-btn {
            width: 12px;
            height: 12px;
            border-radius: 50%;
            background-color: lightgray;
            cursor: pointer;
        }

        .radio-btn.active {
            background-color: green;
        }

        /* CTA Section */
        .cta {
            padding: 2em 5%;
            display: grid;
            grid-template-columns: 2fr 1fr;
            gap: 2em;
        }

        .cta img {
            width: 100%;
            border-radius: 10px;
        }

        .cta h3 {
            color: deeppink;
        }

        /* Display Cards */
        .cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 2em;
            margin: 2em 5%;
        }

        .cards img {
            width: 100%;
            height: 18em;
            object-fit: cover;
            border-radius: 5px;
        }

        .text {
            text-align: center;
            margin-top: 0.5em;
        }

        /* Footer */
       .about-contact-section {
      display: flex;
      justify-content: space-between;
      gap: 40px;
      flex-wrap: wrap;
      background-color: #f9f9f9;
      padding: 40px 20px;
      border-radius: 10px;
    }

    .about-section,
    .contact-info {
      flex: 1;
      min-width: 300px;
    }

    .about-section h2,
    .contact-info h2 {
      color: #0077b6;
      font-size: 1.6rem;
      margin-bottom: 15px;
      text-align: center;
    }

    .about-section p,
    .contact-info p {
      font-size: 0.95rem;
      color: #333;
      line-height: 1.6;
      text-align: justify;
    }

    .contact-info a {
      color: #0077b6;
      text-decoration: none;
      font-weight: bold;
    }

    .contact-info a:hover {
      text-decoration: underline;
    }

    .social-icons {
      margin-top: 20px;
      display: flex;
      justify-content: center;
      gap: 15px;
    }

    .social-icons img {
      width: 30px;
      height: 30px;
      transition: transform 0.3s ease;
    }

    .social-icons a:hover img {
      transform: scale(1.2);
    }

    footer {
      text-align: center;
      padding: 20px;
      background: #333;
      color: white;
      margin-top: 40px;
    }

    footer p {
      font-size: 0.9rem;
    }

    @media (max-width: 768px) {
      .service {
        width: 100%;
      }

      .about-contact-section {
        flex-direction: column;
      }

      .about-section h2,
      .contact-info h2 {
        text-align: center;
      }

      .contact-info {
        text-align: center;
      }
    }
    .vision-mission {
            text-align: center;
            margin-top: 50px;
        }

        .vision-mission h2 {
            color: #0077b6; /* Google Blue */
            font-size: 24px;
            margin-bottom: 15px;
        }

        .vision-mission p {
            color: #0e0e0e;
            font-size: 18px;
            line-height: 1.5;
            max-width: 600px;
            margin: 0 auto;
        }
    </style>
</head>

<body>

    <header>
        <div style="font-family: 'Montserrat', sans-serif; font-weight: 700; font-size: 32px; color: #11d53b; display: inline-flex; align-items: center; gap: 8px;">
  Life Line
  <svg width="40" height="24" viewBox="0 0 40 24" fill="none" xmlns="http://www.w3.org/2000/svg">
    <polyline points="0,12 8,12 12,4 16,20 20,12 28,12 32,16 36,8 40,12" stroke="#f85508" stroke-width="2" fill="none" />
  </svg>
  Hospital
</div>

        <nav>
            <a href="#">Home</a>
            <a href="/ourServices">Our Services</a>
            
             <a href="/chatbot">Help</a>
              <a href="/visitingDoctors">Visiting Doctors</a>
            <a href="/contactUs">Contact Us</a>
            <a href="/logPage"><button>Make an appointment</button></a>
        </nav>
    </header>

    <main>
        <!-- Hero Section -->
        <section class="heading">
            <div class="inner">
                <p id="dynamicSubText" class="wow fadeInUp">Let's make life happier</p>
                <h1 id="dynamicText" class="wow fadeInUp">Hello World</h1>
                <div id="radioButtons" class="radio-container">
                    <span class="radio-btn active"></span>
                    <span class="radio-btn"></span>
                    <span class="radio-btn"></span>
                </div>
            </div>
        </section>

        <!-- CTA Section -->
        <section class="cta">
            <div>
                <h3>Welcome to your health center</h3>
                <p>Lifeline Hospital is a 102 BEDDED multispecialty Hospital located at Kesnand Phata, Wagholi. It is a state of art structure with all types of medical services at affordable charges. We are providing medical services since the year 2008 in Wagholi. We provide cashless facility to our mediclaim patients. We are committed to provide quality healthcare service. Constant improvement and innovative approach is our priority.</p>
                <div class="vision-mission">
        <h2>Our Vision</h2>
        <p>To provide safe, reliable, high-quality healthcare service for every patient, every day.</p>

        <h2>Our Mission</h2>
        <p>To provide competent, innovative, and accessible emergency healthcare services for the community.</p>
    </div>
            </div>
            <img src="https://cdn.pixabay.com/photo/2015/02/26/15/40/doctor-650534_1280.jpg" alt="Doctor">
        </section>

        <!-- Cards Section -->
        <div class="cards">
            <div>
                <img src="https://t4.ftcdn.net/jpg/02/69/98/99/360_F_269989951_9Gf7PWaRtrpm2EochO3D5WVn22sFZbNZ.jpg">
                <div class="text">
                    <h3>Amit Agrawal</h3>
                    <p>President</p>
                    
                </div>
            </div>
            <div>
                <img src="https://cdn.pixabay.com/photo/2017/01/29/21/16/nurse-2019420_1280.jpg">
                <div class="text">
                    <h3>Nate Baston</h3>
                    <p>General Principal</p>
                </div>
            </div>
        </div>
    </main>

    <section class="about-contact-section">
  <div class="about-section">
    <h2>About Us</h2>
    <p>
      Life Line Hospital is a 31-bed multi-speciality hospital offering super-speciality care. We are equipped with a dedicated team of medical professionals who provide world-class medical services. Our hospital is supported with advanced diagnostic facilities such as Digital X-Ray, ECG, Ventilators, and Centralized Oxygen facilities in the ICU, ensuring speedy and healthy recovery. Our surgeries are conducted by top-tier specialists in their fields.
    </p>
  </div>

  <div class="contact-info">
    <h2>Our Address</h2>
    <p>Sector No-1, Near Hotel Haveli,</p>
    <p>Solapur-Miraj  Highway, Sangola, Solapur, (Maharashtra) - 413304</p>
    <p>
  <img src="https://cdn-icons-png.flaticon.com/512/724/724664.png" alt="Phone Icon" style="width: 20px; vertical-align: middle; margin-right: 8px;">
  Phone: <a href="tel:+912027230021">020-27230021</a>
</p>
<p>
  <img src="https://cdn-icons-png.flaticon.com/512/732/732200.png" alt="Email Icon" style="width: 20px; vertical-align: middle; margin-right: 8px;">
  Email: <a href="mailto:contact@lifelinehospital.in">contact@lifelinehospital.in</a>
</p>

    <div class="social-icons">
      <a href="https://www.facebook.com" target="_blank">
        <img src="https://cdn-icons-png.flaticon.com/512/733/733547.png" alt="Facebook"/>
      </a>
      <a href="https://www.instagram.com" target="_blank">
        <img src="https://cdn-icons-png.flaticon.com/512/2111/2111463.png" alt="Instagram"/>
      </a>
      <a href="https://www.twitter.com" target="_blank">
        <img src="https://cdn-icons-png.flaticon.com/512/733/733579.png" alt="Twitter"/>
      </a>
      <a href="https://www.google.com" target="_blank">
        <img src="https://cdn-icons-png.flaticon.com/512/300/300221.png" alt="Google"/>
      </a>
    </div>
  </div>
</section>

<footer>
  <p>&copy; 2025 Life Line Hospital. All Rights Reserved.</p>
</footer>

    <script>
        new WOW().init();
        const headings = ["Multispeciality Hospital", "Best Orthopedic Treatments", "Intensive Care Unit (ICU) Services"];
        const subHeadings = ["Expert in Cardiology", "Specialist in Neurology", "Master of Orthopedics"];
        let index = 0;
        const radioButtons = document.querySelectorAll(".radio-btn");

        setInterval(() => {
            document.getElementById("dynamicText").innerText = headings[index];
            document.getElementById("dynamicSubText").innerText = subHeadings[index];
            radioButtons.forEach((btn, idx) => btn.classList.toggle("active", idx === index));
            index = (index + 1) % headings.length;
        }, 3000);
    </script>
</body>

</html>
