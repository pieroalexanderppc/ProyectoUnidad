<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Pregunta</title>
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
                            <i class="bi bi-pencil-square"></i> Agregar una Nueva Pregunta
                        </h1>
                        <form method="post" action="PreguntaServlet?accion=agregarPregunta">
                            <div class="mb-3">
                                <label for="idPregunta" class="form-label">
                                    <i class="bi bi-badge-cc"></i> ID de la Pregunta:
                                </label>
                                <input type="text" class="form-control" name="idPregunta" id="idPregunta" placeholder="Ejemplo: 1">
                            </div>
                            <div class="mb-3">
                                <label for="FKidLeccion" class="form-label">
                                    <i class="bi bi-journal-plus"></i> ID de la Lección:
                                </label>
                                <input type="text" class="form-control" name="FKidLeccion" id="FKidLeccion" placeholder="Ejemplo: 1">
                            </div>
                            <div class="mb-3">
                                <label for="enunciado" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Enunciado:
                                </label>
                                <textarea class="form-control" name="enunciado" id="enunciado" placeholder="Ejemplo: Pregunta 1">
                                </textarea>
                            </div>
                            <div class="mb-3">
                                <label for="actividad" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Actividad:
                                </label>
                                <textarea class="form-control" name="actividad" id="actividad" placeholder="Ejemplo: Actividad 1">
                                </textarea>
                            </div>
                            <div class="mb-3">
                                <label for="respuesta" class="form-label">
                                    <i class="bi bi-chat-right-dots"></i> Respuesta:
                                </label>
                                <input type="text" class="form-control" name="respuesta" id="respuesta" placeholder="Ejemplo: Respuesta 1">
                            </div>
                            <div class="mb-3">
                                <label for="urlIlustracion" class="form-label">
                                    <i class="bi bi-image"></i> URL Ilustración:
                                </label>
                                <input type="text" class="form-control" name="urlIlustracion" id="urlIlustracion" placeholder="Ejemplo: https://example.com/illustration.jpg">
                            </div>
                            <div class="mb-3">
                                <label for="urlAudio" class="form-label">
                                    <i class="bi bi-file-earmark-music"></i> URL Audio:
                                </label>
                                <input type="text" class="form-control" name="urlAudio" id="urlAudio" placeholder="Ejemplo: https://example.com/audio.mp3">
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
