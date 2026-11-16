// EVALUABLE E14 · "Diseñar y compartir cromo"
// ============================================================
// LEE PRIMERO docs/E14.md Y EL EJEMPLO X14Cromo.kt (ya resuelto).
//
// TU MISIÓN: implementar la función title de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E14Test y el botón Comprobar):
//   1. Limpiar el título: quitar espacios de los extremos (trim).
//   2. RECHAZAR: título vacío, de más de 28 caracteres o color fuera de 0..2.
//   3. Devolver el título limpio, listo para dibujarlo en el cromo.
// PISTA: require(raw.trim().length in 1..28) y require(color in 0..2).
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E14Cromo: aquí vive tu solución evaluable.
object E14Cromo {
 // Limpia y valida el título del cromo antes de dibujarlo.
 // ENTRADA: raw = texto escrito por el alumno; color = 0 (azul), 1 (violeta) o 2 (verde).
 // SALIDA: el título limpio; error si está vacío, es demasiado largo o el color no existe.
 fun title(raw: String, color: Int): String {
  // El color es un índice: 0 azul, 1 violeta, 2 verde.
  require(color in 0..2) { "El color debe ser 0, 1 o 2" }
  // Se recorta el título sin alterar su contenido y se exige de 1 a 28 caracteres.
  val clean = raw.trim()
  require(clean.length in 1..28) { "El título debe tener entre 1 y 28 caracteres" }
  return clean
 }
 // demo() lo usa Academia: valida un título de ejemplo con el color violeta.
 fun demo(): String = title("Mi jugador favorito",1)
}
