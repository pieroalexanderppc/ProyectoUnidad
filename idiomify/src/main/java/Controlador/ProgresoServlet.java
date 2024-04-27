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
                    page = "error.jsp";
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                       String action = request.getParameter("accion");
    
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
                case "agregarProgreso":
                    System.out.println("LLEGO A POST DE AGREGAR PROGRESO");
                        int idUsuario = Integer.parseInt(request.getParameter("FKidUsuario"));
                              System.out.println("es:"+request.getParameter("FKidUsuario"));
                    System.out.println("es:"+request.getParameter("FKidIdioma"));
                    System.out.println("es:"+request.getParameter("FKidCurso"));
                    System.out.println("es:"+request.getParameter("FKidLeccion"));
                    System.out.println("es:"+request.getParameter("puntuacion"));
                 String idLeccion = request.getParameter("FKidLeccion");
                 int puntuacion = Integer.parseInt(request.getParameter("puntuacion"));

                 ClsModeloDaoProgreso daoProgreso = new ClsModeloDaoProgreso();

                 // Verificar si ya existe un registro para la FKidLeccion proporcionada
                 boolean existeProgreso = daoProgreso.existeProgresoPorIdLeccion(idLeccion, idUsuario);

                 if (existeProgreso) {
                     // Si ya existe, actualiza la fecha de progreso y la puntuación
                     boolean actualizacionExitosa = daoProgreso.actualizarProgreso(idLeccion, puntuacion);

                     if (actualizacionExitosa) {
                         page = "app/index.jsp";
                     } else {
                         page = "error.jsp";
                     }
                 } else {
                     // Si no existe, crea un nuevo registro de progreso
                     ClsModeloProgreso nuevoProgreso = new ClsModeloProgreso();
                     nuevoProgreso.setIdFKidUsuario(Integer.parseInt(request.getParameter("FKidUsuario")));
                     nuevoProgreso.setIdFKidIdioma(Integer.parseInt(request.getParameter("FKidIdioma")));
                     nuevoProgreso.setFKidCurso(request.getParameter("FKidCurso"));
                     nuevoProgreso.setFKidLeccion(request.getParameter("FKidLeccion"));
                     nuevoProgreso.setPuntuacion(puntuacion);
                     
                 
                   
                

                     boolean insercionExitosa = daoProgreso.agregarProgreso(nuevoProgreso);

                     if (insercionExitosa) {
                         page = "app/index.jsp";
                     } else {
                         page = "error.jsp";
                     }
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
        return "Short description";
    }// </editor-fold>

}
