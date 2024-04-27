<%@page import="java.util.logging.Logger"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="Modelo.ClsModeloUsuario"%>
<%@page import="Modelo.ClsModeloHistorialUsuario"%>
<%@page import="ModeloDAO.ClsModeloDaoHistorialUsuario"%>
<%@page import="Modelo.ClsModeloPregunta"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ClsModeloDaoPregunta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>



<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="app/learn/LeccionIdiomify/premium.css">
    <title>Premium</title>
  </head>
  <body>
        <%
            ClsModeloUsuario usuarioAutenticado = (ClsModeloUsuario) session.getAttribute("usuarioAutenticado");
            
            String idLeccionStr = request.getParameter("idLeccion");
            ClsModeloHistorialUsuario historialUsuario = new ClsModeloHistorialUsuario();
            historialUsuario.setFKidiLeccion(idLeccionStr);
            historialUsuario.setFKidUsuario(usuarioAutenticado.getIdUsuario());
            ClsModeloDaoHistorialUsuario historialDAO= new ClsModeloDaoHistorialUsuario();
                
            historialDAO.agregarHistorial(historialUsuario);

            ClsModeloDaoPregunta dao = new ClsModeloDaoPregunta();
            List<ClsModeloPregunta> preguntas = dao.listarPreguntasPorLeccion(idLeccionStr);

            // Convertir la lista de preguntas a formato JSON manualmente
            StringBuilder preguntasJSON = new StringBuilder("[");
            for (ClsModeloPregunta pregunta : preguntas) {
                preguntasJSON.append("{");
                preguntasJSON.append("\"idPregunta\":").append(pregunta.getIdPregunta()).append(",");
                preguntasJSON.append("\"FKidLeccion\":\"").append(pregunta.getFKidLeccion()).append("\",");
                preguntasJSON.append("\"enunciado\":\"").append(pregunta.getEnunciado()).append("\",");
                preguntasJSON.append("\"actividad\":\"").append(pregunta.getActividad()).append("\",");
                preguntasJSON.append("\"respuesta\":\"").append(pregunta.getRespuesta()).append("\",");
                preguntasJSON.append("\"urlIlustracion\":\"").append(pregunta.getUrlIlustracion()).append("\",");
                preguntasJSON.append("\"urlAudio\":\"").append(pregunta.getUrlAudio()).append("\"");
                preguntasJSON.append("},");
            }
            if (!preguntas.isEmpty()) {
                preguntasJSON.deleteCharAt(preguntasJSON.length() - 1); // Eliminar la última coma
            }
            preguntasJSON.append("]");
        %>

    <header class="menu_header">
      <div class="salir_icon_content">
        <img src="app/learn/LeccionIdiomify/imagenes/x.svg" alt="" class="icon_salir" id="salir">
      </div>
      <div class="barra">
        <div class="progress-bar">
          <div class="progress"></div>
        </div>
        <div class="vida">
          <img src="app/learn/LeccionIdiomify/imagenes/vida.svg" alt="" class="corazon">
          <div class="corazon_number"></div>
        </div>
      </div>
    </header>

    <main class="main">
      <div class="main_block objetivo">
        <p id="parrafo_objetivo"></p>
      </div>
      <div class="main_block tarea">
        <h1 class="title"></h1>
      </div>
      <div class="main_block help_traduction">
      </div>
      <div class="main_block prompt_text">
        <div class="image_leccion">
        </div>
        <textarea rows="1" id="bol"  placeholder="Escribe aqui en Quechua..."></textarea>
        <!-- <input type="text" id="bol"> -->
      </div>
      <div class="main_block output_error">
        <p id="error_prompt"></p>
      </div>
      <div class="main_block prompt_voz">
        <p id="transcription"></p>
      </div>
      <div class="main_block microfonito">
        <img src="app/learn/LeccionIdiomify/imagenes/volu2.svg" alt="" class="volumen">
        <div class="microfono_animation" id="start-btn">
          <img src="app/learn/LeccionIdiomify/imagenes/micro2.svg" alt="" class="logo">
        </div>
        <img src="app/learn/LeccionIdiomify/imagenes/oido2.svg" alt="" class="volumenLento">
      </div>
    </main>
    <!-- Contido que se muestra cuando hca click en SALIR -->
    <div class="salir_content inactive">
      <p>¿Desea abandonar la leccion de ingles?</p>
      <button class="btn_abandonar">Abandonar</button>
      <button class="btn_continuar">Continuar</button>
    </div>

    <!-- ESTE BOTN ES SUELTO PARA VERIFICAR -->
    <footer class="contentBoton_verificar">
      <button class="btn_verificar" >Verificar</button>
    </footer>

    <footer class="contentBoton_continuar inactive">
      <p class="error"></p>
      <p class="solutions"></p>
      <button class="btn_continuar_prompt" >Continuar</button>
    </footer>
    <div class="finish_container inactive">
      <div class="anuncio">
        <img src="app/learn/LeccionIdiomify/bannersimages/eco.jpg" alt="">
      </div>
      <div class="logro">eres un genio</div>
    </div>
     <div id="preguntasYRespuestas"></div>

<script>
    var preguntasYRespuestasJSON = <%= preguntasJSON.toString() %>;
    var preguntasYRespuestasBidimensional = preguntasYRespuestasJSON.map(objeto => Object.values(objeto));
     var preguntasYRespuestasJSONString = JSON.stringify(preguntasYRespuestasBidimensional);
         // Agrega un atributo data- al elemento que contiene el array JSON
    document.getElementById("preguntasYRespuestas").setAttribute("data-preguntas", preguntasYRespuestasJSONString);
    console.log("TODO ESCELENTE POR AQUI DESDE APPIDIOMIFY");

</script>

    
    <!-- Agrega un atributo data- al elemento que contiene el array JSON -->
   
    <script src="app/learn/LeccionIdiomify/reconocimientoVoz.js"></script>
    <script type="module" src="app/learn/LeccionIdiomify/ContenidoEspañolIngles/app.js"></script>
    <!-- <script type="module" src="./premium.js"></script> -->
  </body>
</html>
