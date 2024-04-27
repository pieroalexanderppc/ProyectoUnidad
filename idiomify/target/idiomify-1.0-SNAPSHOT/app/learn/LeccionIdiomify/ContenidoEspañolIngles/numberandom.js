export function generarNumerosUnicos(cantidad) {
    const numerosGenerados = [];
  
    for (let i = 0; i < cantidad; i++) {
      let numeroAleatorio;
      do {
        numeroAleatorio = Math.floor(Math.random() * cantidad);
      } while (numerosGenerados.includes(numeroAleatorio));
      numerosGenerados.push(numeroAleatorio);
    }
  
    return numerosGenerados;
  }
