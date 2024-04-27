<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.ClsModeloLeccion"%>
<%@page import="ModeloDAO.ClsModeloDaoLeccion"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lección Completada</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* Custom styles */
        body {
                background: linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%), linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%);
            color: white;
            height: 80vw;
        }
        .lesson-info {
            border: 1px solid #cacaca;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
          background: linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%), linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%);
        }
        .lesson-info img {
            width: 100px;
            height: auto;
            margin-bottom: 15px;
        }
        .array-values .card {
            background-color: #4561a7; /* Azul más claro */
            border-radius: 5px;
            margin-bottom: 10px;
        }
        span{
            background: red;
            border-radius: 5px;
            padding: 5px
        }
        .feed{
    margin: auto;
             border-radius: 5px;
             border: 1px solid red;
             width: fit-content;
             padding: 5px;
        }
        a{
            color:white;
            font-family: sans-serif;
            text-decoration: none;
            font-weight: 500;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4"><i class="fas fa-book-open icon"></i> Lección Incompleta</h1>

        <% 
            String idLeccion = request.getParameter("idLeccion");
            String puntuacion = request.getParameter("puntuacion");
            String vidas = request.getParameter("vidas");
            String arrayEncoded = request.getParameter("array");

            // Decodificar y convertir en JSONArray
            String arrayDecoded = URLDecoder.decode(arrayEncoded, "UTF-8");
            JSONArray arrayBidimensional = new JSONArray(arrayDecoded);

            ClsModeloDaoLeccion daoLeccion = new ClsModeloDaoLeccion();
            ClsModeloLeccion leccion = daoLeccion.buscarPorIdLeccion(idLeccion);
        %>

        <div class="lesson-info text-center">
            <!-- Mostrar detalles de la lección -->
            <% if (leccion != null) { %>
                <h3><%= leccion.getTitulo() %></h3>
                <img src="<%= leccion.getUrlBanner() %>" alt="Banner de la lección" class="rounded mb-3">
                <p><i class="fas fa-star icon"></i> Puntuación: <%= puntuacion %></p>
                <p><i class="fas fa-heartbeat icon"></i> Vidas: <%= vidas %></p>
            <% } else { %>
                <p>Información de la lección no disponible.</p>
            <% } %>

            <h2 class="text-center mb-3"><i class="fas fa-language icon"></i> Traducciones de los errores:</h2>
            <div class="row array-values">
                <% 
                for (int i = 0; i < arrayBidimensional.length(); i++) {
                    JSONArray subArray = arrayBidimensional.getJSONArray(i);
                %>
                <div class="col-12 mb-2">
                    <div class="translation-pair feed">
                        <span><i class="fas fa-globe-americas icon"></i> Español: <%= subArray.getString(0) %></span>
                        <span><i class="fas fa-exchange-alt icon"></i> Traducción: <%= subArray.getString(1) %></span>
                    </div>
                </div>
                <%
                }
                %>
            </div>
        </div>

 
                <button class="btn btn-primary">
                    <i class="fa-solid fa-circle-arrow-left">
                        
                    <a href="/idiomify/LeccionServlet?accion=listarLecciones&idCurso=QUE-01&ruta=app">Volver a Intentar</a>
                    </i> 
                </button>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
