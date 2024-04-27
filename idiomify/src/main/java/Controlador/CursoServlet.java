package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Modelo.ClsModeloCurso;
import ModeloDAO.ClsModeloDaoCurso;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;


//PARA EL PDF
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Image;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;



import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.apache.commons.io.IOUtils.writer;

@WebServlet(name = "CursoServlet", urlPatterns = {"/CursoServlet"})
public class CursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String ruta = request.getParameter("ruta");
        String page = "index.jsp";

        if (action != null) {
            switch (action) {
            case "listarCursos":
                        if("app".equals(ruta)){
                            page = "app/learn/cursos.jsp";
                        
                        }else if("landing".equals(ruta)){
                            page = "landing/cursos.jsp";
                        
                        }
                        else if("admin".equals(ruta)){
                            page = "admin/cursos/listarCursos.jsp";
                        
                        }
                        else{
                          // Lógica para listar archivos
                            page = "app/index.jsp";
                            System.out.println("Estamos en listar idiomas");
                        }
                        break;
                case "agregarCursos":
                    page = "admin/cursos/agregarCursos.jsp";
                    break;
                case "editarCursos":
                     page = "admin/cursos/editarCursos.jsp";
              
                    break;
                case "eliminarCurso":
                    String idCursoEliminar = request.getParameter("idCurso");
                    ClsModeloDaoCurso daoCursoEliminar = new ClsModeloDaoCurso();
                    boolean exitoEliminacion = daoCursoEliminar.eliminarCurso(idCursoEliminar);

                    if (exitoEliminacion) {
                                                         
                        String idIdiomaStr = request.getParameter("idIdioma");
                        System.out.println("var de delete:"+idIdiomaStr);
                        page = "admin/cursos/listarCursos.jsp?idIdioma="+idIdiomaStr;
                    } else {
                        page = "error.jsp";
                    }
                    break;
                    
                    
case "exportarPdf":
    String idIdiomaStr = request.getParameter("idIdioma");
    int idIdioma = Integer.parseInt(idIdiomaStr);

    ClsModeloDaoCurso dao = new ClsModeloDaoCurso();
    List<ClsModeloCurso> cursos = dao.listarCursosPorIdIdioma(idIdioma);

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=ListadoCursos.pdf");

    Document document = new Document();
    PdfWriter writer = null; // Declarar aquí para que esté en un ámbito más amplio
    try {
        writer = PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Agregar el logo y encabezado al documento desde una URL
        String imageUrl = "https://i.ibb.co/JsQy6xm/idiomifylogo.png";
        Image logo = Image.getInstance(new URL(imageUrl));
        document.add(logo);
        document.add(new Paragraph("Idiomify tu aplicacion para conocer nuevos idiomas"));
        document.add(new Paragraph("             "));

        // Tabla con encabezados
        PdfPTable table = new PdfPTable(5);
        table.addCell("ID Curso");
        table.addCell("ID Idioma");
        table.addCell("Nombre");
        table.addCell("Descripción");
        table.addCell("URL Banner");

        // Rellenar la tabla con la información de los cursos
        for (ClsModeloCurso curso : cursos) {
            table.addCell(curso.getIdCurso());
            table.addCell(String.valueOf(curso.getFKidIdioma()));
            table.addCell(curso.getNombre());
            table.addCell(curso.getDescripcion());
            table.addCell(curso.getUrlBanner());
        }

        // Agregar la tabla al documento
        document.add(table);

        // Pie de página
          document.add(new Paragraph("        "));
        document.add(new Paragraph("Fecha de creación: " + new Date()));
        String nombreUsuario = "Idiomify con Amor :)";
        document.add(new Paragraph("De: " + nombreUsuario));

    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    } finally {
        if (document != null && document.isOpen()) {
            document.close();
        }
        if (writer != null) {
            writer.close();
        }
    }

    break;


                    
                    case "exportarCsv":
                        String idIdiomatxt = request.getParameter("idIdioma");
                    int idIdiomaint = Integer.parseInt(idIdiomatxt);
                    // Configurar la respuesta para la descarga de un archivo CSV
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition", "attachment; filename=ListadoCursos.csv");

                    // Crear un objeto CSVWriter para escribir el archivo CSV
                    try (CSVWriter writers = new CSVWriter(response.getWriter())) {
                        // Escribir la fila de encabezado del archivo CSV
                        writers.writeNext(new String[] { "ID", "FKidIdioma", "Nombre","Descripcion", "URL Banner" });

                        // Obtener los datos de los idiomas
                        ClsModeloDaoCurso daoCurso = new ClsModeloDaoCurso();
                        List<ClsModeloCurso> cursocsv = daoCurso.listarCursosPorIdIdioma(idIdiomaint);

                        // Escribir los datos de los idiomas al archivo CSV
                        for (ClsModeloCurso cursoExcel : cursocsv) {
                            String[] rowData = {
                                    String.valueOf(cursoExcel.getIdCurso()),
                                    String.valueOf(cursoExcel.getFKidIdioma()),
                                    cursoExcel.getNombre(),
                                    cursoExcel.getDescripcion(),
                                    cursoExcel.getUrlBanner()
                            };
                            writers.writeNext(rowData);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(IdiomaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                case "agregarCursos":
                    ClsModeloCurso nuevoCurso = new ClsModeloCurso();
                    nuevoCurso.setIdCurso(request.getParameter("idCurso"));
                    nuevoCurso.setFKidIdioma(Integer.parseInt(request.getParameter("FKidIdioma")));
                    nuevoCurso.setUrlBanner(request.getParameter("urlBanner"));
                    nuevoCurso.setNombre(request.getParameter("nombre"));
                    nuevoCurso.setDescripcion(request.getParameter("descripcion"));

                    ClsModeloDaoCurso daoCurso = new ClsModeloDaoCurso();
                    boolean exito = daoCurso.agregarCurso(nuevoCurso);

                    if (exito) {
                                   
                        String idIdiomaStr = request.getParameter("FKidIdioma");
                        page = "admin/cursos/listarCursos.jsp?idIdioma="+idIdiomaStr;

                    } else {
                        page = "error.jsp";
                    }
                    break;
                case "actualizarCurso":
                    String idCurso = request.getParameter("idCurso");
                    int FKidIdioma = Integer.parseInt(request.getParameter("FKidIdioma"));
                    String urlBanner = request.getParameter("urlBanner");
                    String nombre = request.getParameter("nombre");
                    String descripcion = request.getParameter("descripcion");
                    
                   

                    ClsModeloCurso cursoActualizado = new ClsModeloCurso(idCurso, FKidIdioma, urlBanner, nombre, descripcion);

                    ClsModeloDaoCurso daoCursoActualizar = new ClsModeloDaoCurso();
                    boolean exitoActualizacion = daoCursoActualizar.actualizarCurso(cursoActualizado);     
              
                    if (exitoActualizacion) {
                                  
                        String idIdiomaStr = request.getParameter("FKidIdioma");
                        page = "admin/cursos/listarCursos.jsp?idIdioma="+idIdiomaStr;
                                                         System.out.println("tienes fff esto es la url valo:"+idIdiomaStr+page);
//                        AQUI QUIERO REDIRIGIR AQUI AL MISMO SERVLET PERO AL LISTARcURSOS PERO AL GET
                    } else {
                        page = "error.jsp";
                    }
                    break;
case "insertarCursosCSV":
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
                            ClsModeloCurso nuevoCursoCsv = new ClsModeloCurso();
                            nuevoCursoCsv.setIdCurso(linea[0]);
                            nuevoCursoCsv.setFKidIdioma(Integer.parseInt(linea[1]));
                            nuevoCursoCsv.setUrlBanner(linea[2]);
                            nuevoCursoCsv.setNombre(linea[3]);
                            nuevoCursoCsv.setDescripcion(linea[4]);

                            ClsModeloDaoCurso daoCursoCsv = new ClsModeloDaoCurso();
                            boolean cursoAgregado = daoCursoCsv.agregarCurso(nuevoCursoCsv);

                            if (cursoAgregado) {
                                System.out.println("Curso agregado exitosamente: " + nuevoCursoCsv.getIdCurso());
                            } else {
                                System.out.println("Error al agregar el curso: " + nuevoCursoCsv.getIdCurso());
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException ex) {
            ex.printStackTrace();  // Agrega esta línea para imprimir la traza de la excepción
        } catch (CsvValidationException | IOException ex) {
            ex.printStackTrace();  // Agrega esta línea para imprimir la traza de la excepción
            Logger.getLogger(CursoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Redirige a la página de listado de cursos después de la importación
            page = "admin/cursos/listarCursos.jsp?idIdioma=3";
        }
    } else {
        page = "admin/cursos/listarCursos.jsp";
        System.out.println("No se encontró el archivo CSV en la solicitud");
        // Manejar el caso en que no se encuentra el archivo CSV
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
