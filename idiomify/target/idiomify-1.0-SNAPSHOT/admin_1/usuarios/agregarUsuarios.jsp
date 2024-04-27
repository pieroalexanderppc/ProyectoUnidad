<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Usuario</title>
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
                            <i class="bi bi-pencil-square"></i> Agregar un Nuevo Usuario
                        </h1>
                        <form method="post" action="UsuarioServlet?accion=agregarUsuario">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Nombre:
                                </label>
                                <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre">
                            </div>
                            <div class="mb-3">
                                <label for="apellido" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Apellido:
                                </label>
                                <input type="text" class="form-control" name="apellido" id="apellido" placeholder="Apellido">
                            </div>
                            <div class="mb-3">
                                <label for="fechaNacimiento" class="form-label">
                                    <i class="bi bi-calendar"></i> Fecha de Nacimiento:
                                </label>
                                <input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento">
                            </div>
                            <div class="mb-3">
                                <label for="genero" class="form-label">
                                    <i class="bi bi-gender"></i> Género:
                                </label>
                                <select class="form-select" name="genero" id="genero">
                                    <option value="Masculino">Masculino</option>
                                    <option value="Femenino">Femenino</option>
                                    <option value="Otro">Otro</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="pais" class="form-label">
                                    <i class="bi bi-flag"></i> País:
                                </label>
                                <input type="text" class="form-control" name="pais" id="pais" placeholder="País">
                            </div>
                            <div class="mb-3">
                                <label for="ciudad" class="form-label">
                                    <i class="bi bi-building"></i> Ciudad:
                                </label>
                                <input type="text" class="form-control" name="ciudad" id="ciudad" placeholder="Ciudad">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">
                                    <i class="bi bi-envelope"></i> Correo Electrónico:
                                </label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="Correo Electrónico">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">
                                    <i class="bi bi-lock"></i> Contraseña:
                                </label>
                                <input type="password" class="form-control" name="password" id="password" placeholder="Contraseña">
                            </div>
                            <div class="mb-3">
                                <label for="fotoPerfil" class="form-label">
                                    <i class="bi bi-image"></i> Foto de Perfil (URL):
                                </label>
                                <input type="text" class="form-control" name="fotoPerfil" id="fotoPerfil" placeholder="URL de la Foto de Perfil">
                            </div>
      

                            <input type="hidden" name="estado" value="1">
  

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
