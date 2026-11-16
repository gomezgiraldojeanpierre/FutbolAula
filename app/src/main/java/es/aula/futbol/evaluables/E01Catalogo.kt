// EVALUABLE E01 · "Más equipos reales"
// ============================================================
// LEE PRIMERO docs/E01.md Y EL EJEMPLO X01Catalogo.kt (ya resuelto).
//
// TU MISIÓN: implementar la función addTeam de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E01Test y el botón Comprobar):
//   1. Recibir el catálogo actual y un LOTE nuevo (un solo club, 5+ jugadores).
//   2. Devolver el catálogo actualizado (lista actual + lote).
//   3. RECHAZAR con error: lote vacío, dos clubes mezclados, ID repetido
//      o el mismo jugador duplicado con otro ID.
// REGLAS: no cambies la firma ni borres el TODO hasta terminar.
// El botón "Comprobar E01" de Academia te dirá exactamente qué caso falla.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E01Catalogo: aquí vive tu solución evaluable.
object E01Catalogo {
 // Añade un lote nuevo al catálogo actual, validando todas las reglas.
 // ENTRADA: current = lista actual; incoming = lote nuevo de UN club.
 // SALIDA: la lista actualizada. Si algo no cumple las reglas: error (require).
 fun addTeam(current: List<Player>, incoming: List<Player>): List<Player> {
  // Posiciones admitidas: son las mismas que usa el resto de la app.
  val positions = setOf("Portero", "Defensa", "Centrocampista", "Delantero")
  // Un lote vacío no aporta nada, por tanto se rechaza.
  require(incoming.isNotEmpty()) { "El lote está vacío" }
  // Todo el lote debe pertenecer a un único club (se compara sin tildes ni mayúsculas).
  val clubs = incoming.map { TextKey.of(it.club) }.toSet()
  require(clubs.size == 1 && clubs.first().isNotEmpty()) { "Un lote debe tener un único club" }
  // Cada ficha necesita nombre, posición conocida y fuente HTTPS: así no se inventan datos.
  incoming.forEach { p ->
   require(p.name.isNotBlank()) { "Ficha sin nombre (id ${p.id})" }
   require(p.position in positions) { "Posición no admitida: ${p.position}" }
   require(p.source.startsWith("https://") && p.source.length > 8) { "La fuente de ${p.name} debe ser HTTPS" }
  }
  // Los IDs no pueden repetirse dentro del lote ni chocar con el catálogo actual.
  val ids = incoming.map { it.id }
  require(ids.toSet().size == ids.size) { "IDs repetidos dentro del lote" }
  require(ids.none { id -> current.any { it.id == id } }) { "Alguno de los IDs ya existe en el catálogo" }
  // Un jugador es el mismo si coinciden nombre y club, aunque tenga otro ID.
  fun key(p: Player) = TextKey.of(p.name) + "|" + TextKey.of(p.club)
  val seen = current.map { key(it) }.toMutableSet()
  incoming.forEach { require(seen.add(key(it))) { "Jugador repetido en su club: ${it.name}" } }
  // Todo es válido: se devuelve el catálogo actual más el lote, sin tocar el original.
  return current + incoming
 }
 // demo() lo usa Academia para probar tu función con el lote del Athletic.
 // Devuelve cuántos jugadores hay tras añadirlo (16 + 5 = 21).
 fun demo(): String = addTeam(Seed.players, Seed.extra).size.toString()
}
