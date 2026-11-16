// EVALUABLE E05 · "Sorpresa respetando filtros"
// ============================================================
// LEE PRIMERO docs/E05.md Y EL EJEMPLO X05Sensor.kt (ya resuelto).
//
// TU MISIÓN: implementar la función choose de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E05Test y el botón Comprobar):
//   1. Elegir UN jugador de la lista visible usando el índice dado.
//   2. El índice da la VUELTA (módulo): 16 con 16 jugadores vuelve al 1,
//      y -1 devuelve al último. PISTA: el operador % de Kotlin da negativos,
//      usa (((index % n) + n) % n).
//   3. Lista vacía: devolver null (nadie a quien elegir).
//   4. La elección debe salir SOLO de la lista recibida (respeta los filtros).
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E05Sensor: aquí vive tu solución evaluable.
object E05Sensor {
 // Elige un jugador de la lista con un índice que da la vuelta.
 // ENTRADA: candidates = lista visible tras filtros; index = número cualquiera.
 // SALIDA: el jugador elegido, o null si la lista está vacía.
 fun choose(candidates: List<Player>, index: Int): Player? {
  // Sin candidatos no hay a quién elegir.
  if (candidates.isEmpty()) return null
  // floorMod nunca devuelve negativos: 16 sobre 16 jugadores da la vuelta y -1 lleva al último.
  return candidates[Math.floorMod(index, candidates.size)]
 }
 // demo() lo usa Academia: elige el primero de los jugadores del Barça.
 fun demo(): String = choose(Seed.players.filter { it.club == "FC Barcelona" },0)?.name ?: "Sin candidatos"
}
