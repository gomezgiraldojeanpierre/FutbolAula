// EVALUABLE E09 · "Once inicial 4-3-3"
// ============================================================
// LEE PRIMERO docs/E09.md Y EL EJEMPLO X09Alineacion.kt (ya resuelto).
//
// TU MISIÓN: implementar la función validate de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E09Test y el botón Comprobar):
//   1. Decidir si una lista de jugadores ES un once 4-3-3 válido.
//   2. Reglas: exactamente 11 jugadores, todos distintos, con 1 portero,
//      4 defensas, 3 centrocampistas y 3 delanteros.
//   3. Cualquier otra cosa (menos jugadores, repetidos, otra distribución)
//      devuelve false. NUNCA lanza error: solo dice sí o no.
// PISTA: agrupa por posición con groupBy { it.position } y cuenta cada grupo.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E09Alineacion: aquí vive tu solución evaluable.
object E09Alineacion {
 // Comprueba si la lista forma un once 4-3-3 completo y válido.
 // ENTRADA: rows = los jugadores elegidos (puede venir incompleta).
 // SALIDA: true solo si es un once válido; false en cualquier otro caso.
 fun validate(rows: List<Player>): Boolean {
  // Esta función solo responde sí o no: nunca lanza error.
  if (rows.size != 11) return false
  // Once jugadores con IDs distintos; un repetido invalida la alineación.
  if (rows.map { it.id }.toSet().size != 11) return false
  // Reparto exacto 1-4-3-3; como suman 11, cualquier otra posición queda descartada sola.
  val count = rows.groupingBy { it.position }.eachCount()
  return count["Portero"] == 1 && count["Defensa"] == 4 &&
   count["Centrocampista"] == 3 && count["Delantero"] == 3
 }
 // demo() lo usa Academia: valida los primeros 11 del catálogo (no es un 4-3-3).
 fun demo(): String = validate(Seed.players.take(11)).toString()
}
