//  Activacion de la ANIMACION DE MICROFONO
const microfonoAnimation =document.querySelector(".microfono_animation");
console.log("este mensaje es desde el archvio reconocimienot de voz de js");

microfonoAnimation.addEventListener("click",animationMicro);
function animationMicro(){
  microfonoAnimation.style.animationPlayState= "running";
}



// Crear un nuevo objeto de reconocimiento de voz
const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
// Configurar el reconocimiento de voz
recognition.lang = 'es'; // Establecer el idioma
recognition.interimResults = false; // Permitir resultados provisionales
recognition.continuous = false; // Permitir reconocimiento continuo

// Obtener los elementos de la página web
const startBtn = document.getElementById('start-btn');
const transcription = document.getElementById('transcription');

// Agregar un evento de clic al botón de inicio
startBtn.addEventListener('click', () => {
    // Comenzar a escuchar el audio

    recognition.start();
    transcription.textContent = ''; // Limpiar el contenido anterior
});

// Detectar cuando se reconocen palabras
recognition.addEventListener('result', (event) => {
    const transcript = Array.from(event.results)
        .map(result => result[0])
        .map(result => result.transcript)
        .join('');
    transcription.textContent = transcript; // Mostrar la transcripción
});

// Detectar cuando el reconocimiento de voz ha terminado
recognition.addEventListener('end', () => {
    microfonoAnimation.style.animationPlayState= "paused";
});


