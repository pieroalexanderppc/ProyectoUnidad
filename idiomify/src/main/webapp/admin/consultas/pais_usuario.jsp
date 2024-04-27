<%@page import="ModeloDAO.ClsModeloDaoConsultas"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Usuarios por País</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .card {
            width: 90%;
            /*margin: auto;*/
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
        <h1>Usuarios por País</h1>

        <% 
            ClsModeloDaoConsultas consultasDao = new ClsModeloDaoConsultas();
            Map<String, Integer> usuariosPorPais = consultasDao.obtenerUsuariosPorPais();

            // Crear listas para almacenar los datos del gráfico.
            String paises = "[";
            String cantidades = "[";

            // Iterar sobre el mapa para obtener la información.
            Set<Map.Entry<String, Integer>> entrySet = usuariosPorPais.entrySet();
            Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                String pais = entry.getKey();
                int cantidadUsuarios = entry.getValue();

                // Agregar datos a las listas del gráfico.
                paises += "'" + pais + "',";
                cantidades += cantidadUsuarios + ",";
            }

            // Eliminar la coma final de las listas.
            paises = paises.substring(0, paises.length() - 1) + "]";
            cantidades = cantidades.substring(0, cantidades.length() - 1) + "]";
        %>

        <canvas id="usuariosPorPaisChart" width="400" height="200"></canvas>

        <script>
            var ctx = document.getElementById('usuariosPorPaisChart').getContext('2d');

            // Parsea los datos de Java a JavaScript.
            var paises = <%= paises %>;
            var cantidades = <%= cantidades %>;

            // Configura y dibuja el gráfico de barras.
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: paises,
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
