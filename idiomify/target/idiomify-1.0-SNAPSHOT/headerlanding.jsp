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
  <title>Idiomify</title>
  <meta name="title" content="Idiomify">
  <meta name="description" content="Este es un template de educación creado por codewithsadee">

  <link rel="shortcut icon" href="/assets/images/logo_icon.svg" type="image/svg+xml">
  <link rel="stylesheet" href="./assets/css/style.css">

  <!-- 
    - Enlace a la fuente de Google
  -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=League+Spartan:wght@400;500;600;700;800&family=Poppins:wght@400;500&display=swap"
    rel="stylesheet">

  <!-- 
    - Precargar imágenes
  -->
  <link rel="preload" as="image" href="./assets/images/hero-bg.svg">
  <link rel="preload" as="image" href="./assets/images/hero-banner-1.jpg">
  <link rel="preload" as="image" href="./assets/images/hero-banner-2.jpg">
  <link rel="preload" as="image" href="./assets/images/hero-shape-1.svg">
  <link rel="preload" as="image" href="./assets/images/hero-shape-2.png">

</head>

<body id="top">

  <!-- 
    - #CABECERA
  -->

  <header class="header" data-header>
    <div class="container">

      <a href="#" class="logo">
        <img src="./assets/images/logo.png" width="180px"  alt="Logo de Idiomify">
      </a>

      <nav class="navbar" data-navbar>

        <div class="wrapper">
          <a href="/idiomify" class="logo">
            <img src="./assets/images/logo.png" width="180px" alt="Logo de Ifiomify">
          </a>

          <button class="nav-close-btn" aria-label="cerrar menú" data-nav-toggler>
            <ion-icon name="close-outline" aria-hidden="true"></ion-icon>
          </button>
        </div>

        <ul class="navbar-list">

          <li class="navbar-item">
            <a href="/idiomify" class="navbar-link" data-nav-link>Inicio</a>
          </li>

          <li class "navbar-item">
            <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=1&ruta=landing" class="navbar-link" data-nav-link>Quechua</a>
          </li>

          <li class="navbar-item">
            <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=2&ruta=landing" class="navbar-link" data-nav-link>Aymara</a>
          </li>

          <li class="navbar-item">
            <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=3&ruta=landing" class="navbar-link" data-nav-link>Ingles</a>
          </li>

          <li class="navbar-item">
            <a href="#" class="navbar-link" data-nav-link>Soporte</a>
          </li>

        </ul>

      </nav>

      <div class="header-actions">

        <!-- <button class="header-action-btn" aria-label="abrir búsqueda" title="Búsqueda">
          <ion-icon name="search-outline" aria-hidden="true"></ion-icon>
        </button> -->

        <!-- <button class="header-action-btn" aria-label="carrito" title="Carrito">
          <ion-icon name="cart-outline" aria-hidden="true"></ion-icon>

          <span class="btn-badge">0</span>
        </button> -->

        <a href="landing/loginUsuario.jsp" class="btn has-before">
          <span class="span">Iniciar Gratis</span>

        </a>

        <button class="header-action-btn" aria-label="abrir menú" data-nav-toggler>
          <ion-icon name="menu-outline" aria-hidden="true"></ion-icon>
        </button>

      </div>

      <div class="overlay" data-nav-toggler data-overlay></div>

    </div>
  </header>
  



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

