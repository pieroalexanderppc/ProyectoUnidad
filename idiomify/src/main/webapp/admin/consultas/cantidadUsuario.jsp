<%@page import="ModeloDAO.ClsModeloDaoConsultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cantidad Total de Usuarios</title>
    <style>
        .card {
            width: 400px;
            margin: 20px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            background-color: #fff;
        }
    </style>
</head>
<body>
    <div class="card">
        <h1>Cantidad Total de Usuarios</h1>

        <% 
            ClsModeloDaoConsultas consultasDao = new ClsModeloDaoConsultas();
            int cantidadTotalUsuarios = consultasDao.obtenerCantidadTotalUsuarios();
        %>

        <p>Total de Usuarios: <%= cantidadTotalUsuarios %></p>
    </div>
</body>
</html>
