
<%@page import="Modelo.ClsModeloUsuario"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ClsModeloDaoConsultas"%>
<%-- Comprueba si el usuario ha iniciado sesión --%>
<% if (session.getAttribute("adminAutenticado") == null) { %>
    <%-- Si no ha iniciado sesión, redirige a loginUsuario.jsp --%>
    <%
        response.sendRedirect("landing/loginadmin.jsp");
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
                table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: black;
        }
    </style>
    </head>
    <body>
                   <%@ include file="header.jsp" %>
         <main>
                    <% 
            ClsModeloDaoConsultas consultasDao = new ClsModeloDaoConsultas();
            int cantidadTotalUsuarios = consultasDao.obtenerCantidadTotalUsuarios();
        %>

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

        </div>

        <div class="box-info">
          <li>
           <i class="fa-solid fa-users fa-beat-fade"></i>                 
            <span class="text">
              <h3><%= cantidadTotalUsuarios %></h3>
              <p>Cantidad de estudiantes</p>
            </span>
          </li>
<!--          <li>
            <i class="fa-solid fa-calendar-days" style="color: #6db135;"></i>            
            <span class="text">
              <h3>8</h3>
              <p>Dias de racha</p>
            </span>
          </li>
          <li>
            <i class="fa-solid fa-heart" style="color: #ff0000;"></i>            
            <span class="text">
              <h3>5</h3>
              <p>Vidas</p>
            </span>
          </li>-->
        </div>
    <div class="card">
        <h1>Últimos 5 Usuarios Registrados</h1>

        <table>
            <thead>
                <tr>
              
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Fecha Nacimiento</th>
                    <th>Género</th>
                    <th>País</th>
                    <th>Ciudad</th>
                    <th>Email</th>
                    <th>Fecha Registro</th>
           
                </tr>
            </thead>
            <tbody>
                <%
                    ClsModeloDaoConsultas consultasDaos = new ClsModeloDaoConsultas();
                    List<ClsModeloUsuario> ultimosUsuarios = consultasDaos.obtenerUltimosUsuariosRegistrados(5);

                    for (ClsModeloUsuario usuario : ultimosUsuarios) {
                %>
                    <tr>
      
                        <td><%= usuario.getNombre() %></td>
                        <td><%= usuario.getApellido() %></td>
                        <td><%= usuario.getFechaNacimiento() %></td>
                        <td><%= usuario.getGenero() %></td>
                        <td><%= usuario.getPais() %></td>
                        <td><%= usuario.getCiudad() %></td>
                        <td><%= usuario.getEmail() %></td>
                        <td><%= usuario.getFechaRegistro() %></td>
       
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
 


         </section>
           
    </body>
</html>