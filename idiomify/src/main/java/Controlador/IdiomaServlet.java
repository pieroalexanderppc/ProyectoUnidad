/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.*;
import ModeloDAO.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//LIBRERIAS PARA EGENRAR PEF
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.Iterator;

@WebServlet(name = "IdiomaServlet", urlPatterns = { "/IdiomaServlet" })
public class IdiomaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("accion");
        String ruta = request.getParameter("ruta");
        String page = "index.jsp"; // Página predeterminada

        if (action != null) {
            switch (action) {
                case "listarIdiomas":
                    if ("app".equals(ruta)) {
                        page = "app/learn/idiomas.jsp";

                    } else if ("admin".equals(ruta)) {
                        page = "admin/idiomas/listarIdiomas.jsp";

                    } else {
                        // Lógica para listar archivos
                        page = "app/index.jsp";
                        System.out.println("Estamos en listar idiomas");
                    }
                    break;
                case "inicio":
                    if ("app".equals(ruta)) {
                       page = "app/index.jsp";

                    } else if ("admin".equals(ruta)) {
                        page = "admin/index.jsp";

                    } 
                    break;
                case "agregarIdiomas":
                    // Lógica para listar archivos
                    page = "admin/idiomas/agregarIdiomas.jsp";
                    break;
                case "editarIdiomas":
                    String idIdiomaStr = request.getParameter("idIdioma");

                    if (idIdiomaStr != null) {
                        try {
                            int idIdioma = Integer.parseInt(idIdiomaStr);
                            ClsModeloDaoIdioma dao = new ClsModeloDaoIdioma();
                            ClsModeloIdioma idioma = dao.obtenerIdiomaPorId(idIdioma);

                            if (idioma != null) {
                                request.setAttribute("idioma", idioma);
                                page = "admin/idiomas/editarIdiomas.jsp";
                            } else {
                                // Manejar el caso en que el idioma no se encontró en la base de datos
                                page = "error.jsp";
                            }
                        } catch (NumberFormatException e) {
                            // Manejar el caso en que el ID de idioma no es válido
                            page = "error.jsp";
                        }
                    } else {
                        // Manejar el caso en que no se proporcionó un ID de idioma
                        page = "error.jsp";
                    }
                    break;
                case "exportarPdf":
                    ClsModeloDaoIdioma dao = new ClsModeloDaoIdioma();
                    List<ClsModeloIdioma> idiomas = dao.obtenerTodosIdiomas();

                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=ListadoIdiomas.pdf");

                    Document document = new Document();
                    try {
                        PdfWriter.getInstance(document, response.getOutputStream());
                        document.open();

                        for (ClsModeloIdioma idioma : idiomas) {
                            document.add(new Paragraph("ID: " + idioma.getIdIdioma()));
                            document.add(new Paragraph("Nombre: " + idioma.getNombre()));
                            document.add(new Paragraph("Descripción: " + idioma.getDescripcion()));
                            document.add(new Paragraph("URL Banner: " + idioma.getUrlBanner()));
                            document.add(new Paragraph("----------------------------------------"));
                        }
                    } catch (DocumentException ex) {
                        Logger.getLogger(IdiomaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        if (document != null && document.isOpen()) {
                            document.close();
                        }
                    }
                    break;
                case "exportarCsv":
                    // Configurar la respuesta para la descarga de un archivo CSV
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition", "attachment; filename=ListadoIdiomas.csv");

                    // Crear un objeto CSVWriter para escribir el archivo CSV
                    try (CSVWriter writer = new CSVWriter(response.getWriter())) {
                        // Escribir la fila de encabezado del archivo CSV
                        writer.writeNext(new String[] { "ID", "Nombre", "Descripción", "URL Banner" });

                        // Obtener los datos de los idiomas
                        ClsModeloDaoIdioma daoIdioma = new ClsModeloDaoIdioma();
                        List<ClsModeloIdioma> IDIO = daoIdioma.obtenerTodosIdiomas();

                        // Escribir los datos de los idiomas al archivo CSV
                        for (ClsModeloIdioma idiomaExcel : IDIO) {
                            String[] rowData = {
                                    String.valueOf(idiomaExcel.getIdIdioma()),
                                    idiomaExcel.getNombre(),
                                    idiomaExcel.getDescripcion(),
                                    idiomaExcel.getUrlBanner()
                            };
                            writer.writeNext(rowData);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(IdiomaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case "eliminarIdioma":
                    // Obtener el ID del idioma a eliminar desde los parámetros de la solicitud
                    int idIdiomaEliminar = Integer.parseInt(request.getParameter("idIdioma"));

                    // Crear una instancia de ClsModeloDaoIdioma y utilizar el método para eliminar
                    // el idioma
                    ClsModeloDaoIdioma daoIdiomaEliminar = new ClsModeloDaoIdioma();
                    boolean exitoEliminacion = daoIdiomaEliminar.eliminarIdioma(idIdiomaEliminar);

                    if (exitoEliminacion) {
                        // La eliminación fue exitosa, puedes redirigir a la página de listado de
                        // idiomas o a otra página
                        page = "admin/idiomas/listarIdiomas.jsp";
                    } else {
                        // Manejar el caso en que la eliminación no fue exitosa (por ejemplo, mostrar un
                        // mensaje de error)
                        page = "error.jsp";
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

                case "agregarIdioma":
                    // Crear un objeto ClsModeloIdioma y asignar los datos del formulario
                    ClsModeloIdioma nuevoIdioma = new ClsModeloIdioma();
                    nuevoIdioma.setNombre(request.getParameter("nombre"));
                    nuevoIdioma.setDescripcion(request.getParameter("descripcion"));
                    nuevoIdioma.setUrlBanner(request.getParameter("urlBanner"));

                    // Crear una instancia de ClsModeloDaoIdioma y agregar el idioma
                    ClsModeloDaoIdioma daoIdioma = new ClsModeloDaoIdioma();
                    boolean exito = daoIdioma.agregarIdioma(nuevoIdioma);

                    if (exito) {
                        // La inserción fue exitosa, puedes redirigir a la página de listado de idiomas
                        page = "admin/idiomas/listarIdiomas.jsp";
                    } else {
                        // Manejar el caso en que la inserción no fue exitosa (por ejemplo, mostrar un
                        // mensaje de error)
                        page = "error.jsp";
                    }
                    break;
                case "actualizarIdioma":
                    // Obtener los datos actualizados del idioma desde los parámetros de la
                    // solicitud
                    int idIdioma = Integer.parseInt(request.getParameter("idIdioma"));
                    String nombre = request.getParameter("nombre");
                    String descripcion = request.getParameter("descripcion");
                    String urlBanner = request.getParameter("urlBanner");

                    // Crear un objeto ClsModeloIdioma con los datos actualizados
                    ClsModeloIdioma idiomaActualizado = new ClsModeloIdioma(idIdioma, nombre, descripcion, urlBanner);

                    // Crear una instancia de ClsModeloDaoIdioma y actualizar el idioma
                    ClsModeloDaoIdioma daoIdiomaActualizar = new ClsModeloDaoIdioma();
                    boolean exitoActualizacion = daoIdiomaActualizar.actualizarIdioma(idiomaActualizado);

                    if (exitoActualizacion) {
                        // La actualización fue exitosa, puedes redirigir a la página de listado de
                        // idiomas o a otra página
                        page = "admin/idiomas/listarIdiomas.jsp";
                    } else {
                        // Manejar el caso en que la actualización no fue exitosa (por ejemplo, mostrar
                        // un mensaje de error)
                        page = "error.jsp";
                    }
                    break;
                case "insertarIdiomaCSV":
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
                                            ClsModeloIdioma nuevoIdiomacsv = new ClsModeloIdioma();
                                            nuevoIdiomacsv.setNombre(linea[0]);
                                            nuevoIdiomacsv.setDescripcion(linea[1]);
                                            nuevoIdiomacsv.setUrlBanner(linea[2]);

                                            ClsModeloDaoIdioma daoIdiomacsv = new ClsModeloDaoIdioma();
                                            daoIdiomacsv.agregarIdioma(nuevoIdiomacsv);

                                        }
                                    }
                                }
                            }
                        } catch (FileUploadException ex) {
                        } catch (CsvValidationException | IOException ex) {
                            Logger.getLogger(IdiomaServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            // Redirige a la página de listado de idiomas después de la importación
                            page = "admin/idiomas/listarIdiomas.jsp";
                        }
                    } else {
                        page = "admin/idiomas/listarIdiomas.jsp";
                        System.out.println("No se encontró el archivo CSV en la solicitud");
                        // Manejar el caso en que no se encuentra el archivo CSV
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
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
