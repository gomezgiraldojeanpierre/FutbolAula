// EVALUABLE E04 · "Comparar fichas sin inventar datos"
// ============================================================
// LEE PRIMERO docs/E04.md Y EL EJEMPLO X04Comparador.kt (ya resuelto).
//
// TU MISIÓN: implementar la función compare de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E04Test y el botón Comprobar):
//   1. Aceptar solo de 2 a 4 jugadores DISTINTOS (fuera de eso: error).
//   2. Devolver un texto que compare identidad (nombre, club) y posición.
//   3. NUNCA inventar puntuaciones ni un ganador: solo datos reales.
// PISTA: usa require(rows.size in 2..4) y require que los IDs no se repitan.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E04Comparador: aquí vive tu solución evaluable.
object E04Comparador {
 // Compara varias fichas y devuelve el texto con la comparación.
 // ENTRADA: rows = de 2 a 4 jugadores distintos.
 // SALIDA: texto legible con la comparación real; error si incumple las reglas.
 fun compare(rows: List<Player>): String {
  // Se exigen de dos a cuatro fichas; con menos no hay comparación y con más no cabe en pantalla.
  require(rows.size in 2..4) { "Elige de 2 a 4 jugadores" }
  require(rows.map { it.id }.toSet().size == rows.size) { "No se puede comparar un jugador consigo mismo" }
  // Solo se muestran campos reales de la ficha; no hay puntuaciones ni ganador.
  return rows.joinToString("\n") { "${it.name} | ${it.club} | ${it.position}" }
 }
 // demo() lo usa Academia: compara a los dos primeros del catálogo.
 fun demo(): String = compare(Seed.players.take(2))
}
