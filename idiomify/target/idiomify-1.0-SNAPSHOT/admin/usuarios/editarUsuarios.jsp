<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.ClsModeloUsuario" %>
<%@ page import="ModeloDAO.ClsModeloDaoUsuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
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
                            <i class="bi bi-pencil-square"></i> Editar Usuario
                        </h1>
                        <%
                            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                            ClsModeloDaoUsuario dao = new ClsModeloDaoUsuario();
                            ClsModeloUsuario usuario = dao.obtenerUsuarioPorId(idUsuario);

                            if (usuario != null) { %>
                                <form method="post" action="/idiomify/UsuarioServlet?accion=actualizarUsuario">
                                    <input type="hidden" name="idUsuario" value="<%= usuario.getIdUsuario() %>">
                                    <div class="mb-3">
                                        <label for="nombre" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Nombre:
                                        </label>
                                        <input type="text" class="form-control" name="nombre" value="<%= usuario.getNombre() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="apellido" class="form-label">
                                            <i class="bi bi-file-earmark-text"></i> Apellido:
                                        </label>
                                        <input type="text" class="form-control" name="apellido" value="<%= usuario.getApellido() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="fechaNacimiento" class="form-label">
                                            <i class="bi bi-calendar"></i> Fecha de Nacimiento:
                                        </label>
                                        <input type="date" class="form-control" name="fechaNacimiento" value="<%= usuario.getFechaNacimiento() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="genero" class="form-label">
                                            <i class="bi bi-gender"></i> Género:
                                        </label>
                                        <select class="form-select" name="genero">
                                            <option value="Masculino" <%= usuario.getGenero().equals("Masculino") ? "selected" : "" %>>Masculino</option>
                                            <option value="Femenino" <%= usuario.getGenero().equals("Femenino") ? "selected" : "" %>>Femenino</option>
                                            <option value="Otro" <%= usuario.getGenero().equals("Otro") ? "selected" : "" %>>Otro</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="pais" class="form-label">
                                            <i class="bi bi-flag"></i> País:
                                        </label>
                                        <input type="text" class="form-control" name="pais" value="<%= usuario.getPais() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="ciudad" class="form-label">
                                            <i class="bi bi-building"></i> Ciudad:
                                        </label>
                                        <input type="text" class="form-control" name="ciudad" value="<%= usuario.getCiudad() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">
                                            <i class="bi bi-envelope"></i> Correo Electrónico:
                                        </label>
                                        <input type="email" class="form-control" name="email" value="<%= usuario.getEmail() %>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="password" class="form-label">
                                            <i class="bi bi-lock"></i> Contraseña:
                                        </label>
                                        <input type="password" class="form-control" name="password" value="<%= usuario.getPasswordHash()%>">
                                    </div>
                                    <div class="mb-3">
                                        <label for="fotoPerfil" class="form-label">
                                            <i class="bi bi-image"></i> Foto de Perfil (URL):
                                        </label>
                                        <input type="text" class="form-control" name="fotoPerfil" value="<%= usuario.getFotoPerfil() %>">
                                    </div>
                                                <div class="mb-3">
                                            <label for="estado" class="form-label">
                                                <i class="bi bi-info-circle"></i> Estado:
                                            </label>
                                                <div class="form-check">
                                                 <input type="checkbox" class="form-check-input" name="estado" id="estado" <%= usuario.getEstado() == 1 ? "checked" : "" %> value="<%= usuario.getEstado() == 1 ? "1" : "0" %>">
                                                 <label class="form-check-label" for="estado">Activo</label>
                                             </div>

                                        </div>

                                    <div class="d-flex justify-content-center">
                                        <button type="submit" class="btn btn-primary w-100">
                                            <i class="bi bi-check"></i> Guardar cambios
                                        </button>
                                    </div>
                                    
                                </form>
                            <% } else { %>
                                <p>El usuario no se encontró.</p>
                            <% }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
