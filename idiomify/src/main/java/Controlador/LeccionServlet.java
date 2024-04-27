package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.ClsModeloLeccion;
import ModeloDAO.ClsModeloDaoLeccion;
import java.util.List;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "LeccionServlet", urlPatterns = {"/LeccionServlet"})
public class LeccionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
                String ruta = request.getParameter("ruta");
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
                case "listarLecciones":
                        if("app".equals(ruta)){
                            page = "app/learn/lecciones.jsp";
                        
                        }
                        else if("admin".equals(ruta)){
                            page = "admin/lecciones/listarLecciones.jsp";
                        
                        }else{
                          // Lógica para listar archivos
                            page = "app/index.jsp";
                            System.out.println("Estamos en listar idiomas");
                        }
                    break;
                case "agregarLecciones":
                    page = "admin/lecciones/agregarLecciones.jsp";
                    break;
                case "editarLecciones":
                    page = "admin/lecciones/editarLecciones.jsp";
                    break;
                case "eliminarLeccion":
                    String idLeccionEliminar = request.getParameter("idLeccion");
                    ClsModeloDaoLeccion daoLeccionEliminar = new ClsModeloDaoLeccion();
                    boolean exitoEliminacion = daoLeccionEliminar.eliminarLeccion(idLeccionEliminar);

                    if (exitoEliminacion) {
                        String idCurso = request.getParameter("idCurso");
                        page = "admin/lecciones/listarLecciones.jsp?idCurso=" + idCurso;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
                case "agregarLecciones":
                    ClsModeloLeccion nuevaLeccion = new ClsModeloLeccion();
                    nuevaLeccion.setIdLeccion(request.getParameter("idLeccion"));
                    nuevaLeccion.setFKidCurso(request.getParameter("FKidCurso"));
                    nuevaLeccion.setFKidIdioma(Integer.parseInt(request.getParameter("FKidIdioma")));
                    nuevaLeccion.setTitulo(request.getParameter("titulo"));
                    nuevaLeccion.setUrlBanner(request.getParameter("urlBanner"));

                    ClsModeloDaoLeccion daoLeccion = new ClsModeloDaoLeccion();
                    boolean exito = daoLeccion.agregarLeccion(nuevaLeccion);

                    if (exito) {
                        String idCurso = request.getParameter("FKidCurso");
                        page = "admin/lecciones/listarLecciones.jsp?idCurso=" + idCurso;
                    } else {
                        page = "error.jsp";
                    }
                    break;
                case "actualizarLeccion":
                    String idLeccion = request.getParameter("idLeccion");
                    String FKidCurso = request.getParameter("FKidCurso");
                    int FKidIdioma = Integer.parseInt(request.getParameter("FKidIdioma"));
                    String titulo = request.getParameter("titulo");
                    String urlBanner = request.getParameter("urlBanner");

                    ClsModeloLeccion leccionActualizada = new ClsModeloLeccion(idLeccion, FKidCurso, FKidIdioma, titulo, urlBanner);

                    ClsModeloDaoLeccion daoLeccionActualizar = new ClsModeloDaoLeccion();
                    boolean exitoActualizacion = daoLeccionActualizar.actualizarLeccion(leccionActualizada);

                    if (exitoActualizacion) {
                       page = "admin/lecciones/listarLecciones.jsp?idCurso=" + FKidCurso;
                        System.out.println("rutaa:"+page);
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
