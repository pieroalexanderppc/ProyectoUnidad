<%@page import="Modelo.ClsModeloAdministrador"%>
<%@page import="ModeloDAO.ClsModeloDaoAdministrador"%>
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
      <%
                            String dniAdmin = request.getParameter("dniAdmin");
                            ClsModeloDaoAdministrador dao = new ClsModeloDaoAdministrador();
                            ClsModeloAdministrador admin = dao.obtenerAdministradorPorDNI(dniAdmin);

                            if (admin != null) { %>
    <%@ include file="../header.jsp" %>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h1 class="custom-title">
                            <i class="bi bi-pencil-square"></i> Editar Admin
                        </h1>
                        <form method="post" action="/idiomify/AdministradorServlet?accion=editarAdministrador">
                            <div class="mb-3">
                                <label for="dni" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> DNI:
                                </label>
                                <input type="text" class="form-control" name="dni" id="dni" value="<%= admin.getDni() %>" placeholder="dni" disabled>
                            </div>
                            <div class="mb-3">
                                <label for="nombre" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Nombre:
                                </label>
                                <input type="text" class="form-control" name="nombre" id="nombre" value="<%= admin.getNombre() %>" placeholder="Nombre">
                            </div>
                            <div class="mb-3">
                                <label for="apellido" class="form-label">
                                    <i class="bi bi-file-earmark-text"></i> Apellido:
                                </label>
                                <input type="text" class="form-control" name="apellido" id="apellido" value="<%= admin.getApellido() %>" placeholder="Apellido">
                            </div>
                            <div class="mb-3">
                                <label for="fechaNacimiento" class="form-label">
                                    <i class="bi bi-calendar"></i> Fecha de Nacimiento:
                                </label>
                                <input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento" value="<%= admin.getFechaNacimiento() %>">
                            </div>
                            <div class="mb-3">
                                <label for="genero" class="form-label">
                                    <i class="bi bi-gender"></i> Género:
                                </label>
                                <select class="form-select" name="genero" id="genero" value="<%= admin.getGenero() %>">
                                    <option value="Masculino">Masculino</option>
                                    <option value="Femenino">Femenino</option>
                                    <option value="Otro">Otro</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="pais" class="form-label">
                                    <i class="bi bi-flag"></i> País:
                                </label>
                                <input type="text" class="form-control" name="pais" id="pais" value="<%= admin.getPais() %>" placeholder="País" >
                            </div>
                            <div class="mb-3">
                                <label for="ciudad" class="form-label">
                                    <i class="bi bi-building"></i> Ciudad:
                                </label>
                                <input type="text" class="form-control" name="ciudad" id="ciudad" value="<%= admin.getCiudad() %>" placeholder="Ciudad">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">
                                    <i class="bi bi-envelope"></i> Correo Electrónico:
                                </label>
                                <input type="email" class="form-control" name="email" id="email" value="<%= admin.getEmail() %>" placeholder="Correo Electrónico">
                            </div>
                            <div class="mb-3">
                                <label for="telefono" class="form-label">
                                    <i class="bi bi-envelope"></i> Telefono:
                                </label>
                                <input type="text" class="form-control" name="telefono" id="telefono" value="<%= admin.getTelefono() %>" placeholder="Telefono">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">
                                    <i class="bi bi-lock"></i> Contraseña:
                                </label>
                                <input type="password" class="form-control" name="password" id="password" value="<%= admin.getPasswordHash() %>" placeholder="Contraseña">
                            </div>
                            <div class="mb-3">
                                <label for="rol" class="form-label">
                                    <i class="bi bi-gender"></i> Rol:
                                </label>
                                <select class="form-select" name="rol" id="rol" value="<%= admin.getRol() %>">
                                    <option value="Superadministrador">Superadministrador</option>
                                    <option value="Administrador">Administrador</option>
                                    <option value="Lectura">Lectura</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="estado" class="form-label">
                                    <i class="bi bi-gender"></i> Rol:
                                </label>
                                <select class="form-select" name="estado" id="estado" value="<%= admin.getEstado() %>">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                     
                                </select>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary w-100">
                                    <i class="bi bi-check"></i> Enviar
                                </button>
                            </div>
                        </form>
                                                    <%
                            } else { %>
                                <p>El admin no se encontró.</p>
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
