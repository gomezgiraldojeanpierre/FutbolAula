// EVALUABLE E15 · "Intercambio con confirmación"
// ============================================================
// LEE PRIMERO docs/E15.md Y EL EJEMPLO X15Bluetooth.kt (ya resuelto).
//
// TU MISIÓN: implementar la función decode de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E15Test y el botón Comprobar):
//   1. Leer el mensaje JSON recibido por Bluetooth: {"version":1,"ids":[...]}.
//   2. Validar TODO antes de aceptar: versión correcta, tamaño máximo 8192 bytes,
//      sin IDs repetidos y todos conocidos en este dispositivo.
//   3. Si algo falla: error controlado (el receptor lo rechaza sin romperse).
//   4. Devolver la lista de IDs válidos.
// PISTA: JSONObject(text) para leer; JSONArray para los ids; TextKey no hace falta aquí.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

import org.json.JSONObject

// Declara el objeto E15Bluetooth: aquí vive tu solución evaluable.
object E15Bluetooth {
 // Decodifica y valida un mensaje de colección recibido por Bluetooth.
 // ENTRADA: text = el mensaje JSON tal como llega; known = IDs conocidos aquí.
 // SALIDA: la lista de IDs válidos; error si el mensaje no cumple el protocolo.
 fun decode(text: String, known: Set<Int>): List<Int> {
  // El tamaño se mide en bytes UTF-8, no en caracteres: una "ñ" ocupa dos.
  require(text.toByteArray(Charsets.UTF_8).size <= 8192) { "Mensaje demasiado grande" }
  // Si el texto no es JSON, JSONObject lanza una excepción que el receptor captura y rechaza.
  val root = JSONObject(text)
  require(root.getInt("version") == 1) { "Versión no admitida" }
  val array = root.getJSONArray("ids")
  require(array.length() > 0) { "La lista de IDs está vacía" }
  // Cada elemento debe ser un entero real; "1" o 1.5 no valen.
  val ids = (0 until array.length()).map { i ->
   val value = array.get(i)
   require(value is Int) { "El ID en la posición $i no es un entero" }
   value
  }
  require(ids.toSet().size == ids.size) { "IDs repetidos" }
  require(known.containsAll(ids)) { "Hay IDs desconocidos en este dispositivo" }
  // Se devuelven en el mismo orden en que llegaron.
  return ids
 }
 // demo() lo usa Academia: decodifica un mensaje válido con los IDs 1 y 2.
 fun demo(): String = decode("{\"version\":1,\"ids\":[1,2]}",setOf(1,2)).toString()
}
