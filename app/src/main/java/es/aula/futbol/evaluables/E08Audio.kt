// EVALUABLE E08 · "Playlist y comentarios de voz"
// ============================================================
// LEE PRIMERO docs/E08.md Y EL EJEMPLO X08Audio.kt (ya resuelto).
//
// TU MISIÓN: implementar la función next de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E08Test y el botón Comprobar):
//   1. Calcular el índice anterior o siguiente en una lista de audios.
//   2. Dirección solo -1 (anterior) o 1 (siguiente); otra cosa: error.
//   3. El índice actual debe existir en la lista; si no: error.
//   4. El movimiento es CIRCULAR: desde el primero hacia atrás se va al
//      último, y desde el último hacia adelante vuelve al primero.
//   5. Lista vacía: devolver null.
// PISTA: el módulo con cuidado con negativos, igual que en E05.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E08Audio: aquí vive tu solución evaluable.
object E08Audio {
 // Calcula el índice del audio anterior o siguiente en la playlist.
 // ENTRADA: current = índice actual; size = cuántos audios hay;
 // direction = -1 (anterior) o 1 (siguiente).
 // SALIDA: el nuevo índice (con vuelta circular), o null si no hay audios.
 fun next(current: Int, size: Int, direction: Int): Int? {
  // Solo hay dos movimientos posibles: anterior (-1) o siguiente (1).
  require(direction == -1 || direction == 1) { "La dirección debe ser -1 o 1" }
  require(size >= 0) { "El tamaño no puede ser negativo" }
  // Sin audios no hay hacia dónde moverse.
  if (size == 0) return null
  // El índice actual debe existir en la lista.
  require(current in 0 until size) { "El índice actual está fuera de la lista" }
  // Recorrido circular: desde el primero hacia atrás se llega al último.
  return Math.floorMod(current + direction, size)
 }
 // demo() lo usa Academia: el anterior al primero de una lista de 3 (circular).
 fun demo(): String = next(0,3,-1).toString()
}
