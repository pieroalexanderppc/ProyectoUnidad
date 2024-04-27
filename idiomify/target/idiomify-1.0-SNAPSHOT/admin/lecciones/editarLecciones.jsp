<%--
Document   : editarLecciones
Created on : Nov 2, 2023, 2:36:19 AM
Author     : LENOVO
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Modelo.ClsModeloLeccion" %>
<%@ page import="ModeloDAO.ClsModeloDaoLeccion" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Lección</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        .custom-title {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            text-align: center;
            border-radius: 5px;
            font-size: 24px;
        }
    </style>
</head>
<body>
     <%@ include file="../header.jsp" %>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h1 class="custom-title">
                            <i class="bi bi-pencil-square"></i> Editar Lección
                        </h1>
                        <%
                            String idLeccion = request.getParameter("idLeccion");
                            ClsModeloDaoLeccion dao = new ClsModeloDaoLeccion();
                            ClsModeloLeccion leccion = dao.buscarPorIdLeccion(idLeccion);

                            if (leccion != null) { %>
                                <form method="post" action="/idiomify/LeccionServlet?accion=actualizarLeccion">
                                    <input type="hidden" name="idLeccion" value="<%= leccion.getIdLeccion() %>">
                                    <input type="hidden" name="FKidCurso" value="<%= leccion.getFKidCurso() %>">
                                    <input type="hidden" name="FKidIdioma" value="<%= leccion.getFKidIdioma() %>">
                                    <div class="mb-3">
                                        <label for="titulo" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Título:
                                        </label>
                                        <input type="text" class="form-control" name="titulo" value="<%= leccion.getTitulo() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="urlBanner" class="form-label">
                                            <i class="bi bi-link-45deg"></i> URL Banner:
                                        </label>
                                        <input type="text" class="form-control" name="urlBanner" value="<%= leccion.getUrlBanner() %>">
                                    </div>
                                    <div class="d-flex justify-content-center">
                                        <button type="submit" class="btn btn-primary w-100">
                                            <i class="bi bi-check"></i> Guardar cambios
                                        </button>
                                    </div>
                                </form>
                            <%
                            } else { %>
                                <p>La lección no se encontró.</p>
                            <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
