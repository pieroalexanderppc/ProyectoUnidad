package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import Configdb.ClsConexiondb;
import Modelo.ClsModeloUsuario;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

public class ClsModeloDaoConsultas {

    public Map<String, Integer> obtenerUsuariosPorPais() {
        Map<String, Integer> usuariosPorPais = new HashMap<>();

        // Crear una instancia de la clase de conexión a la base de datos.
        ClsConexiondb conexionDB = new ClsConexiondb();

        // Obtener la conexión a la base de datos.
        Connection conexion = conexionDB.obtenerConexion();

        if (conexion != null) {
            try {
                // Definir la consulta SQL.
                String consultaSQL = "SELECT pais, COUNT(*) as cantidad FROM tbUsuarios GROUP BY pais";

                // Crear un objeto PreparedStatement para ejecutar la consulta.
                try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                    // Ejecutar la consulta y obtener el conjunto de resultados.
                    try (ResultSet resultados = statement.executeQuery()) {
                        // Iterar sobre los resultados y agregar al mapa.
                        while (resultados.next()) {
                            String pais = resultados.getString("pais");
                            int cantidadUsuarios = resultados.getInt("cantidad");
                            usuariosPorPais.put(pais, cantidadUsuarios);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Cerrar la conexión a la base de datos al finalizar.
                conexionDB.cerrarConexion();
            }
        } else {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
        }

        return usuariosPorPais;
    }
     public Map<String, Integer> obtenerUsuariosPorGenero() {
    Map<String, Integer> usuariosPorGenero = new HashMap<>();

    ClsConexiondb conexionDB = new ClsConexiondb();
    Connection conexion = conexionDB.obtenerConexion();

    if (conexion != null) {
        try {
            String consultaSQL = "SELECT genero, COUNT(*) as cantidad FROM tbUsuarios GROUP BY genero";

            try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                try (ResultSet resultados = statement.executeQuery()) {
                    while (resultados.next()) {
                        String genero = resultados.getString("genero");
                        int cantidadUsuarios = resultados.getInt("cantidad");
                        usuariosPorGenero.put(genero, cantidadUsuarios);
                    }
                }
            }

            // Imprimir datos para verificar en la consola del servidor
            System.out.println("Datos de usuarios por género: " + usuariosPorGenero);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionDB.cerrarConexion();
        }
    } else {
        System.out.println("No se pudo obtener la conexión a la base de datos.");
    }

    return usuariosPorGenero;
}


    // Método para obtener la cantidad total de usuarios en la plataforma.
    public int obtenerCantidadTotalUsuarios() {
        int cantidadTotalUsuarios = 0;

        ClsConexiondb conexionDB = new ClsConexiondb();
        Connection conexion = conexionDB.obtenerConexion();

        if (conexion != null) {
            try {
                String consultaSQL = "SELECT COUNT(*) as cantidad FROM tbUsuarios";

                try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                    try (ResultSet resultados = statement.executeQuery()) {
                        if (resultados.next()) {
                            cantidadTotalUsuarios = resultados.getInt("cantidad");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionDB.cerrarConexion();
            }
        } else {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
        }

        return cantidadTotalUsuarios;
    }
    public Map<String, Integer> obtenerUsuariosPorRangoEdades() {
        Map<String, Integer> usuariosPorRangoEdades = new HashMap<>();

        // Obtener la conexión a la base de datos.
        ClsConexiondb conexionDB = new ClsConexiondb();
        Connection conexion = conexionDB.obtenerConexion();

        if (conexion != null) {
            try {
                // Definir la consulta SQL.
                String consultaSQL = "SELECT COUNT(*) as cantidad FROM tbUsuarios WHERE fechaNacimiento BETWEEN ? AND ?";

                // Obtener el año actual.
                int anoActual = Calendar.getInstance().get(Calendar.YEAR);

                // Definir los rangos de edades (por ejemplo, de 0 a 9, de 10 a 19, etc.).
                int rangoInicial = 0;
                int rangoFinal = 9;

                // Crear un objeto PreparedStatement para ejecutar la consulta.
                try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                    for (int i = 0; i < 10; i++) {
                        // Calcular las fechas límite del rango.
                        String fechaInicio = (anoActual - rangoFinal) + "-01-01";
                        String fechaFin = (anoActual - rangoInicial) + "-12-31";

                        // Establecer los parámetros en la consulta.
                        statement.setString(1, fechaInicio);
                        statement.setString(2, fechaFin);

                        // Ejecutar la consulta y obtener el conjunto de resultados.
                        try (ResultSet resultados = statement.executeQuery()) {
                            // Iterar sobre los resultados y agregar al mapa.
                            if (resultados.next()) {
                                String rango = rangoInicial + " - " + rangoFinal;
                                int cantidadUsuarios = resultados.getInt("cantidad");
                                usuariosPorRangoEdades.put(rango, cantidadUsuarios);
                            }
                        }

                        // Actualizar los rangos para la próxima iteración.
                        rangoInicial += 10;
                        rangoFinal += 10;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Cerrar la conexión a la base de datos al finalizar.
                conexionDB.cerrarConexion();
            }
        } else {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
        }

        return usuariosPorRangoEdades;
    }
    
//    PARA CONSULTAR USUARIOS POR MES
    
public Map<String, Integer> obtenerUsuariosPorMes() {
    Map<String, Integer> usuariosPorMes = new LinkedHashMap<>();

    ClsConexiondb conexionDB = new ClsConexiondb();
    Connection conexion = conexionDB.obtenerConexion();

    if (conexion != null) {
        try {
            // Consulta SQL para obtener la cantidad de usuarios registrados por mes.
            String consultaSQL = "SELECT MONTH(fechaRegistro) AS mes, COUNT(*) AS cantidad FROM tbUsuarios GROUP BY mes";

            try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                try (ResultSet resultados = statement.executeQuery()) {
                    while (resultados.next()) {
                        int mes = resultados.getInt("mes");
                        String nombreMes = obtenerNombreMes(mes); // Método auxiliar para obtener el nombre del mes.
                        int cantidadUsuarios = resultados.getInt("cantidad");
                        usuariosPorMes.put(nombreMes, cantidadUsuarios);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionDB.cerrarConexion();
        }
    } else {
        System.out.println("No se pudo obtener la conexión a la base de datos.");
    }

    // Rellenar con 0 para los meses sin registros.
    for (int i = 1; i <= 12; i++) {
        String nombreMes = obtenerNombreMes(i);
        usuariosPorMes.putIfAbsent(nombreMes, 0);
    }

    return usuariosPorMes;
}

    
        // Método auxiliar para obtener el nombre del mes.
    private String obtenerNombreMes(int numeroMes) {
        switch (numeroMes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "Desconocido";
        }
    }
    
    
    // Método para obtener los últimos usuarios registrados.
    public List<ClsModeloUsuario> obtenerUltimosUsuariosRegistrados(int cantidad) {
        List<ClsModeloUsuario> ultimosUsuarios = new ArrayList<>();

        ClsConexiondb conexionDB = new ClsConexiondb();
        Connection conexion = conexionDB.obtenerConexion();

        if (conexion != null) {
            try {
                // Consulta SQL para obtener los últimos usuarios registrados.
                String consultaSQL = "SELECT * FROM tbUsuarios ORDER BY fechaRegistro DESC LIMIT ?";

                try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                    statement.setInt(1, cantidad);

                    try (ResultSet resultados = statement.executeQuery()) {
                        while (resultados.next()) {
                            int idUsuario = resultados.getInt("idUsuario");
                            String nombre = resultados.getString("nombre");
                            String apellido = resultados.getString("apellido");
                            String fechaNacimiento = resultados.getString("fechaNacimiento");
                            String genero = resultados.getString("genero");
                            String pais = resultados.getString("pais");
                            String ciudad = resultados.getString("ciudad");
                            String email = resultados.getString("email");
                            String passwordHash = resultados.getString("passwordHash");
                            Timestamp fechaRegistro = resultados.getTimestamp("fechaRegistro");
                            Timestamp fechaActualizacion = resultados.getTimestamp("fechaActualizacion");
                            int estado = resultados.getInt("estado");
                            String fotoPerfil = resultados.getString("fotoPerfil");

                            // Crear instancia de ClsModeloUsuario y agregar a la lista.
                            ClsModeloUsuario usuario = new ClsModeloUsuario(idUsuario, nombre, apellido,
                                    fechaNacimiento, genero, pais, ciudad, email, passwordHash, fechaRegistro,
                                    fechaActualizacion, estado, fotoPerfil);

                            ultimosUsuarios.add(usuario);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionDB.cerrarConexion();
            }
        } else {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
        }

        return ultimosUsuarios;
    }

}
