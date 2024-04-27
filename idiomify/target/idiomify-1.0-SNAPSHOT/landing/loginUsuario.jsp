<%-- 
    Document   : loginUsuario
    Created on : Nov 4, 2023, 12:04:43 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Inicio de Sesión</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background: linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%), linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%);;
            border-radius: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 350px;
            padding: 20px;
            text-align: center; /* Para centrar el contenido */
            color: white;
        }

        .container img {
            max-width: 80px;
            margin-bottom: 10px;
        }

        .container h2 {
            text-align: center;
        }

        .container form {
            display: flex;
            flex-direction: column;
        }

        .container label {
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: flex-start;
        }

        .container label i {
            margin-right: 10px;
        }

        .container input[type="email"],
        .container input[type="text"],
          .img_captcha,
        .container input[type="password"] {
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 20px;
            background: #caffff;
        }
        a{
            color:#cacaca;
        }

        .container button {
            background: #007BFF;
            color: #fff;
            border: none;
            border-radius: 20px;
            padding: 10px;
            margin-top: 10px;
            cursor: pointer;
        }

        @media (max-width: 500px) {
            .container {
                width: 90%; /* Ajustar el ancho en pantallas más pequeñas */
            }
        }
    </style>
</head>
<body>
    <div class="container">
   
        <h2>Idiomify</h2>
        
        <h2>Inicio de Sesión</h2>
        <form action="/idiomify/UsuarioServlet?accion=autenticarUsuario" method="POST">
            <label for="email">
                <i class="fa-solid fa-envelope"></i> Correo electrónico:
            </label>
            <input type="email" name="email" id="email" placeholder="Correo electrónico" required>

            <label for="password">
                <i class="fas fa-lock"></i> Contraseña:
            </label>
            <input type="password" name="password" id="password" placeholder="Contraseña" required>
                        <label for="captcha">
                            <i class="fa-solid fa-shield-halved" style="color: #ffffff;"></i> 
                <span>Introduce el CAPTCHA:</span><br>
                <img class="img_captcha" src="/idiomify/captcha-image.jsp" alt="CAPTCHA">
            </label>
            <input type="text" name="captcha" id="captcha" placeholder="Introduce el CAPTCHA" required>
  
            <button type="submit">Iniciar Sesión</button>
        </form>
        <p>No tienes cuenta?.</p>
        <a href="registerUsuario.jsp">Crear Cuenta</a>
    </div>
</body>
</html>

