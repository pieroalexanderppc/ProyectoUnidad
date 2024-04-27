
<%-- Comprueba si el usuario ha iniciado sesión --%>
<% if (session.getAttribute("usuarioAutenticado") == null) { %>
    <%-- Si no ha iniciado sesión, redirige a loginUsuario.jsp --%>
    <%
        response.sendRedirect("landing/loginUsuario.jsp");
    %>
<% } %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                color: white;
            }
            
        </style>
    </head>
    <body>
                   <%@ include file="header.jsp" %>
         <main>
        <div class="head-title">
          <div class="left">
            <h1>Idiomas</h1>
            <ul class="breadcrumb">
              <li>
                <a href="#">Inicio</a>
              </li>
              <i class="fas fa-chevron-right"></i>
              <li>
                <a href="#" class="active">Idiomas</a>
              </li>
            </ul>
          </div>



             <p>
                 Aprende Un nuevo idioma practicando 10 minutos al dia.
La sabiduría no tiene límites ni fecha de caducidad. Aprender es el secreto de la eterna juventud.
             </p>
             <h3>
                 QUECHUA
             </h3>
             <h3>
                 AYMARA
             </h3>
             <h3>
                 INGLES
             </h3>
          



         </section>
           
    </body>
</html>