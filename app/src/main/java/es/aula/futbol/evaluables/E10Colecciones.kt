// EVALUABLE E10 · "Colecciones múltiples"
// ============================================================
// LEE PRIMERO docs/E10.md Y EL EJEMPLO X10Colecciones.kt (ya resuelto).
//
// TU MISIÓN: implementar la función add de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E10Test y el botón Comprobar):
//   1. Añadir un nombre de colección NUEVO a la lista existente.
//   2. Limpiar el nombre: quitar espacios de los extremos (trim).
//   3. RECHAZAR: nombres en blanco, o repetidos aunque cambien acentos o
//      mayúsculas ("Técnica" y "tecnica" son el mismo nombre: usa TextKey.of).
//   4. Devolver la lista completa de nombres, incluido el nuevo al final.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E10Colecciones: aquí vive tu solución evaluable.
object E10Colecciones {
 // Añade un nombre de colección nuevo, validado y limpio.
 // ENTRADA: names = nombres que ya existen; name = el nombre que se quiere añadir.
 // SALIDA: la lista de nombres con el nuevo incluido; error si no es válido.
 fun add(names: List<String>, name: String): List<String> {
  // Se recorta el nombre y se exige una longitud de 1 a 40 caracteres.
  val clean = name.trim()
  require(clean.length in 1..40) { "El nombre debe tener entre 1 y 40 caracteres" }
  // Clave de comparación: sin tildes, sin mayúsculas y sin espacios.
  fun key(value: String) = TextKey.of(value).replace(Regex("\\s+"), "")
  require(names.none { key(it) == key(clean) }) { "Ya existe una colección con ese nombre" }
  // Lista previa más el nombre limpio al final.
  return names + clean
 }
 // demo() lo usa Academia: añade "Mi once" a una lista que ya tiene "Promesas".
 fun demo(): String = add(listOf("Promesas"),"Mi once").joinToString()
}
