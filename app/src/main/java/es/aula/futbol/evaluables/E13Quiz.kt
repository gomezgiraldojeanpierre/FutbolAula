// EVALUABLE E13 · "Reto de cinco preguntas"
// ============================================================
// LEE PRIMERO docs/E13.md Y EL EJEMPLO X13Quiz.kt (ya resuelto).
//
// TU MISIÓN: implementar la función build de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E13Test y el botón Comprobar):
//   1. Crear `count` preguntas a partir de los jugadores disponibles.
//   2. Cada pregunta: texto, DOS opciones distintas y la respuesta correcta
//      entre ellas (puedes mezclar el orden de las opciones).
//   3. Sin repetir jugador: cada pregunta va de un jugador distinto.
//   4. RECHAZAR: pedir más preguntas que jugadores, o catálogos con IDs repetidos.
// PISTA: la respuesta es el club del jugador; el distractor, el club de otro.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E13Quiz: aquí vive tu solución evaluable.
object E13Quiz {
 // Construye las preguntas del cuestionario a partir del catálogo.
 // ENTRADA: rows = jugadores disponibles (ya mezclados por quien llama);
 // count = cuántas preguntas se quieren.
 // SALIDA: la lista de preguntas listas para jugar; error si el catálogo no da para tanto.
 fun build(rows: List<Player>, count: Int): List<Question> {
  require(count > 0) { "El número de preguntas debe ser positivo" }
  require(rows.map { it.id }.toSet().size == rows.size) { "Hay IDs repetidos en el catálogo" }
  require(rows.size >= count) { "No hay jugadores suficientes para $count preguntas" }
  // Con un solo club no existe una segunda opción distinta.
  val clubs = rows.map { it.club }.distinct()
  require(clubs.size >= 2) { "Se necesitan al menos dos clubes" }
  // Cada pregunta usa un jugador distinto: rows ya llega mezclado, por tanto basta con tomar los primeros.
  return rows.take(count).map { player ->
   // El distractor es el club de otro jugador; nunca coincide con la respuesta.
   val other = clubs.filter { it != player.club }.random()
   Question(player.id, "¿En qué club juega ${player.name}?", listOf(player.club, other).shuffled(), player.club)
  }
 }
 // demo() lo usa Academia: construye 5 preguntas con el catálogo inicial.
 fun demo(): String = build(Seed.players,5).joinToString("\n") { it.text }
}
