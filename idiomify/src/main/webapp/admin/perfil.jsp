


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-dT+5FQE8f5Jaz0a1bTMbs6xtnLCZ3bG8X8O53Rbda18Bl3Thm/G2pg2D8SfVXWEC0dS8JO5iQWYl5nMM2O6vpw==" crossorigin="anonymous" />
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%), linear-gradient(280deg, #174B8A 3.68%, #0C1219 71.34%);
            color: white;
            min-height: 100vh;
        }
        .profile-card {
            display: flex;
            flex-direction: column;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
            background: #0C1219;
        }
        .profile-image {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .profile-image img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
        .profile-details {
            padding: 20px;
        }
        .profile-header {
            text-align: center;
            font-size: 24px;
        }
        .profile-field {
            display: flex;
            align-items: center;
            margin-bottom: 16px;
        }
        .profile-icon {
            margin-right: 16px;
            font-size: 20px;
        }
        .profile-value {
            font-size: 16px;
        }
        .edit-button {
            background: #174B8A;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 16px;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .edit-button:hover {
            background-color: #0C1219;
        }
    </style>
</head>
<body>

<c:if test="${adminAutenticado != null}">
    <div class="profile-card">
        <div class="profile-image">
            <img src="${adminAutenticado.fotoPerfil}" alt="Foto de Perfil">
        </div>
        <div class="profile-details">
            <div class="profile-header">
                <h1><i class="fas fa-user-circle"></i> Perfil de Usuario</h1>
            </div>
            <div class="profile-field">
                <div class="profile-icon"><i class="fas fa-user"></i></div>
                <div class="profile-value">Nombre: ${adminAutenticado.nombre}</div>
            </div>
            <!-- ... (otros campos) ... -->
            <div class="profile-field">
                <div class="profile-icon"><i class="fas fa-envelope"></i></div>
                <div class="profile-value">Email: ${adminAutenticado.email}</div>
            </div>
            <!-- Puedes agregar m?s campos seg?n tus necesidades -->
            
        </table>
        </div>
    </div>
</c:if>

</body>
</html>
