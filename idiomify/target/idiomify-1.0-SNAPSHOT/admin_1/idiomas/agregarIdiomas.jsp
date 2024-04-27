<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Idioma</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        .custom-title {
            background-color: #007bff; /* Color de fondo azul */
            color: #fff; /* Color del texto en blanco */
            padding: 10px;
            text-align: center;
            border-radius: 5px;
            font-size: 24px; /* Tamaño del título más pequeño */
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
                            <i class="bi bi-pencil-square"></i> Agregar un Nuevo Idioma
                        </h1>
                        <form method="post" action="IdiomaServlet?accion=agregarIdioma">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Nombre:
                                </label>
                                <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Ejemplo: Español">
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Descripción:
                                </label>
                                <textarea class="form-control" name="descripcion" id="descripcion" placeholder="Ejemplo: Idioma principal del sitio web"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="urlBanner" class="form-label">
                                    <i class="bi bi-link-45deg"></i> URL Banner:
                                </label>
                                <input type="text" class="form-control" name="urlBanner" id="urlBanner" placeholder="Ejemplo: https://example.com/banner.jpg">
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary w-100">
                                    <i class="bi bi-check"></i> Enviar
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
