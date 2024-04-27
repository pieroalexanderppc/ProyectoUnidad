
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Idiomify</title>

    <!-- Font Awesome -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    />
    <style>

  /* Estilos para el card */
  .profile-card {
    display: none;
    position: absolute;
    top: 60px; /* Ajusta esta posición según tus necesidades */
    right: -75px;
    transform: translateX(-50%);
    width: 200px;
    background-color: #0b0b13;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    padding: 16px;
    border-radius: 5px;
    z-index: 1000;
    cursor: pointer;

  }
  #profile-link{
      cursor: pointer;
  }

  .profile-card .card-content {
    text-align: center;
  }

  .profile-card img {
    width: 60px;
    height: 60px;
   
    border-radius: 50%;
  }

  .profile-card p {
    margin: 10px 0;
    font-weight: bold;
  }

  .profile-card a {
    display: block;
    margin: 15px 10px;
    text-decoration: none;
    color: black;
    text-align: left;
    
  }
</style>


    <!-- CSS personalizado -->
    <link rel="stylesheet" href="app/styles.css" />
  </head>
  <body class="dark">
    <section class="sidebar">
      <a href="#" class="logo">
        <i>
          <img id="logo" src="app/images/logo.svg" alt="logo pe" />
        </i>
        <span class="text">Idiomify</span>
      </a>

      <ul class="side-menu top">
        <li class="active">
          <a href="/idiomify/IdiomaServlet?accion=inicio&ruta=app" class="my-nav">
            <i class="fa-solid fa-house" style="color: #ffffff;"></i>
            <span class="text">Inicio</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/IdiomaServlet?accion=listarIdiomas&ruta=app" class="my-nav-link">
            <i class="fa-solid fa-language" style="color: #ffffff;"></i>
            <span class="text">Idiomas</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=1&ruta=app" class="my-nav-link">
            <i>
              <img id="quechua" src="app/images/peru.svg" alt="Peru" />
            </i>
            <span class="text">Quechua</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=2&ruta=app" class="my-nav-link">
            <i>
              <img id="aymara" src="app/images/bolivia.svg" alt="Bolivia" />
            </i>
            <span class="text">Aymara</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=3&ruta=app" class="my-nav-link">
            <i>
              <img id="ingles" src="app/images/usa.svg" alt="Estados Unidos" />
            </i>
            <span class="text">Ingles</span>
          </a>
        </li>
      </ul>

      <ul class="side-menu">
   
        <li>
          <a href="/idiomify/CerrarSesionServlet" class="logout">
            <i class="fas fa-right-from-bracket"></i>
            <span class="text">Cerrar Sesion</span>
          </a>
        </li>
      </ul>
    </section>

    <section class="content">
      <nav>
        <i class="fas fa-bars my-menu-btn"></i>
   
    



        <div class="my-profile app-perfil" id="profile-link" >
          <c:if test="${usuarioAutenticado != null}">
              <img id="cojudo" src="app/${usuarioAutenticado.fotoPerfil}" alt="Foto de Perfil" width="200" height="200" />
          </c:if>
        </div>
        <div class="profile-card" id="profile-card">
          <div class="card-content">
            <img src="app/${usuarioAutenticado.fotoPerfil}" alt="Foto de Perfil" />
            <p>${usuarioAutenticado.nombre} ${usuarioAutenticado.apellido}</p>
            <a href="app/perfil.jsp"><i class="fa-regular fa-user" style="color: #ffffff;"></i> Tu Perfil</a>
            <a href="ProgresoServlet?accion=listarmiscuros"><i class="fa-solid fa-chart-column" style="color: #ffffff;"></i> Tu cursos</a>
            <a href="/idiomify/CerrarSesionServlet"><i class="fa-solid fa-right-from-bracket" style="color: #d36022;"></i> Cerrar Sesión</a>
          </div>
        </div>
      </nav>

      <script src="app.js"></script>
<script>
  // Obtén los elementos HTML
  const profileLink = document.getElementById("cojudo")
  const profileCard = document.getElementById("profile-card");

  // Agrega un evento de clic para mostrar u ocultar el card
  profileLink.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("ya pe causa");
    if (profileCard.style.display === "block") {
      // Si el card ya está visible, ocúltalo
      profileCard.style.display = "none";
    } else {
      // Si el card está oculto, muéstralo
      profileCard.style.display = "block";
    }
  });

  // Agrega un evento de clic para ocultar el card al hacer clic fuera de él
  document.addEventListener("click", function (event) {
    if (event.target !== profileLink && event.target !== profileCard) {
      profileCard.style.display = "none";
    }
  });
</script>


  </body>
</html>
