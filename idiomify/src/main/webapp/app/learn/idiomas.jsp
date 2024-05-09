<%@page import="java.util.List"%>

<%@ page import="Modelo.ClsModeloIdioma" %>
<%@ page import="ModeloDAO.ClsModeloDaoIdioma" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Idiomas</title>
     <link rel="shortcut icon" href="./favicon.svg" type="image/svg+xml">

  <!-- 
    - Enlace al archivo CSS personalizado
  -->
  <link rel="stylesheet" href="./assets/css/style.css">

  <!-- 
    - Enlace a la fuente de Google
  -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=League+Spartan:wght@400;500;600;700;800&family=Poppins:wght@400;500&display=swap"
    rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        .card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .card {
            max-width: 18rem;
        }
        .custom-title {
            font-size: 32px;
            margin-bottom: 20px;
            color: #007bff;
        }
    </style>
</head>
<body>
        <%@ include file="../header.jsp" %>

        <h1 class="text-center custom-title">Listado de Idiomas</h1>
     
        <div class="card-container">
            <% 
            ClsModeloDaoIdioma dao = new ClsModeloDaoIdioma();
            List<ClsModeloIdioma> idiomas = dao.obtenerTodosIdiomas();
            for (ClsModeloIdioma idioma : idiomas) {
                int idIdioma = idioma.getIdIdioma();
                String nombre = sanitize(idioma.getNombre());
                String descripcion = sanitize(idioma.getDescripcion());
                String urlBanner = sanitize(idioma.getUrlBanner());
            %>
            <div class="card">
                <a href="CursoServlet?accion=listarCursos&idIdioma=<%= idIdioma %>&vista=si">
                    <img src="<%= urlBanner %>" class="card-img-top" alt="Banner">
                    <div class="card-body">
                        <h5 class="card-title"><%= nombre %></h5>
                        <p class="card-text"><%= descripcion %></p>
                    </div>
                </a>
            </div>
            <% } %>
        </div>
        

    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


<%! 
    public static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder sanitized = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '<':
                    sanitized.append("&lt;");
                    break;
                case '>':
                    sanitized.append("&gt;");
                    break;
                case '"':
                    sanitized.append("&quot;");
                    break;
                case '\'':
                    sanitized.append("&#39;");
                    break;
                case '&':
                    sanitized.append("&amp;");
                    break;
                default:
                    sanitized.append(c);
                    break;
            }
        }
        return sanitized.toString();
    }
%>