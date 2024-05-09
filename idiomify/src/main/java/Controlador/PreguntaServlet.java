package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.ClsModeloPregunta;
import ModeloDAO.ClsModeloDaoPregunta;

import javax.servlet.RequestDispatcher;

@WebServlet(name = "PreguntaServlet", urlPatterns = {"/PreguntaServlet"})
public class PreguntaServlet extends HttpServlet {
    public static final String ID_PREGUNTA_PARAM = "idPregunta";
    public static final String LISTAR_PREGUNTAS = "admin/preguntas/listarPreguntas.jsp?idLeccion=";
    public static final String ERROR ="error.jsp";
    public static final String FK_idLeccion ="FKidLeccion";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String ruta = request.getParameter("ruta");
        String page = "index.jsp";
       
    
        if (action != null) {
            switch (action) {
                case "listarPreguntas":
                    if ("app".equals(ruta)) {
                        page = "app/learn/LeccionIdiomify/appidiomify.jsp";
                    } else if ("admin".equals(ruta)) {
                        page = "admin/preguntas/listarPreguntas.jsp";
                    } else {
                        page = "admin/preguntas/listarPreguntas.jsp";
                    }
                    break;
                case "agregarPreguntas":
                    page = "admin/preguntas/agregarPreguntas.jsp";
                    break;
                case "editarPreguntas":
                    page = "admin/preguntas/editarPreguntas.jsp";
                    break;
                case "eliminarPreguntas":
                    String idPreguntaEliminarstr = request.getParameter(ID_PREGUNTA_PARAM);
                    int idPreguntaEliminar = Integer.parseInt(idPreguntaEliminarstr);
                    ClsModeloDaoPregunta daoPreguntaEliminar = new ClsModeloDaoPregunta();
                    boolean exitoEliminacion = daoPreguntaEliminar.eliminarPregunta(idPreguntaEliminar);
                    page = exitoEliminacion ?LISTAR_PREGUNTAS + request.getParameter("idLeccion") : ERROR;
                    break;
                case "demo":
                    if ("app".equals(ruta)) {
                        page = "app/learn/LeccionIdiomify/appidiomify.jsp";
                    } else {
                        page = "app/index.jsp";
                    }
                    break;
                default:
                    page = ERROR;
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
                    String idPreguntaEliminarstr = request.getParameter(ID_PREGUNTA_PARAM);
                    int idPreguntaEliminar = Integer.parseInt(idPreguntaEliminarstr);
                    nuevaPregunta.setIdPregunta(idPreguntaEliminar);
                    nuevaPregunta.setFKidLeccion(request.getParameter(FK_idLeccion));
                    nuevaPregunta.setEnunciado(request.getParameter("enunciado"));
                    nuevaPregunta.setActividad(request.getParameter("actividad"));
                    nuevaPregunta.setRespuesta(request.getParameter("respuesta"));
                    nuevaPregunta.setUrlIlustracion(request.getParameter("urlIlustracion"));
                    nuevaPregunta.setUrlAudio(request.getParameter("urlAudio"));

                    ClsModeloDaoPregunta daoPregunta = new ClsModeloDaoPregunta();
                    boolean exito = daoPregunta.agregarPregunta(nuevaPregunta);

                    if (exito) {
                        String idLeccion = request.getParameter(FK_idLeccion);
                        page = LISTAR_PREGUNTAS+ idLeccion;
                    } else {
                        page = ERROR;
                    }
                    break;
                case "actualizarPregunta":
                    String idPreguntaactualizarrstr = request.getParameter(ID_PREGUNTA_PARAM);
                     int idPregunta = Integer.parseInt(idPreguntaactualizarrstr);
                   
            
                    String FKidLeccion = request.getParameter(FK_idLeccion);
                    String enunciado = request.getParameter("enunciado");
                    String actividad = request.getParameter("actividad");
                    String respuesta = request.getParameter("respuesta");
                    String urlIlustracion = request.getParameter("urlIlustracion");
                    String urlAudio = request.getParameter("urlAudio");

                    ClsModeloPregunta preguntaActualizada = new ClsModeloPregunta(idPregunta, FKidLeccion, enunciado, actividad, respuesta, urlIlustracion, urlAudio);

                    ClsModeloDaoPregunta daoPreguntaActualizar = new ClsModeloDaoPregunta();
                    boolean exitoActualizacion = daoPreguntaActualizar.actualizarPregunta(preguntaActualizada);

                    if (exitoActualizacion) {
                        page = LISTAR_PREGUNTAS + FKidLeccion;
                    } else {
                        page = ERROR;
                    }
                    break;
                default:
                    page = ERROR;
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
