// FUNCION DE LECTURA EN INGLES NORMAL
export function LeerNormalIdioma(urlAudio) {
  let audio = new Audio(urlAudio);
  audio.playbackRate = 1; // Establecer la velocidad de reproducción a la mitad (lento)
  audio.play();
}
// FUNCION DE LECTURA EN INGLES LENTO
export function LeerLentoIdioma(urlAudio) {
  let audio = new Audio(urlAudio);
  audio.playbackRate = 0.6; // Establecer la velocidad de reproducción a la mitad (lento)
  audio.play();
}
