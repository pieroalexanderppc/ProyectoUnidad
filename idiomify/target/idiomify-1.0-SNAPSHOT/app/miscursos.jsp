<%@page import="Modelo.ClsModeloUsuario"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ClsModeloConsultaProgreso"%>
<%@page import="ModeloDAO.ClsModeloDaoProgreso"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detalles de Progreso</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Estilos personalizados -->
    <style>
        /* Estilos para las tarjetas (cards) */
        .card {
            color: white;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin: 10px;
            width: 300px;
               background: linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%), linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%);

        }

        /* Ajustes para la imagen del banner */
        .card img {
            width: 100px;
            height: auto;
            margin-top: 10px;
            border-radius: 5px;
        }

        /* Estilos para los iconos */
        .icon {
            margin-right: 5px;
        }
    </style>
</head>
<body>
     <%@ include file="header.jsp" %>
    <div class="container">
        <h1 class="text-center mt-3 mb-4">Detalles de Progreso</h1>
        <div class="row">
            <% 
                      int idUsuario =-1;
                ClsModeloUsuario usuarioAutenticado = (ClsModeloUsuario) session.getAttribute("usuarioAutenticado");
            if (usuarioAutenticado != null) {
                 idUsuario = usuarioAutenticado.getIdUsuario();
                // Aquí puedes utilizar idUsuario en tu JSP
            }
                ClsModeloDaoProgreso daoProgreso = new ClsModeloDaoProgreso();
                List<ClsModeloConsultaProgreso> detallesProgreso = daoProgreso.obtenerDetallesProgresoPorUsuario(idUsuario);

                for (ClsModeloConsultaProgreso progreso : detallesProgreso) {
            %>
                <!-- Código HTML para la tarjeta de progreso -->
                <div class="col-md-4">
                    <div class="card">
                        <h2><i class="fas fa-book icon"></i> Lección: <%= progreso.getTituloLeccion() %></h2>
                        <p><i class="fas fa-chalkboard-teacher icon"></i> Curso: <%= progreso.getNombreCurso() %></p>
                        <p><i class="fas fa-language icon"></i> Idioma: <%= progreso.getNombreIdioma() %></p>
                        <p><i class="fas fa-star icon"></i> Puntuación: <%= progreso.getPuntuacion() %></p>
                        <p><i class="far fa-calendar-alt icon"></i> Fecha de Progreso: <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(progreso.getFechaProgreso()) %></p>
                        <!-- Imagen del banner de la lección -->
                        <img src="<%= progreso.getUrlBannerLeccion() %>" alt="Banner de la Lección">
                    </div>
                </div>
            <% } %>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
