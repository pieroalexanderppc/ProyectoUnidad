<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Lección</title>
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
                            <i class="bi bi-journal-plus"></i> Agregar una Nueva Lección
                        </h1>
                        <form method="post" action="LeccionServlet?accion=agregarLecciones">
                            <div class="mb-3">
                                <label for="idLeccion" class="form-label">
                                    <i class="bi bi-badge-cc"></i> ID de la Lección:
                                </label>
                                <input type="text" class="form-control" name="idLeccion" id="idLeccion" placeholder="Ejemplo: 1">
                            </div>
                            <div class="mb-3">
                                <label for="FKidCurso" class="form-label">
                                    <i class="bi bi-book"></i> ID del Curso:
                                </label>
                                <input type="text" class="form-control" name="FKidCurso" id="FKidCurso" placeholder="Ejemplo: 1">
                            </div>
                            <div class="mb-3">
                                <label for="FKidIdioma" class="form-label">
                                    <i class="bi bi-translate"></i> ID del Idioma:
                                </label>
                                <input type="text" class="form-control" name="FKidIdioma" id="FKidIdioma" placeholder="Ejemplo: 1">
                            </div>
                            <div class="mb-3">
                                <label for="titulo" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Título:
                                </label>
                                <input type="text" class="form-control" name="titulo" id="titulo" placeholder="Ejemplo: Lección 1">
                            </div>
                            <div class="mb-3">
                                <label for="urlBanner" class="form-label">
                                    <i class="bi bi-image"></i> URL del Banner:
                                </label>
                                <input type="text" class="form-control" name="urlBanner" id="urlBanner" placeholder="Ejemplo: https://example.com/banner.jpg">
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary w-100">
                                    <i class="bi bi-check-circle"></i> Enviar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
