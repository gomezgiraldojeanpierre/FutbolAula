package es.aula.futbol

import org.junit.Test
import org.junit.Assert.*

// Pruebas de la ampliación propia de E16: E16Panel.summary.
class E16AmpliacionTest {
 @Test fun sinFavoritosNoFalla() {
  assertEquals("Aún no hay favoritos que resumir", E16Panel.summary(emptyList()))
 }
 @Test fun clubConMasFichas() {
  // Real Madrid tiene 8 fichas en el catálogo inicial y el Barça 8; se prueba con un subconjunto sin empate.
  val rows = Seed.players.filter { it.club == "Real Madrid" }.take(3) + Seed.players.filter { it.club == "FC Barcelona" }.take(1)
  assertEquals("Club más representado en tu colección: Real Madrid (3)", E16Panel.summary(rows))
 }
 @Test fun empateGanaElPrimeroAlfabetico() {
  val rows = listOf(Seed.players[0], Seed.players[8])
  assertTrue(E16Panel.summary(rows).contains("FC Barcelona (1)"))
 }
}
