/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.ClsModeloUsuario;
import ModeloDAO.ClsModeloDaoUsuario;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
import Controlador.LoginServlet;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String page = "index.jsp"; // Página predeterminada

        if (action != null) {
            switch (action) {
                case "listarUsuarios":
                    // Lógica para listar usuarios
                    page = "admin/usuarios/listarUsuarios.jsp";
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
                    page = "admin/usuarios/listarUsuarios.jsp";
                    break;
                  
    case "exportarPdf":
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

    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    } finally {
        if (documentUsuarios != null && documentUsuarios.isOpen()) {
            documentUsuarios.close();
        }
        if (writerUsuarios != null) {
            writerUsuarios.close();
        }
    }

    break;




                default:
                    // Lógica para manejar otros casos o errores
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
        String page = "index.jsp"; // Página predeterminada

        if (action != null) {
            switch (action) {
                case "registrarUsuario":
                   // Obtén los datos del formulario de registro
                   String nombre = request.getParameter("nombre");
                   String apellido = request.getParameter("apellido");
                   String fechaNacimiento = request.getParameter("fechaNacimiento");
                   String genero = request.getParameter("genero");
                   String pais = request.getParameter("pais");
                   String ciudad = request.getParameter("ciudad");
                   String email = request.getParameter("email");
               
                   String password = request.getParameter("password"); // Asegúrate de encriptar la contraseña antes de enviarla a la base de datos
                   int estado = 1; // Puedes establecer el estado inicial según tus necesidades // Puedes manejar la imagen de perfil si es necesario
                   String fotoPerfil = "fotos/fotodefault.png";
                   // Crea un objeto ClsModeloUsuario con los datos del formulario
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
                   
           HttpSession sessiontxt = request.getSession(); 
                   String captcha = request.getParameter("captcha");
                   String captchaTextFromSessiontxt = (String) sessiontxt.getAttribute("captchaText");
                    System.out.println(captchaTextFromSessiontxt);
                    if (captcha == null || captchaTextFromSessiontxt == null || !captcha.equalsIgnoreCase(captchaTextFromSessiontxt)) {
                        // CAPTCHA incorrecto
                        captcha="1";
                    }


                   // Llama al método registrarUsuario de ClsModeloDaoUsuario
                   ClsModeloDaoUsuario daoUsuariotxt = new ClsModeloDaoUsuario();
                   boolean exitoRegistro = daoUsuariotxt.registrarUsuario(nuevoUsuario);
                   
                   ClsModeloDaoUsuario daoUsuarioAutx = new ClsModeloDaoUsuario();
                    ClsModeloUsuario usuarioAutenticadotx = daoUsuarioAutx.autenticarUsuario(email, password,captcha);

                   if (exitoRegistro) {
                       // El registro fue exitoso
                       // Puedes redirigir a una página de éxito o a la página de inicio de sesión
                        request.getSession().setAttribute("usuarioAutenticado", usuarioAutenticadotx);
                       page = "app/index.jsp";
                       System.out.println("llego a  exito");
                   } else {
                       // El registro falló, puedes redirigir a una página de error o mostrar un mensaje
                       page = "error.jsp";
                       System.out.println("aqui error despues del esxito");
                   }
                   break;

                case "actualizarUsuario":
                    // Obtén los parámetros del formulario
                    int idUsuarioActualizar = Integer.parseInt(request.getParameter("idUsuario"));
                    String nuevoNombre = request.getParameter("nombre");
                    String nuevoApellido = request.getParameter("apellido");
                    String nuevaFechaNacimiento = request.getParameter("fechaNacimiento");
                    String nuevoGenero = request.getParameter("genero");
                    String nuevoPais = request.getParameter("pais");
                    String nuevaCiudad = request.getParameter("ciudad");
                    String nuevoEmail = request.getParameter("email");
                    String nuevoPassword = request.getParameter("password");
                   String parametroEstado = request.getParameter("estado");
                    int nuevoEstado = parametroEstado != null ? Integer.parseInt(parametroEstado) : 0;

                     
                    System.out.println("esto es el estado que llega:"+request.getParameter("estado"));
                    String nuevaFotoPerfil = request.getParameter("fotoPerfil");
                    // Agrega otros campos según tus necesidades

                    // Crea un objeto ClsModeloUsuario con la nueva información
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
                    // Establece otros campos según tus necesidades

                    // Actualiza el usuario en la base de datos
                    ClsModeloDaoUsuario daoUsuarioupdate = new ClsModeloDaoUsuario();
                    boolean actualizacionExitosa = daoUsuarioupdate.actualizarUsuario(usuarioActualizado);

                    if (actualizacionExitosa) {
                        // La actualización fue exitosa
                        List<ClsModeloUsuario> usuariosActualizados = daoUsuarioupdate.obtenerTodosUsuarios();
                        request.setAttribute("usuarios", usuariosActualizados);
                        page = "admin/usuarios/listarUsuarios.jsp";
                    } else {
                        // La actualización falló, puedes manejarlo de acuerdo a tus necesidades
                        page = "admin/usuarios/errorActualizacion.jsp";
                    }
                    break;

                case "autenticarUsuario":
                    String emails = request.getParameter("email");
                    String passwords = request.getParameter("password");
                    HttpSession session = request.getSession(); 
                   String captchaText = request.getParameter("captcha");
                   String captchaTextFromSession = (String) session.getAttribute("captchaText");
                            System.out.println(captchaTextFromSession);
                            if (captchaText == null || captchaTextFromSession == null || !captchaText.equalsIgnoreCase(captchaTextFromSession)) {
                                // CAPTCHA incorrecto
                                captchaText="1";
                            }

                    // Llama al método autenticarUsuario de ClsModeloDaoUsuario
                    ClsModeloDaoUsuario daoUsuarioAu = new ClsModeloDaoUsuario();
                    ClsModeloUsuario usuarioAutenticado = daoUsuarioAu.autenticarUsuario(emails, passwords,captchaText);

                    if (usuarioAutenticado != null) {
                        // La autenticación fue exitosa
                        // Puedes almacenar la información del usuario en la sesión si es necesario
                        request.getSession().setAttribute("usuarioAutenticado", usuarioAutenticado);
                        page = "app/index.jsp";// Redirige a la página del perfil del usuario
                    } else {
                        // La autenticación falló, puedes redirigir a una página de error o mostrar un mensaje
                        page = "error.jsp";
                    }
                    break;
                                    case "editarUsuarioPerfil":
                    int idUsuarioActualizar2 = Integer.parseInt(request.getParameter("idUsuario"));
                    String nuevoNombre2 = request.getParameter("nombre");
                    String nuevoApellido2 = request.getParameter("apellido");
                    String nuevoGenero2 = request.getParameter("genero");
                    
                    ClsModeloUsuario usuarioActualizado2 = new ClsModeloUsuario();
                    usuarioActualizado2.setIdUsuario(idUsuarioActualizar2);
                    usuarioActualizado2.setNombre(nuevoNombre2);
                    usuarioActualizado2.setApellido(nuevoApellido2);
                    usuarioActualizado2.setGenero(nuevoGenero2);
                    
                    ClsModeloDaoUsuario daoUsuarioupdate2 = new ClsModeloDaoUsuario();
                    boolean actualizacionExitosa2 = daoUsuarioupdate2.actualizarUsuarioPerfil(usuarioActualizado2);

                    if (actualizacionExitosa2) {
                        // La actualización fue exitosa
                        List<ClsModeloUsuario> usuariosActualizados = daoUsuarioupdate2.obtenerTodosUsuarios();
                        request.setAttribute("usuarios", usuariosActualizados);
                        page = "app/perfil.jsp";
                    } else {
                        // La actualización falló, puedes manejarlo de acuerdo a tus necesidades
                        page = "app/perfil.jsp";
                    }
                    break;
                    
                    case "insertarUsuarioCSV":
    // Verifica si la solicitud contiene datos multipartes (archivo adjunto)
    if (ServletFileUpload.isMultipartContent(request)) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    // Lógica para procesar el contenido del archivo CSV
                    try (CSVReader csvReader = new CSVReader(
                            new BufferedReader(new InputStreamReader(item.getInputStream())))) {
                        csvReader.skip(1);
                        String[] linea;
                        while ((linea = csvReader.readNext()) != null) {
                            ClsModeloUsuario nuevoUsuariocsv = new ClsModeloUsuario();
                            nuevoUsuariocsv.setNombre(linea[0]);
                            nuevoUsuariocsv.setApellido(linea[1]);
                            nuevoUsuariocsv.setFechaNacimiento(linea[2]);
                            nuevoUsuariocsv.setGenero(linea[3]);
                            nuevoUsuariocsv.setPais(linea[4]);
                            nuevoUsuariocsv.setCiudad(linea[5]);
                            nuevoUsuariocsv.setEmail(linea[6]);
                            
                            // Encripta el password antes de almacenarlo en la base de datos
                            String passwordsk = linea[7];
                            String hashedPassword = loginServlet.hashPassword(passwordsk);
                            nuevoUsuariocsv.setPasswordHash(hashedPassword);
                            
                            nuevoUsuariocsv.setEstado(Integer.parseInt(linea[8]));
                            nuevoUsuariocsv.setFotoPerfil(linea[9]);

                            ClsModeloDaoUsuario daoUsuariocsv = new ClsModeloDaoUsuario();
                            daoUsuariocsv.registrarUsuario(nuevoUsuariocsv);
                        }
                    } catch (CsvValidationException ex) {
                        Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (FileUploadException ex) {
        } catch (IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Redirige a la página de listado de usuarios después de la importación
            page = "admin/usuarios/listarUsuarios.jsp";
        }
    } else {
        page = "admin/usuarios/listarUsuarios.jsp";
        System.out.println("No se encontró el archivo CSV en la solicitud");
        // Manejar el caso en que no se encuentra el archivo CSV
    }
    break;

                

                default:
                    // Lógica para manejar otros casos o errores
                    page = "error.jsp";
                      System.out.println("llego a error de frente");
                    break;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
