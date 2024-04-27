package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.ClsModeloPregunta;
import ModeloDAO.ClsModeloDaoPregunta;
import java.util.List;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "PreguntaServlet", urlPatterns = {"/PreguntaServlet"})
public class PreguntaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String ruta = request.getParameter("ruta");
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
                case "listarPreguntas":
                        if("app".equals(ruta)){
                            page = "app/learn/LeccionIdiomify/appidiomify.jsp";
                        
                        }
                        else if("admin".equals(ruta)){
                            page = "admin/preguntas/listarPreguntas.jsp";
                        
                        }else{
                          // Lógica para listar archivos
                            page = "admin/preguntas/listarPreguntas.jsp";
                            System.out.println("Estamos en listar idiomas");
                        }

                    break;
                case "agregarPreguntas":
                    page = "admin/preguntas/agregarPreguntas.jsp";
                    break;
                case "editarPreguntas":
                    page = "admin/preguntas/editarPreguntas.jsp";
                    break;
                case "eliminarPreguntas":
                    String idPreguntaEliminarstr = request.getParameter("idPregunta");
                    int idPreguntaEliminar = Integer.parseInt(idPreguntaEliminarstr);
                    ClsModeloDaoPregunta daoPreguntaEliminar = new ClsModeloDaoPregunta();
                    boolean exitoEliminacion = daoPreguntaEliminar.eliminarPregunta(idPreguntaEliminar);


                    if (exitoEliminacion) {
                        String idLeccion = request.getParameter("idLeccion");
                        page = "admin/preguntas/listarPreguntas.jsp?idLeccion=" + idLeccion;
                    } else {
                        page = "error.jsp";
                    }
                    break;
                case "demo":
                        if("app".equals(ruta)){
                            page = "app/learn/LeccionIdiomify/appidiomify.jsp";
                        
                        }else{
                          // Lógica para listar archivos
                            page = "app/index.jsp";
                            System.out.println("Estamos en listar idiomas");
                        }
                break;
                default:
                    page = "error.jsp";
                    break;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
                case "agregarPregunta":
                    ClsModeloPregunta nuevaPregunta = new ClsModeloPregunta();
                    String idPreguntaEliminarstr = request.getParameter("idPregunta");
                    int idPreguntaEliminar = Integer.parseInt(idPreguntaEliminarstr);
                    nuevaPregunta.setIdPregunta(idPreguntaEliminar);
                    nuevaPregunta.setFKidLeccion(request.getParameter("FKidLeccion"));
                    nuevaPregunta.setEnunciado(request.getParameter("enunciado"));
                    nuevaPregunta.setActividad(request.getParameter("actividad"));
                    nuevaPregunta.setRespuesta(request.getParameter("respuesta"));
                    nuevaPregunta.setUrlIlustracion(request.getParameter("urlIlustracion"));
                    nuevaPregunta.setUrlAudio(request.getParameter("urlAudio"));

                    ClsModeloDaoPregunta daoPregunta = new ClsModeloDaoPregunta();
                    boolean exito = daoPregunta.agregarPregunta(nuevaPregunta);

                    if (exito) {
                        String idLeccion = request.getParameter("FKidLeccion");
                        page = "admin/preguntas/listarPreguntas.jsp?idLeccion=" + idLeccion;
                    } else {
                        page = "error.jsp";
                    }
                    break;
                case "actualizarPregunta":
                    String idPreguntaactualizarrstr = request.getParameter("idPregunta");
                     int idPregunta = Integer.parseInt(idPreguntaactualizarrstr);
                   
            
                    String FKidLeccion = request.getParameter("FKidLeccion");
                    String enunciado = request.getParameter("enunciado");
                    String actividad = request.getParameter("actividad");
                    String respuesta = request.getParameter("respuesta");
                    String urlIlustracion = request.getParameter("urlIlustracion");
                    String urlAudio = request.getParameter("urlAudio");

                    ClsModeloPregunta preguntaActualizada = new ClsModeloPregunta(idPregunta, FKidLeccion, enunciado, actividad, respuesta, urlIlustracion, urlAudio);

                    ClsModeloDaoPregunta daoPreguntaActualizar = new ClsModeloDaoPregunta();
                    boolean exitoActualizacion = daoPreguntaActualizar.actualizarPregunta(preguntaActualizada);

                    if (exitoActualizacion) {
                        page = "admin/preguntas/listarPreguntas.jsp?idLeccion=" + FKidLeccion;
                    } else {
                        page = "error.jsp";
                    }
                    break;
                default:
                    page = "error.jsp";
                    break;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Descripción corta";
    }
}
