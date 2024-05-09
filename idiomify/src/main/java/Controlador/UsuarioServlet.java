/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.ClsModeloUsuario;
import ModeloDAO.ClsModeloDaoUsuario;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//PARA ENCRIPTAR

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
      LoginServlet loginServlet = new LoginServlet();
      public static final String listarUsuarios ="admin/usuarios/listarUsuarios.jsp";
      public static final String ERROR ="error.jsp";
      public static final String nombreString ="nombre";
      public static final String apellidoString ="apellido";
      public static final String generoString ="genero";
      public static final String emailString ="email";
      public static final String passwordString ="password";

      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
          String action = request.getParameter("accion");
          String page = "index.jsp"; // Página predeterminada
      
          if (action != null) {
              switch (action) {
                  case "listarUsuarios":
                      // Lógica para listar usuarios
                      page = listarUsuarios;
                      break;
                  case "agregarUsuarios":
                      // Lógica para agregar usuario
                      page = "admin/usuarios/agregarUsuarios.jsp";
                      break;
                  case "editarUsuarios":
                      // Lógica para editar usuario
                      page = "admin/usuarios/editarUsuarios.jsp";
                      break;
                  case "eliminarUsuario":
                      // Lógica para eliminar usuario
                      page = listarUsuarios;
                      break;
                  case "exportarPdf":
                      try {
                        exportarPdfUsuarios(request, response);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                      return; // Salir del método después de exportar el PDF
                  default:
                      // Lógica para manejar otros casos o errores
                      page = ERROR;
                      break;
              }
          }
      
          RequestDispatcher dispatcher = request.getRequestDispatcher(page);
          dispatcher.forward(request, response);
      }
      
      private void exportarPdfUsuarios(HttpServletRequest request, HttpServletResponse response)
              throws IOException, DocumentException {
          ClsModeloDaoUsuario daoUsuario = new ClsModeloDaoUsuario();
          List<ClsModeloUsuario> usuarios = daoUsuario.obtenerTodosUsuarios();
      
          response.setContentType("application/pdf");
          response.setHeader("Content-Disposition", "attachment; filename=ListadoUsuarios.pdf");
      
          Document documentUsuarios = new Document();
          PdfWriter writerUsuarios = null;
      
          try {
              writerUsuarios = PdfWriter.getInstance(documentUsuarios, response.getOutputStream());
      
              documentUsuarios.open();
      
              // Agregar el logo y encabezado al documento desde una URL
              String imageUrl = "https://i.ibb.co/JsQy6xm/idiomifylogo.png";
              Image logo = Image.getInstance(new URL(imageUrl));
              documentUsuarios.add(logo);
              documentUsuarios.add(new Paragraph("Idiomify tu aplicación para conocer nuevos idiomas"));
              documentUsuarios.add(new Paragraph("             "));
      
              // Tabla con encabezados
              PdfPTable tableUsuarios = new PdfPTable(13);
              tableUsuarios.addCell("ID Usuario");
              tableUsuarios.addCell("Nombre");
              tableUsuarios.addCell("Apellido");
              tableUsuarios.addCell("Fecha de Nacimiento");
              tableUsuarios.addCell("Género");
              tableUsuarios.addCell("País");
              tableUsuarios.addCell("Ciudad");
              tableUsuarios.addCell("Email");
              tableUsuarios.addCell("Password Hash");
              tableUsuarios.addCell("Fecha de Registro");
              tableUsuarios.addCell("Fecha de Actualización");
              tableUsuarios.addCell("Estado");
              tableUsuarios.addCell("URL Foto de Perfil");
      
              // Rellenar la tabla con la información de los usuarios
              for (ClsModeloUsuario usuario : usuarios) {
                  tableUsuarios.addCell(String.valueOf(usuario.getIdUsuario()));
                  tableUsuarios.addCell(usuario.getNombre());
                  tableUsuarios.addCell(usuario.getApellido());
                  tableUsuarios.addCell(usuario.getFechaNacimiento());
                  tableUsuarios.addCell(usuario.getGenero());
                  tableUsuarios.addCell(usuario.getPais());
                  tableUsuarios.addCell(usuario.getCiudad());
                  tableUsuarios.addCell(usuario.getEmail());
                  tableUsuarios.addCell(usuario.getPasswordHash());
                  tableUsuarios.addCell(String.valueOf(usuario.getFechaRegistro()));
                  tableUsuarios.addCell(String.valueOf(usuario.getFechaActualizacion()));
                  tableUsuarios.addCell(String.valueOf(usuario.getEstado()));
                  tableUsuarios.addCell(usuario.getFotoPerfil());
              }
      
              // Agregar la tabla al documento
              documentUsuarios.add(tableUsuarios);
      
              // Pie de página
              documentUsuarios.add(new Paragraph("        "));
              documentUsuarios.add(new Paragraph("Fecha de creación: " + new Date()));
              String nombreUsuario = "Idiomify con Amor :)";
              documentUsuarios.add(new Paragraph("De: " + nombreUsuario));
      
          } catch (IOException | DocumentException e) {
              e.printStackTrace();
          } finally {
              if (documentUsuarios != null && documentUsuarios.isOpen()) {
                  documentUsuarios.close();
              }
              if (writerUsuarios != null) {
                  writerUsuarios.close();
              }
          }
      }

      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
          String action = request.getParameter("accion");
          String page = "index.jsp";
      
          if ("registrarUsuario".equals(action)) {
              String captcha = request.getParameter("captcha");
              HttpSession session = request.getSession();
              String captchaTextFromSession = (String) session.getAttribute("captchaText");
              boolean captchaValido = captcha != null && captchaTextFromSession != null &&
                      captcha.equalsIgnoreCase(captchaTextFromSession);
      
              if (captchaValido) {
                  ClsModeloUsuario nuevoUsuario = construirUsuarioDesdeFormulario(request);
                  ClsModeloDaoUsuario daoUsuario = new ClsModeloDaoUsuario();
                  boolean exitoRegistro = daoUsuario.registrarUsuario(nuevoUsuario);
      
                  if (exitoRegistro) {
                      ClsModeloUsuario usuarioAutenticado = daoUsuario.autenticarUsuario(nuevoUsuario.getEmail(),
                              nuevoUsuario.getPasswordHash(), captcha);
                      request.getSession().setAttribute("usuarioAutenticado", usuarioAutenticado);
                      page = "app/index.jsp";
                  } else {
                      page = ERROR;
                  }
              } else {
                  page = ERROR;
              }
          } else if ("actualizarUsuario".equals(action)) {
              ClsModeloUsuario usuarioActualizado = construirUsuarioActualizadoDesdeFormulario(request);
              ClsModeloDaoUsuario daoUsuario = new ClsModeloDaoUsuario();
              boolean actualizacionExitosa = daoUsuario.actualizarUsuario(usuarioActualizado);
      
              if (actualizacionExitosa) {
                  List<ClsModeloUsuario> usuariosActualizados = daoUsuario.obtenerTodosUsuarios();
                  request.setAttribute("usuarios", usuariosActualizados);
                  page = listarUsuarios;
              } else {
                  page = "admin/usuarios/errorActualizacion.jsp";
              }
          } else if ("autenticarUsuario".equals(action)) {
              String email = request.getParameter(emailString);
              String password = request.getParameter(passwordString);
              HttpSession session = request.getSession();
              String captchaText = request.getParameter("captcha");
              String captchaTextFromSession = (String) session.getAttribute("captchaText");
              boolean captchaValido = captchaText != null && captchaTextFromSession != null &&
                      captchaText.equalsIgnoreCase(captchaTextFromSession);
      
              if (captchaValido) {
                  ClsModeloDaoUsuario daoUsuario = new ClsModeloDaoUsuario();
                  ClsModeloUsuario usuarioAutenticado = daoUsuario.autenticarUsuario(email, password, captchaText);
      
                  if (usuarioAutenticado != null) {
                      request.getSession().setAttribute("usuarioAutenticado", usuarioAutenticado);
                      page = "app/index.jsp";
                  } else {
                      page = ERROR;
                  }
              } else {
                  page = ERROR;
              }
          } else if ("editarUsuarioPerfil".equals(action)) {
              ClsModeloUsuario usuarioActualizado = construirUsuarioActualizadoDesdeFormulario(request);
              ClsModeloDaoUsuario daoUsuario = new ClsModeloDaoUsuario();
              boolean actualizacionExitosa = daoUsuario.actualizarUsuarioPerfil(usuarioActualizado);
      
              if (actualizacionExitosa) {
                  List<ClsModeloUsuario> usuariosActualizados = daoUsuario.obtenerTodosUsuarios();
                  request.setAttribute("usuarios", usuariosActualizados);
                  page = "app/perfil.jsp";
              } else {
                  page = "app/perfil.jsp";
              }
          } else if ("insertarUsuarioCSV".equals(action)) {
              page = procesarInsertarUsuarioCSV(request);
          } else {
              page = ERROR;
          }
      
          RequestDispatcher dispatcher = request.getRequestDispatcher(page);
          dispatcher.forward(request, response);
      }
      
      public ClsModeloUsuario construirUsuarioDesdeFormulario(HttpServletRequest request) {
          String nombre = request.getParameter(nombreString);
          String apellido = request.getParameter(apellidoString);
          String fechaNacimiento = request.getParameter("fechaNacimiento");
          String genero = request.getParameter(generoString);
          String pais = request.getParameter("pais");
          String ciudad = request.getParameter("ciudad");
          String email = request.getParameter(emailString);
          String password = request.getParameter(passwordString);
          int estado = 1;
          String fotoPerfil = "fotos/fotodefault.png";
      
          ClsModeloUsuario nuevoUsuario = new ClsModeloUsuario();
          nuevoUsuario.setNombre(nombre);
          nuevoUsuario.setApellido(apellido);
          nuevoUsuario.setFechaNacimiento(fechaNacimiento);
          nuevoUsuario.setGenero(genero);
          nuevoUsuario.setPais(pais);
          nuevoUsuario.setCiudad(ciudad);
          nuevoUsuario.setEmail(email);
          nuevoUsuario.setPasswordHash(password);
          nuevoUsuario.setEstado(estado);
          nuevoUsuario.setFotoPerfil(fotoPerfil);
      
          return nuevoUsuario;
      }
      
      private ClsModeloUsuario construirUsuarioActualizadoDesdeFormulario(HttpServletRequest request) {
          int idUsuarioActualizar = Integer.parseInt(request.getParameter("idUsuario"));
          String nuevoNombre = request.getParameter(nombreString);
          String nuevoApellido = request.getParameter(apellidoString);
          String nuevaFechaNacimiento = request.getParameter("fechaNacimiento");
          String nuevoGenero = request.getParameter(generoString);
          String nuevoPais = request.getParameter("pais");
          String nuevaCiudad = request.getParameter("ciudad");
          String nuevoEmail = request.getParameter(emailString);
          String nuevoPassword = request.getParameter(passwordString);
          String parametroEstado = request.getParameter("estado");
          int nuevoEstado = parametroEstado != null ? Integer.parseInt(parametroEstado) : 0;
          String nuevaFotoPerfil = request.getParameter("fotoPerfil");
      
          ClsModeloUsuario usuarioActualizado = new ClsModeloUsuario();
          usuarioActualizado.setIdUsuario(idUsuarioActualizar);
          usuarioActualizado.setNombre(nuevoNombre);
          usuarioActualizado.setApellido(nuevoApellido);
          usuarioActualizado.setFechaNacimiento(nuevaFechaNacimiento);
          usuarioActualizado.setGenero(nuevoGenero);
          usuarioActualizado.setPais(nuevoPais);
          usuarioActualizado.setCiudad(nuevaCiudad);
          usuarioActualizado.setEmail(nuevoEmail);
          usuarioActualizado.setPasswordHash(nuevoPassword);
          usuarioActualizado.setEstado(nuevoEstado);
          usuarioActualizado.setFotoPerfil(nuevaFotoPerfil);
      
          return usuarioActualizado;
      }

      private String procesarInsertarUsuarioCSV(HttpServletRequest request) {
        String page = listarUsuarios;
    
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
    
            try {
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        try (CSVReader csvReader = new CSVReader(
                                new BufferedReader(new InputStreamReader(item.getInputStream())))) {
                            csvReader.skip(1);
                            String[] linea;
                            while ((linea = csvReader.readNext()) != null) {
                                ClsModeloUsuario nuevoUsuariocsv = construirUsuarioDesdeCSV(linea);
                                ClsModeloDaoUsuario daoUsuariocsv = new ClsModeloDaoUsuario();
                                daoUsuariocsv.registrarUsuario(nuevoUsuariocsv);
                            }
                        } catch (CsvValidationException ex) {
                            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } catch (FileUploadException | IOException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No se encontró el archivo CSV en la solicitud");
        }
    
        return page;
    }

      private ClsModeloUsuario construirUsuarioDesdeCSV(String[] linea) {
        ClsModeloUsuario nuevoUsuariocsv = new ClsModeloUsuario();
        nuevoUsuariocsv.setNombre(linea[0]);
        nuevoUsuariocsv.setApellido(linea[1]);
        nuevoUsuariocsv.setFechaNacimiento(linea[2]);
        nuevoUsuariocsv.setGenero(linea[3]);
        nuevoUsuariocsv.setPais(linea[4]);
        nuevoUsuariocsv.setCiudad(linea[5]);
        nuevoUsuariocsv.setEmail(linea[6]);
        String passwordsk = linea[7];
        String hashedPassword = loginServlet.hashPassword(passwordsk);
        nuevoUsuariocsv.setPasswordHash(hashedPassword);
        nuevoUsuariocsv.setEstado(Integer.parseInt(linea[8]));
        nuevoUsuariocsv.setFotoPerfil(linea[9]);
    
        return nuevoUsuariocsv;
}
}