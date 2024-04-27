<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
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
            color: #cacaca;
        }

        .container {
             background: linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%), linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%);
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            
            padding: 20px;
            text-align: center;
            margin: 0 auto;
        }

        .container img {
            max-width: 80px;
            margin-bottom: 10px;
        }

        .container h2 {
            text-align: center;
        }

        .container form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px; /* Espacio entre las columnas aumentado */
        }

        .container label {
            font-weight: bold;
            display: flex;
            align-items: center;
        }

        .container label i {
            margin-right: 10px;
        }

        .container input[type="text"],
        .container input[type="date"],
        .container input[type="email"],
        .container input[type="password"],
        .container select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            width: 95%;
       
        }

        .container .form-group {
            margin-bottom: 20px;
        }

        .container button {
            background: #007BFF;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px;
            cursor: pointer;
            width: 200%;
        }
    </style>
</head>
<body>
    <div class="container">

        <h2>Registro de Usuario</h2>
        <form action="/idiomify/UsuarioServlet?accion=registrarUsuario" method="POST">
            <div class="form-group">
                <label for="nombre">
                    <i class="fa-solid fa-user"></i> Nombre:
                </label>
                <input type="text" name="nombre" id="nombre" placeholder="Nombre" required>
            </div>

            <div class="form-group">
                <label for="apellido">
                    <i class="fa-solid fa-user"></i> Apellido:
                </label>
                <input type="text" name="apellido" id="apellido" placeholder=" Apellido" required>
            </div>

            <div class="form-group">
                <label for="fechaNacimiento">
                    <i class="fa-solid fa-calendar"></i> Fecha de Nacimiento:
                </label>
                <input type="date" name="fechaNacimiento" id="fechaNacimiento" placeholder="Fecha de Nacimiento" required>
            </div>

            <div class="form-group">
                <label for="genero">
                    <i class="fa-solid fa-venus-mars"></i> Género:
                </label>
                <select name="genero" id="genero" required>
                    <option value="Masculino">Masculino</option>
                    <option value="Femenino">Femenino</option>
                    <option value="Otro">Otro</option>
                </select>
            </div>

            <div class="form-group">
                <label for="pais">
                    <i class="fa-solid fa-globe-americas"></i> País:
                </label>
                <select name="pais" id="pais" required>
                    <option value="">Selecciona un país</option>
                </select>
            </div>

            <div class="form-group">
                <label for="ciudad">
                    <i class="fa-solid fa-city"></i> Ciudad:
                </label>
                <select name="ciudad" id="ciudad" required>
                    <option value="">Selecciona una ciudad</option>
                </select>
            </div>

            <div class="form-group">
                <label for="email">
                    <i class="fa-solid fa-envelope"></i> Correo electrónico:
                </label>
                <input type="email" name="email" id="email" placeholder="Correo electrónico" required>
            </div>

            <div class="form-group">
                <label for="password">
                    <i class="fas fa-lock"></i> Contraseña:
                </label>
                <input type="password" name="password" id="password" placeholder="Contraseña" required>
            </div>
                        <label for="captcha">
                <span>Introduce el CAPTCHA:</span><br>
            <img src="/idiomify/captcha-image.jsp" alt="CAPTCHA">
            </label>
            <input type="text" name="captcha" id="captcha" placeholder="Introduce el CAPTCHA" required>
    

            <button type="submit">Registrarse</button>
        </form>
        <p>¿Ya tienes una cuenta? <a href="loginUsuario.jsp">Iniciar Sesión</a></p>
    </div>

 

    <script>
        // Definir un objeto con la lista de países y ciudades
      const paisesYDepartamentos = {
                "Argentina": [
                    "Buenos Aires",
                    "Córdoba",
                    "Santa Fe",
                    "Mendoza",
                    "Tucumán",
                    "Entre Ríos",
                    "Salta",
                    "Misiones",
                    "Chaco",
                    "Corrientes",
                    "Santiago del Estero",
                    "San Juan",
                    "Jujuy",
                ],
                "Bolivia": [
                    "La Paz",
                    "Cochabamba",
                    "Santa Cruz",
                    "Potosí",
                    "Oruro",
                    "Tarija",
                    "Chuquisaca",
                    "Beni",
                    "Pando",
                ],
                "Chile": [
                    "Santiago",
                    "Valparaíso",
                    "Región del Maule",
                    "Biobío",
                    "La Araucanía",
                    "Los Lagos",
                    "Atacama",
                    "Coquimbo",
                ],
                "Colombia": [
                    "Bogotá",
                    "Antioquia",
                    "Valle del Cauca",
                    "Cundinamarca",
                    "Santander",
                    "Atlántico",
                    "Bolívar",
                    "Nariño",
                ],
                "Costa Rica": [
                    "San José",
                    "Alajuela",
                    "Cartago",
                    "Heredia",
                    "Guanacaste",
                    "Puntarenas",
                    "Limón",
                ],
                "Cuba": [
                    "La Habana",
                    "Santiago de Cuba",
                    "Camagüey",
                    "Holguín",
                    "Villa Clara",
                    "Las Tunas",
                    "Cienfuegos",
                ],
                "República Dominicana": [
                    "Santo Domingo",
                    "Santiago",
                    "La Romana",
                    "San Pedro de Macorís",
                    "San Cristóbal",
                    "Puerto Plata",
                ],
                "Ecuador": [
                    "Pichincha",
                    "Guayas",
                    "Azuay",
                    "Manabí",
                    "Santo Domingo de los Tsáchilas",
                    "Tungurahua",
                ],
                "El Salvador": [
                    "San Salvador",
                    "Santa Ana",
                    "San Miguel",
                    "La Libertad",
                    "Ahuachapán",
                    "La Paz",
                    "Sonsonate",
                ],
                "España": [
                    "Madrid",
                    "Cataluña",
                    "Andalucía",
                    "Comunidad Valenciana",
                    "Castilla y León",
                    "País Vasco",
                    "Galicia",
                    "Canarias",
                ],
                "Guatemala": [
                    "Guatemala",
                    "Quetzaltenango",
                    "Huehuetenango",
                    "Petén",
                    "San Marcos",
                    "Escuintla",
                ],
                "Honduras": [
                    "Francisco Morazán",
                    "Cortés",
                    "Atlántida",
                    "Olancho",
                    "Comayagua",
                    "La Paz",
                ],
                "México": [
                    "Ciudad de México",
                    "Jalisco",
                    "Nuevo León",
                    "Puebla",
                    "Guanajuato",
                    "Veracruz",
                    "Sinaloa",
                    "Michoacán",
                ],
                "Nicaragua": [
                    "Managua",
                    "León",
                    "Granada",
                    "Matagalpa",
                    "Chinandega",
                    "Jinotega",
                    "Atlántico Norte",
                ],
                "Panamá": [
                    "Ciudad de Panamá",
                    "Bocas del Toro",
                    "Chiriquí",
                    "Coclé",
                    "Darién",
                    "Herrera",
                    "Los Santos",
                ],
                "Paraguay": [
                    "Asunción",
                    "San Lorenzo",
                    "Luque",
                    "Capiatá",
                    "Lambare",
                    "Fernando de la Mora",
                    "Limpio",
                ],
                "Perú": [
                    "Lima",
                    "Cusco",
                    "Arequipa",
                    "Lambayeque",
                    "La Libertad",
                    "Puno",
                    "Piura",
                ],
                "Puerto Rico": [
                    "San Juan",
                    "Bayamón",
                    "Carolina",
                    "Ponce",
                    "Caguas",
                    "Guaynabo",
                    "Arecibo",
                ],
                "Uruguay": [
                    "Montevideo",
                    "Canelones",
                    "Maldonado",
                    "Salto",
                    "Paysandú",
                    "Rivera",
                    "Tacuarembó",
                ],
                "Venezuela": [
                    "Distrito Capital",
                    "Miranda",
                    "Zulia",
                    "Carabobo",
                    "Lara",
                    "Aragua",
                    "Anzoátegui",
                ],
            };

        // Obtener las referencias de los elementos select
        const paisSelect = document.getElementById("pais");
        const ciudadSelect = document.getElementById("ciudad");

        // Función para llenar la lista de países
        function llenarPaises() {
            for (const pais in paisesYDepartamentos) {
                const option = document.createElement("option");
                option.value = pais;
                option.textContent = pais;
                paisSelect.appendChild(option);
            }
        }

        // Función para llenar la lista de ciudades basada en el país seleccionado
        function llenarCiudades() {
            const selectedPais = paisSelect.value;
            ciudadSelect.innerHTML = '<option value="">Selecciona una ciudad</option>';

            if (selectedPais in paisesYDepartamentos) {
                const ciudades = paisesYDepartamentos[selectedPais];
                ciudades.forEach(ciudad => {
                    const option = document.createElement("option");
                    option.value = ciudad;
                    option.textContent = ciudad;
                    ciudadSelect.appendChild(option);
                });
            }
        }

        // Llenar la lista de países inicialmente
        llenarPaises();

        // Escuchar eventos de cambio en el select de países para llenar la lista de ciudades
        paisSelect.addEventListener("change", llenarCiudades);
    </script>
</body>
</html>
