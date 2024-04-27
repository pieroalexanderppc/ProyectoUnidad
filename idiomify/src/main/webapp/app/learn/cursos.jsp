<%@page import="java.util.List"%>
<%@page import="Modelo.ClsModeloCurso"%>
<%@page import="ModeloDAO.ClsModeloDaoCurso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Cursos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body.dark-mode {
            background-color: #1a1a1a;
            color: #ffffff;
        }

        .card.dark-mode {
            background-color: #2c2c2c;
            border: 1px solid #444;
            color: #ffffff;
        }

        .card.dark-mode .card-title {
            color: #ffffff;
        }

        .card.dark-mode .card-text {
            color: #d3d3d3;
        }
    </style>
</head>
<body >
    <%@ include file="../header.jsp" %>

    <h1 class="text-center">Listado de Cursos</h1>
    <div class="container">
        <div class="row">
            <%
                String idIdiomaStr = request.getParameter("idIdioma");
                int idIdioma = Integer.parseInt(idIdiomaStr);
                ClsModeloDaoCurso dao = new ClsModeloDaoCurso();
                List<ClsModeloCurso> cursos = dao.listarCursosPorIdIdioma(idIdioma);
                for (ClsModeloCurso curso : cursos) {
            %>
            <div class="col-md-4">
                <div class="card dark-mode">
                    <a href="LeccionServlet?accion=listarLecciones&idCurso=<%= curso.getIdCurso()%>&ruta=app">
                        <img src="<%= curso.getUrlBanner() %>" class="card-img-top" alt="Banner">
                        <div class="card-body">
                            <h5 class="card-title"><%= curso.getNombre() %></h5>
                            <p class="card-text"><%= curso.getDescripcion() %></p>
                        </div>
                    </a>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
