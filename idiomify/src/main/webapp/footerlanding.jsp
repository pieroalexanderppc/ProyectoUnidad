<%-- 
    Document   : footerlanding
    Created on : Nov 7, 2023, 4:43:42 AM
    Author     : LENOVO
--%>

<%-- 
    Document   : headerlanding
    Created on : Nov 7, 2023, 4:43:10 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body id="top">
    <!--<body id="top">-->


  <!-- 
    - #FOOTER
  -->

  <footer class="footer" style="background-image: url('./assets/images/footer-bg.png')">

    <div class="footer-top section">
      <div class="container grid-list">

        <div class="footer-brand">

          <a href="/idiomify" class="logo">
            <img src="./assets/images/logo.png" width="162" height="50" alt="Ifiomify logo">
          </a>

          <p class="footer-brand-text">
            Somos una plataforma que te ayuda a aprender un nuevo idioma para tener mas oportuindades laborales.
          </p>

          <div class="wrapper">
            <span class="span">Ubicacion:</span>

            <address class="address">Peru - Tacna</address>
          </div>

     

          <div class="wrapper">
            <span class="span">Email:</span>

            <a href="mailto:idiomifyinfo@gmail.com" class="footer-link">idiomifyinfo@gmail.com</a>
          </div>

        </div>

        <ul class="footer-list">

          <li>
            <p class="footer-list-title">Plataforma Idiomify</p>
          </li>

          <li>
            <a href="/idiomify" class="footer-link">Inicio</a>
          </li>

          <li>
            <a href="#" class="footer-link">Idiomas</a>
          </li>

          <li>
            <a href="#" class="footer-link">Quechua</a>
          </li>

          <li>
            <a href="#" class="footer-link">Aymara</a>
          </li>

          <li>
            <a href="#" class="footer-link">Ingles</a>
          </li>

          <li>
            <a href="#" class="footer-link">Soporte</a>
          </li>

        </ul>

        <ul class="footer-list">

          <li>
            <p class="footer-list-title">Enlaces</p>
          </li>

       

          <li>
            <a href="landing/loginadmin.jsp" class="footer-link">Iniciar Sesion como administrador</a>
          </li>

          <li>
            <a href="#" class="footer-link">Iniciar Sesion</a>
          </li>

          <li>
            <a href="app/index.jsp" class="footer-link">Crear Cuenta</a>
          </li>

        </ul>

        <div class="footer-list">

          <p class="footer-list-title">Soporte</p>

          <p class="footer-list-text">
            Ingresa tu Correo electronico para que recibas contenido adicional.
          </p>

          <form action="" class="newsletter-form">
            <input type="email" name="email_address" placeholder="Ingresa tu email" required class="input-field">

            <button type="submit" class="btn has-before">
              <span class="span">Suscribirse</span>

            </button>
          </form>

          <ul class="social-list">

            <li>
              <a href="#" class="social-link">
                <ion-icon name="logo-facebook"></ion-icon>
              </a>
            </li>

            <li>
              <a href="#" class="social-link">
                <ion-icon name="logo-linkedin"></ion-icon>
              </a>
            </li>

            <li>
              <a href="#" class="social-link">
                <ion-icon name="logo-instagram"></ion-icon>
              </a>
            </li>

            <li>
              <a href="#" class="social-link">
                <ion-icon name="logo-twitter"></ion-icon>
              </a>
            </li>

            <li>
              <a href="#" class="social-link">
                <ion-icon name="logo-youtube"></ion-icon>
              </a>
            </li>

          </ul>

        </div>

      </div>
    </div>

    <div class="footer-bottom">
      <div class="container">

        <p class="copyright">
          Copyright 2023 Todo los derechos reservados por <a href="#" class="copyright-link">Idiomify</a>
        </p>

      </div>
    </div>

  </footer>





  <!-- 
    - #BACK TO TOP
  -->

  <a href="#top" class="back-top-btn" aria-label="back top top" data-back-top-btn>
    <ion-icon name="chevron-up" aria-hidden="true"></ion-icon>
  </a>





  <!-- 
    - custom js link
  -->
  <script src="./assets/js/script.js" defer></script>
  <script src="https://unpkg.com/typed.js@2.0.16/dist/typed.umd.js"></script>
  
  <!-- 
    - ionicon link
  -->
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

</body>

</html>

