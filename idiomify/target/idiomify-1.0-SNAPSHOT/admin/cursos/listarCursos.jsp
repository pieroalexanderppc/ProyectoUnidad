<%-- 
    Document   : listarCursos
    Created on : Oct 31, 2023, 1:39:41 PM
    Author     : LENOVO
--%>

<%@page import="Modelo.ClsModeloAdministrador"%>
<%-- 
    Document: listarCursos
    Created on: Oct 31, 2023, 1:39:41 PM
    Author: LENOVO
--%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ClsModeloCurso"%>
<%@page import="ModeloDAO.ClsModeloDaoCurso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Cursos</title>
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
     <% String idIdiomaStr = request.getParameter("idIdioma");
     %>
     
    <div class="container mt-5" id="top">
        <h2 class="text-center">Todo los Cursos</h2>
         <div class="d-flex justify-content-end align-items-center mb-3">
             
             
                     <%
    ClsModeloAdministrador adminAutenticadoped = (ClsModeloAdministrador) session.getAttribute("adminAutenticado");
%>

<% if (adminAutenticadope != null && !adminAutenticadope.getRol().equalsIgnoreCase("Lectura")) { %>

        <a href="/idiomify/CursoServlet?accion=agregarCursos" class="btn btn-primary me-2">
                <i class="bi bi-plus"></i> Nuevo
<% } %>
            </a>
           <a href="/idiomify/CursoServlet?accion=exportarPdf&idIdioma=<%= idIdiomaStr%>" class="btn btn-primary me-2">
                PDF <i class="fa-solid fa-download"></i>
            </a>
            <a href="/idiomify/CursoServlet?accion=exportarCsv&idIdioma=<%= idIdiomaStr%>" class="btn btn-primary me-2">
               CSV<i class="fa-solid fa-download"></i>
            </a>
               
            <% if (adminAutenticadope != null && !adminAutenticadope.getRol().equalsIgnoreCase("Lectura")) { %>

  
            <form method="post" action="/idiomify/CursoServlet?accion=insertarCursosCSV" enctype="multipart/form-data">
                <div class="input-group">
                    <input type="file" class="form-control" id="archivoCSV" name="archivoCSV" accept=".csv" required>
                    <button type="submit" class="btn btn-primary">
                       <i class="fa-solid fa-upload" style="color: #ffffff;"></i> Subir CSV
                    </button>
                </div>
            </form>
<% } %>
              </div>
           <div class="table-responsive">
            <table class="table table-sm table-dark" id="myTable">


            <thead class="table-header">
                <tr>
                    <th>ID</th>
                    <th>FKidIdioma</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>URL Banner</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    
                    int idIdioma = Integer.parseInt(idIdiomaStr);
                    ClsModeloDaoCurso dao = new ClsModeloDaoCurso();
                    List<ClsModeloCurso> cursos = dao.listarCursosPorIdIdioma(idIdioma);
                    for (ClsModeloCurso curso : cursos) { %>
                    <tr>
                        <td><%= curso.getIdCurso() %></td>
                        <td><%= curso.getFKidIdioma() %></td>
                        <td><%= curso.getNombre() %></td>
                        <td><%= curso.getDescripcion() %></td>
                        <td>
                            <img src="<%= curso.getUrlBanner() %>" alt="Banner" width="100" >
                        </td>
                        <td>
                            <div class="acciones-icons">
                                
                         <% if (adminAutenticadope != null && !adminAutenticadope.getRol().equalsIgnoreCase("Lectura")) { %>

  
                                <a href="/idiomify/CursoServlet?accion=editarCursos&idCurso=<%= curso.getIdCurso()%>" class="btn btn-warning">
                                   <i class="fa-solid fa-pen-to-square" style="color: #ffffff;"></i>
                                </a>
<% } %>

                                <a href="/idiomify/LeccionServlet?accion=listarLecciones&idCurso=<%= curso.getIdCurso()%>&ruta=admin" class="btn btn-info">
                                    <i class="bi bi-eye"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                <%
                    }
                %>
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
