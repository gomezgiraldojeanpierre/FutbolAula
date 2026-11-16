// EVALUABLE E16 · "Panel de mi colección"
// ============================================================
// LEE PRIMERO docs/E16.md Y EL EJEMPLO X16Panel.kt (ya resuelto).
//
// TU MISIÓN: implementar la función counts de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E16Test y el botón Comprobar):
//   1. Contar cuántos jugadores hay de cada club.
//   2. Sin duplicar: si un jugador aparece dos veces, cuenta UNA (usa IDs).
//   3. Lista vacía: mapa vacío, sin error.
//   4. Devolver el mapa club -> recuento, ordenado alfabéticamente por club.
// PISTA: rows.distinctBy { it.id }.groupingBy { it.club }.eachCount().toSortedMap().
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E16Panel: aquí vive tu solución evaluable.
object E16Panel {
 // Cuenta jugadores por club para dibujar el panel personal.
 // ENTRADA: rows = los jugadores elegidos (favoritos, normalmente).
 // SALIDA: mapa con club y cuántos hay de ese club, ordenado alfabéticamente.
 fun counts(rows: List<Player>): Map<String, Int> {
  // Primero se quitan los duplicados por ID y después se cuenta por club.
  val byClub = rows.distinctBy { it.id }.groupingBy { it.club }.eachCount()
  // El mapa resultante conserva el orden alfabético por club (sin distinguir tildes ni mayúsculas).
  return byClub.entries.sortedBy { TextKey.of(it.key) }.associate { it.key to it.value }
 }
 // AMPLIACIÓN PROPIA (pendiente de acordar con el docente): resumen del club más representado.
 // Solo usa recuentos de la colección, no estadísticas deportivas. Si hay empate, gana el primero por orden alfabético.
 // Sin jugadores devuelve un mensaje neutro en lugar de fallar.
 fun summary(rows: List<Player>): String {
  val top = counts(rows).entries.maxByOrNull { it.value } ?: return "Aún no hay favoritos que resumir"
  return "Club más representado en tu colección: ${top.key} (${top.value})"
 }
 // demo() lo usa Academia: el recuento por club del catálogo inicial.
 fun demo(): String = counts(Seed.players).toString()
}
