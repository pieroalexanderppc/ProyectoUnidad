
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
    <link rel="stylesheet" href="admin/styles.css" />
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
          <a href="/idiomify/IdiomaServlet?accion=inicio&ruta=admin" class="my-nav">
            <i class="fa-solid fa-house" style="color: #ffffff;"></i>
            <span class="text">Inicio</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/IdiomaServlet?accion=listarIdiomas&ruta=admin" class="my-nav-link">
            <i class="fa-solid fa-language" style="color: #ffffff;"></i>
            <span class="text">Idiomas</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=1&ruta=admin" class="my-nav-link">
            <i>
              <img id="quechua" src="admin/images/peru.svg" alt="Peru" />
            </i>
            <span class="text">Quechua</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=2&ruta=admin" class="my-nav-link">
            <i>
              <img id="aymara" src="admin/images/bolivia.svg" alt="Bolivia" />
            </i>
            <span class="text">Aymara</span>
          </a>
        </li>
        <li>
          <a href="/idiomify/CursoServlet?accion=listarCursos&idIdioma=3&ruta=admin" class="my-nav-link">
            <i>
              <img id="ingles" src="admin/images/usa.svg" alt="Estados Unidos" />
            </i>
            <span class="text">Ingles</span>
          </a>
        </li>
        
        <!--DE AQUI PRA ABAJO PUEDES UTILIZAR PRA AGREGA MENUS-->
           <li>
          <a href="/idiomify/UsuarioServlet?accion=listarUsuarios" class="my-nav-link">
               <i class="fa-solid fa-users" style="color: #ffffff;"></i>
            <span class="text">Usuarios</span>
          </a>
        </li>
        
        
                        <li>
          <a href="/idiomify/AdministradorServlet?accion=listarAdmin" class="my-nav-link">
           <i class="fa-solid fa-users-gear" style="color: #ffffff;"></i>
            <span class="text">Administradores</span>
          </a>
        </li>
        
        <!--USUARIOS POR PAIS-->
                               <li>
          <a href="/idiomify/ConsultaServlet?accion=userforpais" class="my-nav-link">
         <i class="fa-solid fa-globe"></i>
            <span class="text">Usuarios por pais</span>
          </a>
        </li>
        
           <li>
          <a href="/idiomify/ConsultaServlet?accion=obtenerUsuariosPorGenero" class="my-nav-link">
          <i class="fa-solid fa-venus-mars"></i>
            <span class="text">Usuarios por genero</span>
          </a>
        </li>

           <li>
          <a href="/idiomify/ConsultaServlet?accion=rangoedad" class="my-nav-link">
        <i class="fa-solid fa-people-group"></i>
            <span class="text">Rango de edad</span>
          </a>
        </li>
           <li>
          <a href="/idiomify/ConsultaServlet?accion=usuariomes" class="my-nav-link">
          <i class="fa-regular fa-calendar-days"></i>
            <span class="text">Usuario mes</span>
          </a>
        </li>
                  <li>
          <a href="/idiomify/ConsultaServlet?accion=ultimosUsuarios" class="my-nav-link">
              <i class="fa-solid fa-users-between-lines"></i>
          
            <span class="text"> Ultimo Usuarios</span>
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
 
   
        <div class="derecha">
                    <p>${adminAutenticado.rol}</p>

        <div class="my-profile app-perfil" id="profile-link" >
              <img id="cojudo" src="admin/${adminAutenticado.fotoPerfil}" alt="Foto de Perfil" width="200" height="200">
        </div>
            
        </div>

   <!--NO URGES ESTE CARD PORQUE ESTO LO HAGO JAVAS SCRIPT SOLO LO ATERIROR-->
        <div class="profile-card" id="profile-card">
          <div class="card-content">
            <img src="admin/${adminAutenticado.fotoPerfil}" alt="Foto de Perfil" />
            <p>${adminAutenticado.nombre} ${adminAutenticado.apellido}</p>
            <a href="admin/perfil.jsp"><i class="fa-regular fa-user" style="color: #ffffff;"></i> Tu Perfil</a>
     
            <a href="/idiomify/CerrarSesionServlet"><i class="fa-solid fa-right-from-bracket" style="color: #d36022;"></i> Cerrar Sesión</a>
          </div>
        </div>
      </nav>
            <main class=".content">

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
