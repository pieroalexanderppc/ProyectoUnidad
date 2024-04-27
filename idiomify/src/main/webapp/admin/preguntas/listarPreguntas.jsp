<%@page import="Modelo.ClsModeloAdministrador"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ClsModeloPregunta"%>
<%@page import="ModeloDAO.ClsModeloDaoPregunta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Preguntas</title>
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
        <h2 class="text-center">Toda las Preguntas</h2>
            <div class="d-flex justify-content-end align-items-center mb-3">
                
                                     <%
    ClsModeloAdministrador adminAutenticadope = (ClsModeloAdministrador) session.getAttribute("adminAutenticado");
%>

<% if (adminAutenticadope != null && !adminAutenticadope.getRol().equalsIgnoreCase("Lectura")) { %>


        <a href="/idiomify/PreguntaServlet?accion=agregarPreguntas" class="btn btn-primary me-2">
                <i class="bi bi-plus"></i> Nuevo
        </a>
<% } %>
          <a href="/idiomify/PreguntaServlet?accion=exportarPdf" class="btn btn-primary me-2">
                PDF <i class="fa-solid fa-download"></i>
            </a>
            <a href="/idiomify/PreguntaServlet?accion=exportarCsv" class="btn btn-primary me-2">
               CSV<i class="fa-solid fa-download"></i>
            </a>
          
          
<% if (adminAutenticadope != null && !adminAutenticadope.getRol().equalsIgnoreCase("Lectura")) { %>



            <form method="post" action="/idiomify/PreguntaServlet?accion=insertarLeccionesCSV" enctype="multipart/form-data">
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
  
            <thead >
                <tr>
                    <th>ID</th>
                    <th>FKidLeccion</th>
                    <th>Enunciado</th>
                    <th>Actividad</th>
                    <th>Respuesta</th>
                    <th>URL Ilustración</th>
                    <th>URL Audio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String idLeccionStr = request.getParameter("idLeccion");
                    ClsModeloDaoPregunta dao = new ClsModeloDaoPregunta();
                    List<ClsModeloPregunta> preguntas = dao.listarPreguntasPorLeccion(idLeccionStr);
                    for (ClsModeloPregunta pregunta : preguntas) { %>
                    <tr>
                        <td><%= pregunta.getIdPregunta() %></td>
                        <td><%= pregunta.getFKidLeccion() %></td>
                        <td><%= pregunta.getEnunciado() %></td>
                        <td><%= pregunta.getActividad() %></td>
                        <td><%= pregunta.getRespuesta() %></td>
                        <td>
                            <img src="<%= pregunta.getUrlIlustracion()%>" alt="Banner" width="60">
                        </td>
                        <td>
                            <audio controls>
                                <source src="<%= pregunta.getUrlAudio() %>" type="audio/mpeg">
                                Tu navegador no soporta la reproducción de audio.
                            </audio>
                        </td>
                        <td>
                            <div class="acciones-icons">
                                
<% if (adminAutenticadope != null && !adminAutenticadope.getRol().equalsIgnoreCase("Lectura")) { %>


   
                                <a href="/idiomify/PreguntaServlet?accion=editarPreguntas&idPregunta=<%= pregunta.getIdPregunta() %>" class="btn btn-warning">
                                    <i class="bi bi-pencil"></i>
                                </a>
<% } %>

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
