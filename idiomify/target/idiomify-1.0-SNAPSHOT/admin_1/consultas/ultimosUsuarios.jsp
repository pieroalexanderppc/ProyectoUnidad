<%@page import="ModeloDAO.ClsModeloDaoConsultas"%>
<%@page import="Modelo.ClsModeloUsuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Últimos Usuarios Registrados</title>
    <style>
        .card {
            width: 90%;
            margin: auto;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            color: #cacaca;
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
    <%@ include file="../header.jsp" %>
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
                    ClsModeloDaoConsultas consultasDao = new ClsModeloDaoConsultas();
                    List<ClsModeloUsuario> ultimosUsuarios = consultasDao.obtenerUltimosUsuariosRegistrados(5);

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
</body>
</html>
