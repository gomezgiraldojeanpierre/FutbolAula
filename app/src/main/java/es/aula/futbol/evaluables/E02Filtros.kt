// EVALUABLE E02 · "Buscar, filtrar y ordenar"
// ============================================================
// LEE PRIMERO docs/E02.md Y EL EJEMPLO X02Filtros.kt (ya resuelto).
//
// TU MISIÓN: implementar la función filter de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E02Test y el botón Comprobar):
//   1. Filtrar a la vez por: texto buscado, club, posición y favoritos.
//   2. El texto busca SIN importar acentos ni mayúsculas (usa TextKey.of).
//   3. Ordenar por nombre: ascendente o descendente según el parámetro.
// PISTA: encadena filter { ... } y luego sortedBy { ... }; si toca descendente,
// da la vuelta al resultado con .reversed().
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E02Filtros: aquí vive tu solución evaluable.
object E02Filtros {
 // Filtra y ordena el catálogo combinando todos los criterios a la vez.
 // ENTRADA: rows = catálogo; query = texto; club/position = filtros exactos
 // (vacío significa "todos"); favorites/onlyFavorites = filtro de favoritos;
 // descending = true ordena de la Z a la A.
 // SALIDA: la lista que cumple TODO a la vez, ordenada por nombre.
 fun filter(rows: List<Player>, query: String, club: String, position: String, favorites: Set<Int>, onlyFavorites: Boolean, descending: Boolean): List<Player> {
  // Se normaliza el texto una sola vez: así "MBAPPÉ" y "mbappe" son la misma búsqueda.
  val text = TextKey.of(query)
  // Las cuatro condiciones se combinan con AND; un filtro vacío significa "todos".
  val found = rows.filter { p ->
   (text.isEmpty() || TextKey.of(p.name).contains(text)) &&
    (club.isEmpty() || p.club == club) &&
    (position.isEmpty() || p.position == position) &&
    (!onlyFavorites || p.id in favorites)
  }
  // Se ordena por nombre normalizado; en descendente basta con invertir el resultado.
  val ordered = found.sortedBy { TextKey.of(it.name) }
  return if (descending) ordered.reversed() else ordered
 }
 // demo() lo usa Academia: filtra los delanteros del Barça sin favoritos.
 fun demo(): String = filter(Seed.players, "", "FC Barcelona", "Delantero", emptySet(), false, false).joinToString { it.name }
}
