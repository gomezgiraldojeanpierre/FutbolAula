// EVALUABLE E06 · "Filtro de contraste reversible"
// ============================================================
// LEE PRIMERO docs/E06.md Y EL EJEMPLO X06Contraste.kt (ya resuelto).
//
// TU MISIÓN: implementar la función parameters de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E06Test y el botón Comprobar):
//   1. Recibir un valor de contraste de 0 a 3 (fuera de rango o NaN: error).
//   2. Devolver el PAR (escala, desplazamiento) que usa la matriz de color.
//   3. Las reglas fijas: escala = valor; desplazamiento = 128*(1-valor).
//      Así contraste 1 no cambia nada (offset 0) y contraste 0 empuja a 128.
// PISTA: con valor=1f el offset debe ser 0f; con valor=0f debe ser 128f.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E06Contraste: aquí vive tu solución evaluable.
object E06Contraste {
 // Calcula los parámetros de la matriz de contraste para un valor dado.
 // ENTRADA: value = contraste pedido, entre 0 y 3 (no puede ser NaN).
 // SALIDA: el par (escala, desplazamiento) para la matriz de color.
 fun parameters(value: Float): Pair<Float, Float> {
  // NaN, infinitos y valores fuera de 0..3 se rechazan antes de calcular nada.
  require(value.isFinite() && value in 0f..3f) { "El contraste debe estar entre 0 y 3" }
  // Escala = valor; desplazamiento = 128 * (1 - valor). Con valor 1 el nivel no cambia.
  return Pair(value, 128f * (1f - value))
 }
 // demo() lo usa Academia: pide los parámetros del contraste 1.4.
 fun demo(): String = parameters(1.4f).toString()
}
