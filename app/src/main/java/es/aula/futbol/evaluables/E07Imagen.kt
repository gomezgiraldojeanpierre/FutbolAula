// EVALUABLE E07 · "Imagen persistente por jugador"
// ============================================================
// LEE PRIMERO docs/E07.md Y EL EJEMPLO X07Imagen.kt (ya resuelto).
//
// TU MISIÓN: implementar la función filename de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E07Test y el botón Comprobar):
//   1. Construir el nombre de archivo de la imagen de un jugador.
//   2. Formato EXACTO: "jugador_<ID>.<extension>" con ID positivo.
//   3. Solo admitir png o jpg; cualquier otra cosa (incluidas rutas con ../
//      o barras) debe RECHAZARSE: el nombre nunca puede manipular la ruta.
// PISTA: require(extension in setOf("png","jpg")) y return "jugador_$id.$extension".
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E07Imagen: aquí vive tu solución evaluable.
object E07Imagen {
 // Construye el nombre de archivo seguro para la imagen de un jugador.
 // ENTRADA: id = número positivo del jugador; extension = "png" o "jpg".
 // SALIDA: el nombre de archivo; error si el ID o la extensión no son válidos.
 fun filename(id: Int, extension: String): String {
  // Un ID positivo evita nombres como "jugador_0" o "jugador_-3".
  require(id > 0) { "El ID debe ser positivo" }
  // Solo se admiten png y jpg; así nunca entra una ruta con "../" o barras en el nombre.
  require(extension == "png" || extension == "jpg") { "Extensión no admitida: use png o jpg" }
  // El nombre lo construimos nosotros, el usuario no aporta ninguna ruta.
  return "jugador_$id.$extension"
 }
 // demo() lo usa Academia: el nombre del jugador 7 en formato png.
 fun demo(): String = filename(7,"png")
}
