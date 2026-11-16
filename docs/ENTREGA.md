# Entrega: Fútbol Aula (E01–E16)

## Resultado real de las pruebas
- `gradlew.bat testDebugUnitTest --offline`: 67 tests, 0 fallos (64 originales + 3 de la ampliación de E16).
- `gradlew.bat assembleDebug --offline`: compila sin errores.
- No se han podido ejecutar aquí: `connectedDebugAndroidTest` (necesita emulador o móvil), cámara, micrófono, acelerómetro, Bluetooth entre dos equipos y las capturas de pantalla. Esas pruebas siguen siendo manuales (ver `docs/PRUEBAS_MANUALES.md`).

## Qué se ha hecho en cada ejercicio
| Ejercicio | Solución (archivo en `evaluables/`) |
|---|---|
| E01 | `addTeam` rechaza lote vacío, varios clubes, posición no admitida, fuente no HTTPS, IDs repetidos y jugador repetido en su club (nombre + club normalizados). |
| E02 | `filter` combina texto (con `TextKey`), club, posición y favoritos con AND; ordena por nombre normalizado y revierte en descendente. |
| E03 | `mark` recorta, quita vacías y repetidas sin tildes, limita a 5 etiquetas de 24 caracteres y valida 0–5; `copy` conserva nota y favorito. |
| E04 | `compare` exige de 2 a 4 IDs distintos y devuelve `nombre \| club \| posición` por línea, sin ganador. |
| E05 | `choose` devuelve `null` si no hay candidatos y usa `Math.floorMod` para que el índice dé la vuelta, también en negativo. |
| E06 | `parameters` rechaza NaN y valores fuera de 0–3; devuelve `(valor, 128·(1−valor))`. |
| E07 | `filename` exige ID positivo y extensión exacta `png` o `jpg`; construye `jugador_ID.ext` sin rutas del usuario. |
| E08 | `next` admite solo −1 o 1, devuelve `null` con lista vacía, valida el índice y recorre en círculo. |
| E09 | `validate` exige 11 IDs distintos y reparto 1-4-3-3; nunca lanza error. |
| E10 | `add` recorta, exige 1–40 caracteres y rechaza nombres equivalentes sin tildes, mayúsculas ni espacios. |
| E11 | `restore` valida con `Wire.decode` antes de mezclar; sobrescribe favorito y nota y conserva etiquetas, valoración y jugadores ausentes. |
| E12 | `resolve` distingue respuesta vacía válida de error; ante un fallo usa lo guardado con `stale=true`. |
| E13 | `build` valida cantidad, IDs, jugadores y clubes; crea preguntas de jugadores distintos con dos clubes distintos. |
| E14 | `title` valida color 0–2 y título de 1 a 28 caracteres, y devuelve el título recortado. |
| E15 | `decode` valida 8192 bytes UTF-8, versión 1, IDs enteros, no vacíos, sin duplicados y conocidos; conserva el orden. |
| E16 | `counts` quita duplicados por ID y devuelve el recuento por club ordenado. |

## Ampliación propia de E16 (pendiente de acordar con el docente)
`E16Panel.summary` indica el club más representado entre los favoritos y se muestra en el panel. Usa solo recuentos de la colección. Si hay empate gana el primero por orden alfabético. Sus pruebas están en `E16AmpliacionTest` (sin favoritos, un club claro y empate).

## Nota sobre los datos de E01
Los dos clubes añadidos son los lotes de referencia que ya trae la app (`Seed.extra`: Athletic Club y `Seed.atleti`: Atlético de Madrid), con cinco fichas y fuente HTTPS cada uno. Se cargan desde Añadir equipos y dependen de `addTeam`. No se han inventado posiciones ni estadísticas; las fuentes están en `FUENTES.md`.

## Cinco líneas (para el informe)
1. Hemos sustituido los 16 `TODO` por implementaciones que respetan las firmas y el contrato de cada enunciado.
2. Un error frecuente era el módulo con índices negativos (E05 y E08); lo resolvimos con `Math.floorMod`.
3. Otro caso delicado era E11: `Wire.decode` descarta los IDs desconocidos, por tanto partimos de los datos actuales para no perder los ausentes.
4. Lo comprobamos con `testDebugUnitTest` (67 tests en verde) y con `assembleDebug`.
5. Falta la prueba en móvil real (sensores, audio, Bluetooth) y las capturas, que conviene hacer antes de entregar.
