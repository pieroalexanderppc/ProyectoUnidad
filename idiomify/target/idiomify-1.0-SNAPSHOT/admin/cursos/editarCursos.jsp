<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Modelo.ClsModeloCurso" %>
<%@ page import="ModeloDAO.ClsModeloDaoCurso" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Curso</title>
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
                            <i class="bi bi-pencil-square"></i> Editar Curso
                        </h1>
                        <%
                            String idCurso = request.getParameter("idCurso");
                            ClsModeloDaoCurso dao = new ClsModeloDaoCurso();
                            ClsModeloCurso curso = dao.buscarPorIdCurso(idCurso);

                            if (curso != null) { %>
                                <form method="post" action="/idiomify/CursoServlet?accion=actualizarCurso">
                                    <input type="hidden" name="idCurso" value="<%= curso.getIdCurso() %>">
                                    <input type="hidden" name="FKidIdioma" value="<%= curso.getFKidIdioma()%>">
                                    <div class="mb-3">
                                        <label for="nombre" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Nombre:
                                        </label>
                                        <input type="text" class="form-control" name="nombre" value="<%= curso.getNombre() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="descripcion" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Descripción:
                                        </label>
                                        <textarea class="form-control" name="descripcion"><%= curso.getDescripcion() %></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="urlBanner" class="form-label">
                                            <i class="bi bi-link-45deg"></i> URL Banner:
                                        </label>
                                        <input type="text" class="form-control" name="urlBanner" value="<%= curso.getUrlBanner() %>">
                                    </div>
                                    <div class="d-flex justify-content-center">
                                        <button type="submit" class="btn btn-primary w-100">
                                            <i class="bi bi-check"></i> Guardar cambios
                                        </button>
                                    </div>
                                </form>
                            <%
                            } else { %>
                                <p>El curso no se encontró.</p>
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
