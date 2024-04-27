/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

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
@WebServlet(name = "ConsultaServlet", urlPatterns = {"/ConsultaServlet"})
public class ConsultaServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
    
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
                case "userforpais":
                    page = "admin/consultas/pais_usuario.jsp";
                   
                    
                    break;
                case "obtenerUsuariosPorGenero":
                  page = "admin/consultas/obtenerUsuariosPorGenero.jsp";
  
                    break;
                case "cantidadUsuario":
                  page = "admin/consultas/cantidadUsuario.jsp";
  
                    break;
                case "rangoedad":
                  page = "admin/consultas/rangoedad.jsp";
  
                    break;
                case "usuariomes":
                  page = "admin/consultas/usuariomes.jsp";
  
                    break;
                case "ultimosUsuarios":
                  page = "admin/consultas/ultimosUsuarios.jsp";
  
                    break;
                   case "listarAuditoria":
                    // Lógica para actualizar usuario
                    page = "admin/consultas/listarAuditoria.jsp"; // Redirigir a la página de listado
                    break; 
                case "demo":
                   
                            page = "cantidadUsuario.jsp";
        
                break;
                default:
                    page = "error.jsp";
                    break;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
