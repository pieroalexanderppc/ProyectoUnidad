/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.ClsModeloProgreso;
import ModeloDAO.ClsModeloDaoProgreso;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ProgresoServlet", urlPatterns = {"/ProgresoServlet"})
public class ProgresoServlet extends HttpServlet {

    public static final String ERROR ="error.jsp";
    
    public static final String FK_idUsuario = "FKidUsuario";
    public static final String FK_idLeccion = "FKidLeccion";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                String action = request.getParameter("accion");
    
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
                case "completado":
                    page = "admin/consultas/pais_usuario.jsp";
                   
                    
                    break;
                    case "listarmiscuros":             
                    page = "app/miscursos.jsp";
                    break;
                   

           
                default:
                    page = ERROR;
                    break;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
   
    }
    
            
//        CREATE TABLE tbProgreso (
//    idProgreso INT AUTO_INCREMENT PRIMARY KEY,
//    FKidUsuario INT NOT NULL,
//    FKidIdioma INT NOT NULL,
//    FKidCurso VARCHAR(6) NOT NULL,
//    FKidLeccion VARCHAR(9) NOT NULL,
//    fechaProgreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    puntuacion INT DEFAULT 0,
//    FOREIGN KEY (FKidUsuario) REFERENCES tbUsuarios(idUsuario),
//    FOREIGN KEY (FKidIdioma) REFERENCES tbIdiomas(idIdioma),
//    FOREIGN KEY (FKidCurso) REFERENCES tbCursos(idCurso),
//    FOREIGN KEY (FKidLeccion) REFERENCES tbLecciones(idLeccion)
//);

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("accion");
    String page = "index.jsp";

    if ("agregarProgreso".equals(action)) {
        int idUsuario = Integer.parseInt(request.getParameter(FK_idUsuario));
        String idLeccion = request.getParameter(FK_idLeccion);
        int puntuacion = Integer.parseInt(request.getParameter("puntuacion"));
        ClsModeloDaoProgreso daoProgreso = new ClsModeloDaoProgreso();

        boolean existeProgreso = daoProgreso.existeProgresoPorIdLeccion(idLeccion, idUsuario);
        if (existeProgreso) {
            boolean actualizacionExitosa = daoProgreso.actualizarProgreso(idLeccion, puntuacion);
            page = actualizacionExitosa ? "app/index.jsp" : ERROR;
        } else {
            ClsModeloProgreso nuevoProgreso = new ClsModeloProgreso();
            nuevoProgreso.setIdFKidUsuario(idUsuario);
            nuevoProgreso.setIdFKidIdioma(Integer.parseInt(request.getParameter("FKidIdioma")));
            nuevoProgreso.setFKidCurso(request.getParameter("FKidCurso"));
            nuevoProgreso.setFKidLeccion(idLeccion);
            nuevoProgreso.setPuntuacion(puntuacion);
            boolean insercionExitosa = daoProgreso.agregarProgreso(nuevoProgreso);
            page = insercionExitosa ? "app/index.jsp" : ERROR;
        }
    } else {
        page = ERROR;
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
    dispatcher.forward(request, response);
}
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
