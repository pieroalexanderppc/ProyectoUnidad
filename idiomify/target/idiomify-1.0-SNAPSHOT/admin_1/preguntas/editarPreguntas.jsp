<%-- 
Document: editarPreguntas
Created on: Nov 2, 2023, 3:19:23 AM
Author: LENOVO
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Modelo.ClsModeloPregunta" %>
<%@ page import="ModeloDAO.ClsModeloDaoPregunta" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Pregunta</title>
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
                            <i class="bi bi-pencil-square"></i> Editar Pregunta
                        </h1>
                        <%
                            String idPreguntaStr = request.getParameter("idPregunta");
                            int idPregunta = Integer.parseInt(idPreguntaStr);
                            ClsModeloDaoPregunta dao = new ClsModeloDaoPregunta();
                            ClsModeloPregunta pregunta = dao.buscarPorIdPregunta(idPregunta);

                            if (pregunta != null) { %>
                                <form method="post" action="/idiomify/PreguntaServlet?accion=actualizarPregunta">
                                    <input type="hidden" name="idPregunta" value="<%= pregunta.getIdPregunta() %>">
                                    <input type="hidden" name="FKidLeccion" value="<%= pregunta.getFKidLeccion() %>">
                                    <div class="mb-3">
                                        <label for="enunciado" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Enunciado:
                                        </label>
                                        <textarea class="form-control" name="enunciado"><%= pregunta.getEnunciado() %></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="actividad" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Actividad:
                                        </label>
                                        <textarea class="form-control" name="actividad"><%= pregunta.getActividad() %></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="respuesta" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Respuesta:
                                        </label>
                                        <input type="text" class="form-control" name="respuesta" value="<%= pregunta.getRespuesta() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="urlIlustracion" class="form-label">
                                            <i class="bi bi-link-45deg"></i> URL Ilustración:
                                        </label>
                                        <input type="text" class="form-control" name="urlIlustracion" value="<%= pregunta.getUrlIlustracion() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="urlAudio" class="form-label">
                                            <i class="bi bi-link-45deg"></i> URL Audio:
                                        </label>
                                        <input type="text" class="form-control" name="urlAudio" value="<%= pregunta.getUrlAudio() %>">
                                    </div>
                                    <div class="d-flex justify-content-center">
                                        <button type="submit" class="btn btn-primary w-100">
                                            <i class="bi bi-check"></i> Guardar cambios
                                        </button>
                                    </div>
                                </form>
                            <%
                            } else { %>
                                <p>La pregunta no se encontró.</p>
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
