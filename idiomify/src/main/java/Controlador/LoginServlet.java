/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.net.HttpURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author usuario
 */
@WebServlet("/captcha-image.jsp")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establece el tamaño de la imagen CAPTCHA
        int width = 200;
        int height = 50;

        // Genera una imagen CAPTCHA en blanco
        BufferedImage captchaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = captchaImage.getGraphics();

        // Rellena el fondo con un color claro
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        // Configura una fuente personalizada para los caracteres
        Font font = new Font("Monospaced", Font.BOLD, 30);
        g.setFont(font);

        // Genera una cadena aleatoria de caracteres alfanuméricos
        String captchaText = generateRandomCaptchaText(6); // Cambia el número según tu preferencia

        // Dibuja los caracteres en la imagen
        g.setColor(Color.BLACK);
        g.drawString(captchaText, 40, 35); // Ajusta las coordenadas según el diseño deseado

        // Almacena el texto CAPTCHA en la sesión para la validación posterior
        request.getSession().setAttribute("captchaText", captchaText);

        // Envía la imagen CAPTCHA al navegador
        response.setContentType("image/png");
        ImageIO.write(captchaImage, "png", response.getOutputStream());
    }

    private String generateRandomCaptchaText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            captchaText.append(characters.charAt(index));
        }

        return captchaText.toString();
    }
    
    
    public String hashPassword(String password) {
        try {
            String salt = "$2a$12$9oM9Z5I7nIeVQlj4K1cVo.";
            return BCrypt.hashpw(password, salt);
        } catch (Exception e) {
// Manejo de la excepción, por ejemplo, registro o notificación de errores
                        return null; // Otra acción apropiada en caso de error
        }
    }
}
