# PSP · UT1 · Práctica ProcLab

**Alumno/a:** [Nombre y apellidos] · **Curso:** 2º DAM · 2026/2027

## Puesta en marcha (hazlo una sola vez)

1. **Renombra** esta carpeta sustituyendo `apellido1-apellido2` por los tuyos.
2. Inicializa Git en la raíz: `git init`, `git add .` y `git commit -m "B0: plantilla inicial"`.
3. Abre en IntelliJ **la carpeta `kotlin/`** (*File → Open*), no la raíz del repositorio. Si pregunta, elige *Trust Project* y deja que cargue Gradle (la primera vez descarga dependencias: tarda unos minutos).
4. Comprueba en *File → Settings → Build, Execution, Deployment → Build Tools → Gradle*:
   - **Build and run using:** IntelliJ IDEA
   - **Run tests using:** IntelliJ IDEA
   - **Gradle JVM:** un JDK 21
5. Abre `src/main/kotlin/psp/ut1/b2/PruebaEntorno.kt` y pulsa el ▶ junto a `fun main`. Debe terminar con `Código del hijo: 0`.
6. En la terminal (Alt+F12), dentro de `kotlin/`, ejecuta `./gradlew test` (Windows: `gradlew.bat test`). Debe pasar la prueba `EntornoTest`.

Si los pasos 5 y 6 funcionan, el entorno está listo.

## Estructura

| Carpeta | Contenido |
|---------|-----------|
| `c/` | Bloque 1 (programas C). `make` compila todos los `.c`; `make clean` borra los ejecutables |
| `kotlin/src/main/kotlin/psp/ut1/util/` | `Procesos.kt`: `esWindows`, `procesoKotlin`, `comandoShell` |
| `kotlin/src/main/kotlin/psp/ut1/hijos/` | `Dormilon.kt` y otros hijos reutilizables |
| `kotlin/src/main/kotlin/psp/ut1/b2/` … `b4/` | Bloques 2, 3 y 4 |
| `kotlin/src/main/kotlin/psp/ut1/proyecto/` | Bloque 5 (proyecto integrador) |
| `kotlin/src/test/kotlin/` | Pruebas unitarias |
| `kotlin/corpus/` | Ficheros de texto de prueba del Bloque 5 |
| `capturas/` | Imágenes usadas en `INFORME.md` |

**Regla de oro:** la carpeta y la línea `package` deben coincidir. Un `main` en `psp/ut1/b3/Cesar.kt` se lanza como hijo con `procesoKotlin("psp.ut1.b3.CesarKt", ...)`.

## Compilar y ejecutar desde la terminal

```bash
cd kotlin
./gradlew jar
java -cp build/libs/psp-ut1-1.0-SNAPSHOT.jar psp.ut1.b2.PruebaEntornoKt
./gradlew test
```

## Cómo ejecutar cada bloque

<!-- Completa esta sección a medida que avances: comando exacto y argumentos de cada programa. -->

## Ayudas utilizadas

<!-- Obligatorio: documentación, compañeros, herramientas de IA… y para qué las usaste. -->
