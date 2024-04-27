<%@page import="ModeloDAO.ClsModeloDaoConsultas"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Usuarios por Rango de Edades</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .card {
            width: 90%;
            margin: auto;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            color: #cacaca;
        }
    </style>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <div class="card">
        <h1>Usuarios por Rango de Edades</h1>

        <% 
            ClsModeloDaoConsultas consultasDao = new ClsModeloDaoConsultas();
            Map<String, Integer> usuariosPorRangoEdades = consultasDao.obtenerUsuariosPorRangoEdades();

            // Crear listas para almacenar los datos del gráfico.
            String rangos = "[";
            String cantidades = "[";

            // Iterar sobre el mapa para obtener la información.
            Set<Map.Entry<String, Integer>> entrySet = usuariosPorRangoEdades.entrySet();
            Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                String rango = entry.getKey();
                int cantidadUsuarios = entry.getValue();

                // Agregar datos a las listas del gráfico.
                rangos += "'" + rango + "',";
                cantidades += cantidadUsuarios + ",";
            }

            // Eliminar la coma final de las listas.
            rangos = rangos.substring(0, rangos.length() - 1) + "]";
            cantidades = cantidades.substring(0, cantidades.length() - 1) + "]";
        %>

        <canvas id="usuariosPorRangoEdadesChart" width="400" height="200"></canvas>

        <script>
            var ctx = document.getElementById('usuariosPorRangoEdadesChart').getContext('2d');

            // Parsea los datos de Java a JavaScript.
            var rangos = <%= rangos %>;
            var cantidades = <%= cantidades %>;

            // Configura y dibuja el gráfico de barras.
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: rangos,
                    datasets: [{
                        label: 'Cantidad de Usuarios',
                        data: cantidades,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </div>
</body>
</html>
