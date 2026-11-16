// EVALUABLE E12 · "Datos guardados sin conexión"
// ============================================================
// LEE PRIMERO docs/E12.md Y EL EJEMPLO X12Cache.kt (ya resuelto).
//
// TU MISIÓN: implementar la función resolve de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E12Test y el botón Comprobar):
//   1. Decidir qué catálogo mostrar y con qué mensaje.
//   2. Si la descarga funcionó (remote != null y sin error): mostrar el remoto,
//      stale=false, mensaje "Actualizado".
//   3. Si falló: mostrar los datos GUARDADOS, stale=true (están caducados),
//      con un mensaje claro. Nunca dejar la app sin datos si había guardados.
//   4. Sin red y sin nada guardado: lista vacía con stale=true.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E12Cache: aquí vive tu solución evaluable.
object E12Cache {
 // Decide qué datos mostrar según cómo fue la descarga.
 // ENTRADA: remote = catálogo descargado (null si falló); saved = datos guardados;
 // error = mensaje del fallo (null si todo fue bien).
 // SALIDA: CacheResult con las filas a mostrar, si están caducadas y el mensaje.
 fun resolve(remote: List<Player>?, saved: List<Player>, error: String?): CacheResult {
  // Respuesta válida: remote existe y no hubo error. Una lista vacía también es válida.
  if (remote != null && error == null) return CacheResult(remote, false, "Actualizado")
  // La descarga falló: se mantiene el último resultado guardado y se marca como caducado.
  val reason = error ?: "sin respuesta"
  if (saved.isNotEmpty()) return CacheResult(saved, true, "Sin conexión ($reason). Se muestran los datos guardados")
  // Primera apertura sin red: no hay nada que enseñar, pero la app sigue funcionando.
  return CacheResult(emptyList(), true, "Sin datos: no hay conexión ($reason) ni datos guardados")
 }
 // demo() lo usa Academia: qué pasa si no hay red y solo están los datos iniciales.
 fun demo(): String = resolve(null,Seed.players,"sin red").message
}
