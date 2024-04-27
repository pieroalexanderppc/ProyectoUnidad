import { LeerLentoIdioma, LeerNormalIdioma } from "./speak.js";
import { generarNumerosUnicos } from "./numberandom.js";
// import { preguntasYRespuestas} from "../lecciones.php"
// Obtén el elemento div que contiene el array JSON
const preguntasYRespuestasDiv = document.getElementById("preguntasYRespuestas");


// Obtén la cadena JSON del atributo data-preguntas
const preguntasYRespuestasJSON = preguntasYRespuestasDiv.getAttribute("data-preguntas");
 let preguntasYRespuestas;
// Intenta analizar la cadena JSON
try {
   preguntasYRespuestas = JSON.parse(preguntasYRespuestasJSON);
  console.log("Datos parseados:", preguntasYRespuestas);
} catch (error) {
  console.error("Error al analizar la cadena JSON:", error);
}
  for (var i = 0; i < preguntasYRespuestas.length; i++) {
    for (var j = 0; j < preguntasYRespuestas[i].length; j++) {
      var valor = preguntasYRespuestas[i][j];
      console.log("Fila " + i + ", Columna " + j + ": " + valor);
    }
  }

let countPreguntas = preguntasYRespuestas.length;
let seHizoClic = false; // Inicialmente, se asume que no se hizo clic
let inputVacio = true;
let arrayErrores = [];
let puntos = 20/countPreguntas;
console.log(puntos);
console.log(countPreguntas);
let puntuacion = 0;
console.log(puntuacion);

const microfonoAnimation = document.querySelector(".microfono_animation");
const transcription = document.getElementById("transcription");
const errorPrompt = document.getElementById("error_prompt");
const inputVerificar = document.getElementById("bol");

ejecucionCurso(
  preguntasYRespuestas,
  countPreguntas,
  preguntasYRespuestas[0][2]
  );

function ejecucionCurso(verbos, generarNumero, parrafoObjetivo) {
  console.log("desde appppppppppppppppppp");
  const numeros = generarNumerosUnicos(generarNumero);
  console.log(numeros);
  const containerObjetivo = document.getElementById("parrafo_objetivo");
  containerObjetivo.textContent = parrafoObjetivo;
  
  const titulo = document.querySelector(".title");
  const imageLeccion = document.querySelector(".image_leccion");
  const helpTraduction = document.querySelector(".help_traduction");
  const input = document.getElementById("bol");
  const verificar = document.querySelector(".btn_verificar");
  const contentBotonContinuar = document.querySelector(
    ".contentBoton_continuar"
  );
  const messegeError = document.querySelector(".error");
  const messegeSolutions = document.querySelector(".solutions");
  const corazonNumber = document.querySelector(".corazon_number");
  const continuePrompt = document.querySelector(".btn_continuar_prompt");
  const finishAnuncio = document.querySelector(".finish_container");

  let firstEnter = true; //ESTA VARIABLE ES PARA VERIFICAR EL ENTER
  let indice = 0; //indice para controlar el indice de los array
  let barraProgreso = 0; // para aumnetar la barra de progreso
  let cantidadVidas = 3; // para determinar la cantidad de vida que le resta
  corazonNumber.textContent = cantidadVidas;
  updateProgress(barraProgreso); // Actualiza la barra de progreso al 50%

  //sonido en parlante
  let talk;
  const volumenNormal = document.querySelector(".volumen");
  volumenNormal.addEventListener("click", decir);
  function decir() {
    LeerNormalIdioma(talk);
  }
  const volumenLento = document.querySelector(".volumenLento");
  volumenLento.addEventListener("click", decirLento);
  function decirLento() {
    LeerLentoIdioma(talk);
  }

  verificar.addEventListener("click", verificarPrompt);

  //VERIFICAR SI SE HIZO CLICK EN EL BOTON  MICROFONO

  // LO QUE OCURRE AL HACER CLICK EN VERIFICAR PROMPT
  function verificarPrompt() {
    if (seHizoClic === true && inputVacio === false) {
      seHizoClic = false;
      inputVacio = true;

      input.disabled = true;
      helpTraduction.textContent = "";
      contentBotonContinuar.classList.toggle("inactive");

      let valor = input.value; //obtiene el valor que recogio del promt text del usuario

      //ESTE IF COMPARA CON EL VALOR CONVERTIDO EN MINUSCULA CON MINUSCULA DEL ARRAY VERBO INPORTADO
      if (
        valor.toLowerCase().replace(/’/g, "'") ===
        verbos[numeros[indice]][4].toLowerCase().replace(/’/g, "'")
      ) {
        puntuacion=puntuacion+puntos;
        playMusic("app/learn/LeccionIdiomify/sound/correcto.mp3");
        barraProgreso += 100 / verbos.length;
        updateProgress(barraProgreso); // Actualiza la barra de progreso al 100%
        messegeError.textContent = "¡ Eres Brillante !";
        messegeSolutions.textContent = "";

        indice++;
      } else {
        
        playMusic("app/learn/LeccionIdiomify/sound/incorrecto.mp3");
        messegeError.textContent = "¡ Esta mal tu respuesta !";
        messegeSolutions.textContent =
        "Solucion correcta: " + verbos[numeros[indice]][4];
        cantidadVidas -= 1;
        corazonNumber.textContent = cantidadVidas;
        agregarArrayErrores([
          verbos[numeros[indice]][3], // Verbo
          verbos[numeros[indice]][4]  // Traducción
        ]);
        indice++;
      }
    } else {
      //ESTO GUARDA EN UN ARRAY LOS ERRORES
      errorPrompt.textContent = "Activa el micrófono y escribe el texto"; // Limpiar el contenido anterior
    }
  }
  // LO QUE OCURRE AL HACER CLICK EN CONTINUAR PROMPT
  continuePrompt.addEventListener("click", continuarPrompt);

  function continuarPrompt() {
     
    transcription.textContent = ""; // Limpiar el contenido anterior
    errorPrompt.textContent = "";

    if (cantidadVidas === 0) {
      unfinishidLesson();
    } else if (indice === verbos.length) {
      finishidLesson();
    } else {
      input.disabled = false;
      helpTraduction.textContent = "";
      contentBotonContinuar.classList.toggle("inactive");
      input.value = "";
      titulo.textContent = verbos[numeros[indice]][3];
      imageLeccion.innerHTML=`<img src="${verbos[numeros[indice]][5]}" alt="imagenes para aprender gramatica" width="80px">`;
      talk = verbos[numeros[indice]][6];
      LeerNormalIdioma(talk);
    }
  }

  titulo.textContent = verbos[numeros[indice]][3];
  imageLeccion.innerHTML=`<img src="${verbos[numeros[indice]][5]}" alt="imagenes para aprender gramatica" width="80px">`;
  talk = verbos[numeros[indice]][6];
  LeerNormalIdioma(talk);

  // BARRA DE PROGRESO
  function updateProgress(progress) {
    const progressBar = document.querySelector(".progress");
    progressBar.style.width = progress + "%";
  }

  //  ACTIVAR DESACTIVAR  CONTENIDO DE SALIR AL HACER CLICK AL ICON SALIR
  const iconSalir = document.querySelector(".icon_salir");
  const salirContent = document.querySelector(".salir_content");
  const continuar = document.querySelector(".btn_continuar");
  const abandonar = document.querySelector(".btn_abandonar");

  iconSalir.addEventListener("click", mostrarContenidoSalir);
  continuar.addEventListener("click", mostrarContenidoSalir);
  function mostrarContenidoSalir() {
    salirContent.classList.toggle("inactive");
  }
  // FUNCION PARA VOLVER HACIA ATRAS EN LA PAGINA DE APP
  abandonar.addEventListener("click", volverPage);
  function volverPage() {
    window.history.back();
  }
  function playMusic(musicURL) {
    var audio = new Audio(musicURL);
    audio.play();
  }
  function finishidLesson() {
    // Obtener la URL actual
    let url = new URL(window.location.href);

    // Obtener el valor del parámetro "idLeccion" de la URL
    let idLeccion = url.searchParams.get("idLeccion");
    let arrayParaEnviar = JSON.stringify(arrayErrores);

    // Verificar si se obtuvo el valor correctamente
    if (idLeccion) {


      // Construir la URL con los parámetros en la URL
      let nuevaURL =
        "/idiomify/app/learn/LeccionIdiomify/ContenidoEspañolIngles/completo.jsp?idLeccion=" +
        idLeccion +
        "&puntuacion=" +
        encodeURIComponent(puntuacion) +
        "&vidas=" +
        cantidadVidas +
        "&array=" +
        encodeURIComponent(arrayParaEnviar);

      // Redirigir automáticamente a la nueva URL
      window.location.href = nuevaURL;

      console.log("Valor de idLeccion:", idLeccion);
    } else {
      console.log("El parámetro idLeccion no se encontró en la URL.");
    }
  }

  function unfinishidLesson() {
    // Obtener la URL actual
    let url = new URL(window.location.href);

    // Obtener el valor del parámetro "idLeccion" de la URL
    let idLeccion = url.searchParams.get("idLeccion");
    let arrayParaEnviar = JSON.stringify(arrayErrores);

    // Verificar si se obtuvo el valor correctamente
    if (idLeccion) {
      // Construir la URL con los parámetros en la URL
      let nuevaURL =
        "/idiomify/app/learn/LeccionIdiomify/ContenidoEspañolIngles/incompleto.jsp?idLeccion=" +
        idLeccion +
        "&puntuacion=" +
        encodeURIComponent(puntuacion) +
        "&vidas=" +
        cantidadVidas +
        "&array=" +
        encodeURIComponent(arrayParaEnviar);

      // Redirigir automáticamente a la nueva URL
      window.location.href = nuevaURL;

      console.log("Valor de idLeccion:", idLeccion);
    } else {
      console.log("El parámetro idLeccion no se encontró en la URL.");
    }
  }

  // ESTE EVENTO AYUDA AL USUARIO VER LA TRADUCCION
  titulo.addEventListener("click", traduction);
  function traduction() {
    helpTraduction.textContent = verbos[numeros[indice]][4];
  }

  // Variable para verificar si es la primera vez que se presiona Enter
  // Agregar un manipulador de eventos para el evento 'keydown'
  window.addEventListener("keydown", (event) => {
    // Verificar si la tecla presionada es Enter
    if (event.key === "Enter" || event.keyCode === 13) {
      // Verificar si es la primera vez que se presiona Enter
      if (firstEnter) {
        // Ejecutar la primera función
        verificarPrompt();
        // Cambiar el valor de la variable para indicar que ya se presionó Enter
        firstEnter = false;
      } else {
        // Ejecutar la segunda función
        continuarPrompt();
        firstEnter = true;
      }
    }
  });
}

//AQUI SE VERIFICA SI SE HIZO CLIK O NO EN EL MICROFONO

// Agregar un controlador de eventos 'click' al elemento
microfonoAnimation.addEventListener("click", function () {
  // Cambiar el valor de la variable cuando se hace clic
  seHizoClic = true;

  // Resto de las acciones que se ejecutan cuando se hace clic en microfonoAnimation
  console.log("Se hizo clic en microfonoAnimation");
});
inputVerificar.addEventListener("blur", function () {
  // Obtén el valor del input
  const valorInput = inputVerificar.value.trim(); // .trim() elimina espacios en blanco al principio y al final

  // Verifica si el input está vacío
  if (valorInput === "") {
    // El input está vacío
    console.log("El input está vacío.");
  } else {
    inputVacio = false;
    // El input tiene contenido
    console.log("El input tiene contenido:", valorInput);
  }
});
function agregarArrayErrores(elemento) {
  arrayErrores.push(elemento);
}
