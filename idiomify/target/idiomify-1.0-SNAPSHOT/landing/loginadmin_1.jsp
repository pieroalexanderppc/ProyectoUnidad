<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Inicio de Sesi?n</title>
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
            color: white;
        }

        .container {
               background: linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%), linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%);
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            padding: 20px;
            text-align: center; /* Para centrar el contenido */
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
        .container input[type="password"] {
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
            background: #caffff;
        }

        .container button {
            background: #007BFF;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px;
            margin-top: 10px;
            cursor: pointer;
        }

        @media (max-width: 500px) {
            .container {
                width: 90%; /* Ajustar el ancho en pantallas m?s peque?as */
            }
        }
    </style>
</head>
<body>
    <div class="container">
   
        <h2>Idiomify</h2>
        
        <h2>Inicio de Sesi?n</h2>
        <form action="/idiomify/AdministradorServlet?accion=autenticarAdministrador" method="POST">
            <label for="username">
                <i class="fa-solid fa-envelope"></i> Correo electr?nico:
            </label>
            <input type="email" name="email" id="email" placeholder="Correo electr?nico" required>

            <label for="password">
                <i class="fas fa-lock"></i> Contrase?a:
            </label>
            <input type="password" name="password" id="password" placeholder="Contrase?a" required>
                        <label for="captcha">
                <span>Introduce el CAPTCHA:</span><br>
            <img src="/idiomify/captcha-image.jsp" alt="CAPTCHA">
            </label>
            <input type="text" name="captcha" id="captcha" placeholder="Introduce el CAPTCHA" required>
 

            <button type="submit">Iniciar Sesi?n</button>
        </form>
        <script>
        // Funci?n para mostrar el mensaje emergente al cargar la p?gina
        function mostrarMensaje() {
            alert("?Correo o contrase?a incorrecto!");
        }

        // Llama a la funci?n al cargar la p?gina
        window.onload = mostrarMensaje;
    </script>
        <p>Iniciar Sesion solo para administradores de Idiomify </p>
    </div>
</body>
</html>
