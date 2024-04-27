<%-- 
    Document: listarUsuarios
    Created on: Nov 4, 2023, 11:53:01 AM
    Author: LENOVO
--%>

<%@page import="Modelo.ClsModeloAdministrador"%>
<%@page import="ModeloDAO.ClsModeloDaoAdministrador"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.ClsModeloUsuario" %>
<%@ page import="ModeloDAO.ClsModeloDaoUsuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Administradores</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>

        .table-header {
            background-color: #aed9e0 !important;
        }
        .acciones-icons {
            display: flex;
            gap: 10px;
        }
        .custom-title {
            font-size: 32px !important;
            margin-bottom: 20px !important;
            color: #007bff !important;
        }
        .table-dark {
            background-color: #000000 !important; /* Cambiado a negro */

            border: 1px solid #ffffff; /* Añadir borde blanco */
        }
        .table-dark th, .table-dark td {
            border-color: #cacaca;
        }
        .btn {
            border-radius: 5px; /* Añadir borde redondeado a los botones */
        }
   
    </style>
</head>
<body>
     <%@ include file="../header.jsp" %>
     <div class="container mt-5" id="top">
        <h2 class="text-center">Listado de Administradores</h2>
        <div class="d-flex justify-content-end align-items-center mb-3">
        <!-- Puedes cambiar la URL del enlace según tus necesidades -->
        <a href="/idiomify/AdministradorServlet?accion=agregarAdmin" class="btn btn-primary me-2">
            <i class="bi bi-plus"></i> Nuevo
        </a>
        <a href="/idiomify/AdministradorServlet?accion=exportarPdf" class="btn btn-primary me-2">
            PDF <i class="fa-solid fa-download"></i>
        </a>
        <a href="/idiomify/AdministradorServlet?accion=exportarCsv" class="btn btn-primary me-2">
           CSV<i class="fa-solid fa-download"></i>
        </a>
        <form method="post" action="/idiomify/AdministradorServlet?accion=insertarAdministradorCSV" enctype="multipart/form-data">
            <div class="input-group">
                <input type="file" class="form-control" id="archivoCSV" name="archivoCSV" accept=".csv" required>
                <button type="submit" class="btn btn-primary">
                   <i class="fa-solid fa-upload" style="color: #ffffff;"></i> Subir CSV
                </button>
            </div>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table table-sm table-dark" id="myTable">
            <thead >
                <tr>
                     <th>Foto</th>
                     <th>Dni</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Genero</th>
                    <th>Pais</th>
                    <th>Ciudad</th>
                    <th>Email</th>
                    <th>Telefono</th>
                    <th>Rol</th>
                    <th>Password</th>
                    <th>Fecha Registro</th>
                    <th>Fecha Actualizacion</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% ClsModeloDaoAdministrador dao = new ClsModeloDaoAdministrador();
                   List<ClsModeloAdministrador> administradores = dao.obtenerTodosAdministradores();
                   for (ClsModeloAdministrador administrador : administradores) { %>
                   <tr>
                          <td> <img src="admin/<%= administrador.getFotoPerfil() %>  " width="50px"></img></td>
                       <td><%= administrador.getDni() %></td>
                       <td><%= administrador.getNombre() %></td>
                       <td><%= administrador.getApellido() %></td>
                       <td><%= administrador.getFechaNacimiento() %></td>
                       <td><%= administrador.getGenero() %></td>
                       <td><%= administrador.getPais() %></td>
                       <td><%= administrador.getCiudad() %></td>
                       <td><%= administrador.getEmail() %></td>
                       <td><%= administrador.getTelefono() %></td>
                       <td><%= administrador.getRol() %></td>
                       <td><%= administrador.getPasswordHash() %></td>
                       <td><%= administrador.getFechaRegistro() %></td>
                       <td><%= administrador.getFechaActualizacion() %></td>
                       <td><%= administrador.getEstado() %></td>
                    
                       <td>
                           <div class="acciones-icons">
                               <!-- Puedes cambiar las URLs de los enlaces según tus necesidades -->
                               <a href="/idiomify/AdministradorServlet?accion=editarAdmin&dniAdmin=<%= administrador.getDni()%>" class="btn btn-primary">
                                <i class="fa-regular fa-pen-to-square"></i>
                               </a>
         
                           </div>
                       </td>
                   </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
    
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap5.min.js"></script>
<script>
  $(document).ready(function() {
$('#myTable').DataTable({
    "language": {
        "search": "Buscar:",
        "lengthMenu": "Mostrar _MENU_ entradas por página",
        "info": "Mostrando _START_ a _END_ de _TOTAL_ entradas",
        "infoEmpty": "Mostrando 0 a 0 de 0 entradas",
        "zeroRecords": "No se encontraron resultados",
        "infoFiltered": "(filtrado de _MAX_ entradas totales)",
        "paginate": {
            "previous": "Anterior",
            "next": "Siguiente"
        },
        "loadingRecords": "Cargando...",
        "processing": "Procesando...",
        "emptyTable": "No hay datos disponibles en la tabla"
    }   
});
});

</script>
</body>
</html>
