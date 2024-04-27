<%@page import="ModeloDAO.ClsModeloDaoConsultas"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Usuarios por Género</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .card{
        
            width: 400px;
            margin: 20px auto;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
    </style>
</head>
<body>
     <%@ include file="../header.jsp" %>
    <div class="card">
        <h1>Cantidad de Usuarios por Género</h1>

        <% 
            ClsModeloDaoConsultas consultasDao = new ClsModeloDaoConsultas();
            Map<String, Integer> usuariosPorGenero = consultasDao.obtenerUsuariosPorGenero();

            // Crear un objeto JSON en Java.
            String usuariosPorGeneroJson = "{";
            Set<Map.Entry<String, Integer>> entrySet = usuariosPorGenero.entrySet();
            Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                String genero = entry.getKey();
                int cantidadUsuarios = entry.getValue();

                usuariosPorGeneroJson += "\"" + genero + "\":" + cantidadUsuarios;

                if (iterator.hasNext()) {
                    usuariosPorGeneroJson += ",";
                }
            }

            usuariosPorGeneroJson += "}";
        %>

        <canvas id="usuariosPorGeneroChart" width="400" height="200"></canvas>

        <script>
            var ctx = document.getElementById('usuariosPorGeneroChart').getContext('2d');

            // Parsea los datos de Java a JavaScript.
            var usuariosPorGenero = <%= usuariosPorGeneroJson %>;

            // Configura y dibuja el gráfico circular (pastel).
            var myChart = new Chart(ctx, {
                type: 'doughnut', // 'doughnut' mantiene la forma circular
                data: {
                    labels: Object.keys(usuariosPorGenero),
                    datasets: [{
                        data: Object.values(usuariosPorGenero),
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                        ],
                        borderWidth: 1
                    }]
                }
            });
        </script>
    </div>
</body>
</html>
