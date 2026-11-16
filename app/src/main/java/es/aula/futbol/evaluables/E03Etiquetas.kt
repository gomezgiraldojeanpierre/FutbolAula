// EVALUABLE E03 · "Etiquetas y valoración personal"
// ============================================================
// LEE PRIMERO docs/E03.md Y EL EJEMPLO X03Etiquetas.kt (ya resuelto).
//
// TU MISIÓN: implementar la función mark de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E03Test y el botón Comprobar):
//   1. Limpiar la lista de etiquetas: quitar espacios, vacías y repetidas
//      (aunque cambien acentos o mayúsculas: "Técnica" = "tecnica").
//   2. Admitir COMO MÁXIMO 5 etiquetas de hasta 24 caracteres cada una.
//   3. La valoración solo puede ir de 0 a 5; fuera de rango: error.
//   4. Conservar la nota y el favorito que ya tenía la ficha (usa copy).
// PISTA: original.copy(tags = clean, rating = rating) crea la Mark nueva.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E03Etiquetas: aquí vive tu solución evaluable.
object E03Etiquetas {
 // Guarda etiquetas limpias y valoración personal sobre una marca existente.
 // ENTRADA: original = la marca actual; tags = etiquetas escritas por el alumno;
 // rating = valoración personal de 0 a 5.
 // SALIDA: una Mark nueva con las etiquetas y valoración ya validadas.
 fun mark(original: Mark, tags: List<String>, rating: Int): Mark {
  // Se recortan las etiquetas y se descartan las vacías.
  val trimmed = tags.map { it.trim() }.filter { it.isNotEmpty() }
  // Ninguna etiqueta puede superar los 24 caracteres.
  require(trimmed.all { it.length <= 24 }) { "Cada etiqueta admite como máximo 24 caracteres" }
  // Se eliminan las repetidas sin distinguir tildes ni mayúsculas; se conserva la primera escrita.
  val seen = mutableSetOf<String>()
  val clean = trimmed.filter { seen.add(TextKey.of(it)) }
  // El máximo de cinco se cuenta después de limpiar, porque "Pase" y " pase " son una sola.
  require(clean.size <= 5) { "Se admiten como máximo 5 etiquetas" }
  // La valoración es personal y va de 0 a 5.
  require(rating in 0..5) { "La valoración debe estar entre 0 y 5" }
  // copy conserva favorito y nota, que son las otras propiedades de la ficha.
  return original.copy(tags = clean, rating = rating)
 }
 // demo() lo usa Academia: guarda dos etiquetas repetidas y valoración 4.
 fun demo(): String = mark(Mark(),listOf("Técnica"," técnica "),4).toString()
}
