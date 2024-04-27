<%-- 
    Document   : cursos
    Created on : Nov 7, 2023, 4:39:07 AM
    Author     : LENOVO
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        #top{
            margin-top: 40px;
        }

        body.dark-mode {
            background-color: #1a1a1a;
            color: #ffffff;
        }

        .card.dark-mode {
            background-color: #2c2c2c;
            border: 1px solid #444;
            color: #ffffff;
        }

        .card.dark-mode .card-title {
            color: #ffffff;
        }

        .card.dark-mode .card-text {
            color: #d3d3d3;
        }
    </style>
</head>
<body id="top">
    <%@ include file="../headerlanding.jsp" %>
    
        <section class="section course" id="courses" aria-label="course">
        <div class="container">

          <p class="section-subtitle text_negrita">Cursos de Quechua</p>

          <h2 class="h2 section-title text_negrita">Elija un Curso para iniciar</h2>

          <ul class="grid-list">
                                          <%
                String idIdiomaStr = request.getParameter("idIdioma");
                int idIdioma = Integer.parseInt(idIdiomaStr);
                ClsModeloDaoCurso dao = new ClsModeloDaoCurso();
                List<ClsModeloCurso> cursos = dao.listarCursosPorIdIdioma(idIdioma);
                for (ClsModeloCurso curso : cursos) {
            %>

            <li>

              <div class="course-card">

                <figure class="card-banner img-holder" style="--width: 370; --height: 220;">
                  <img src="<%= curso.getUrlBanner() %>" width="370" height="220" loading="lazy"
                    alt="Build Responsive Real- World Websites with HTML and CSS" class="img-cover">
                </figure>

                <div class="abs-badge">
                  <ion-icon name="time-outline" aria-hidden="true"></ion-icon>

                  
                </div>

                <div class="card-content">

                  <span class="badge">Basico</span>

                  <h3 class="h3">
                    <a href="#" class="card-title"><%= curso.getNombre() %></a>
                  </h3>

                  <div class="wrapper">

   
         

                </div>

              </div>

            </li>
                          <%
                }
            %>


          </ul>

          <a href="#" class="btn has-before">
            <span class="span">Iniciar Gratis</span>
          </a>

        </div>
      </section>


                  <%@ include file="../footerlanding.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
