<%-- 
    Document: lecciones
    Created on: Nov 2, 2023, 3:01:38 PM
    Author: LENOVO
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.ClsModeloLeccion"%>
<%@page import="ModeloDAO.ClsModeloDaoLeccion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Lecciones</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        .card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .card {
            width: 210px;
        }
    </style>
</head>
<body>
         <%@ include file="../header.jsp" %>
   
        <h1 class="text-center">Listado de Lecciones</h1>
        
        <div class="card-container">
            <%
                String idCursoStr = request.getParameter("idCurso");
                ClsModeloDaoLeccion dao = new ClsModeloDaoLeccion();
                List<ClsModeloLeccion> lecciones = dao.listarLeccionesPorCurso(idCursoStr);
                for (ClsModeloLeccion leccion : lecciones) {
            %>
            <div class="card">
                <a href="PreguntaServlet?accion=listarPreguntas&idLeccion=<%= leccion.getIdLeccion() %>&ruta=app">
                    <img src="<%= leccion.getUrlBanner() %>" class="card-img-top" alt="Banner">
                    <div class="card-body">
                        <h5 class="card-title"><%= leccion.getTitulo() %></h5>
                    </div>
                </a>
            </div>
            <%
                }
            %>
        </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
