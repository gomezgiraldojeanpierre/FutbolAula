// EVALUABLE E11 · "Copia de seguridad validada"
// ============================================================
// LEE PRIMERO docs/E11.md Y EL EJEMPLO X11Copia.kt (ya resuelto).
//
// TU MISIÓN: implementar la función restore de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E11Test y el botón Comprobar):
//   1. Leer el TEXTO de una copia en JSON (formato de Wire.encode).
//   2. Quedarse solo con los IDs conocidos: si la copia habla de un jugador
//      que no existe en este móvil, se ignora.
//   3. Si el texto está corrupto o no es JSON: error controlado (nada se rompe).
//   4. Devolver un mapa ID -> Mark SOLO con favorito y nota (la copia v1
//      no incluye etiquetas ni valoración).
// PISTA: Wire.decode(text, known) ya valida el formato; aprovéchalo.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E11Copia: aquí vive tu solución evaluable.
object E11Copia {
 // Lee y valida una copia de seguridad para poder restaurarla.
 // ENTRADA: current = datos actuales; text = texto JSON de la copia;
 // known = IDs que existen en este dispositivo.
 // SALIDA: mapa con los datos recuperables; error si la copia es inválida.
 fun restore(current: Map<Int, Mark>, text: String, known: Set<Int>): Map<Int, Mark> {
  // Wire.decode valida versión, tipos y duplicados, y descarta los IDs desconocidos.
  // Si el texto no es JSON lanza una excepción antes de tocar ningún dato.
  val backup = Wire.decode(text, known)
  // Se parte de los datos actuales para conservar los jugadores ausentes de la copia.
  val result = LinkedHashMap(current)
  // La copia sobrescribe solo favorito y nota; etiquetas y valoración se mantienen.
  backup.forEach { (id, mark) ->
   result[id] = (current[id] ?: Mark()).copy(favorite = mark.favorite, note = mark.note)
  }
  return result
 }
 // demo() lo usa Academia: restaura una copia mínima con un favorito.
 fun demo(): String = restore(emptyMap(),Wire.encode(mapOf(1 to Mark(true,"Nota"))),setOf(1)).toString()
}
