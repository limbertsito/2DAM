# psp-ut1 · Proyecto de prácticas de la UT1 (procesos)

Proyecto Gradle con todos los ejercicios del cuaderno de la UT1, del bloque 2 al 5. Lo que
**no es de procesos ya está hecho**: los procesos hijo, los formatos de salida y los `main`.
Tú solo completas lo que trabaja PSP: lanzar procesos con `ProcessBuilder`, esperarlos con
`waitFor`, leer y escribir en sus canales, usar `ProcessHandle` y terminarlos con `destroy`.

## Abrirlo

En IntelliJ IDEA: *File › Open* y eliges esta carpeta (la que tiene `build.gradle.kts`).
Espera a que Gradle termine de cargar. Necesitas el JDK 21.

## Qué hay

```text
src/main/kotlin/psp/ut1/
├── util/Procesos.kt     ← esWindows, procesoKotlin(...) y comandoShell(...): ya hecho
├── hijos/               ← procesos hijo de los ejercicios: ya hechos, no se tocan
├── b2/                  ← Ejecutor.kt, MiniPs.kt, Vigilante.kt
├── b3/                  ← ClienteCesar.kt, ConsolaKV.kt
├── b4/                  ← PrimosParalelos.kt, Dependencias.kt
└── proyecto/            ← B5: Orquestador.kt (el Trabajador ya está hecho)
src/test/kotlin/psp/ut1/comprobaciones/   ← B2Test … B5Test: para saber si has terminado
corpus/, corpus-errores/                  ← textos de prueba del B5
```

## Dónde tienes que escribir

Cada hueco empieza con un comentario `// TODO Bn.m · Paso k` que dice **qué** hay que hacer y
**qué método** usar. Debajo hay una línea provisional como esta:

```kotlin
// TODO B2.1 · Paso 1 · Lanza el proceso.
val proceso: Process = TODO("B2.1 · paso 1: lanzar el proceso")
```

Cambia `TODO("...")` por tu código y deja el nombre de la variable, porque el código de
debajo lo usa. Si ejecutas un programa con un hueco sin hacer, se para con el error
`NotImplementedError: B2.1 · paso 1…`, que te dice dónde te has quedado.

Para ver la lista de todos los huecos en IntelliJ: *View › Tool Windows › TODO*.

## Ejecutar un programa

Con el triángulo verde que hay junto a `fun main` en IntelliJ. Los programas que necesitan
argumentos (`MiniPs --yo`, `Vigilante 5 notepad`...) se configuran en *Run › Edit
Configurations › Program arguments*.

## Saber si has terminado: las comprobaciones

Cada bloque tiene unas pruebas automáticas que lanzan procesos de verdad. Con los huecos sin
hacer salen **en rojo** y, cuando los completas, **en verde**. Se ejecutan con el triángulo
verde junto a `class B2Test` o desde la terminal:

```text
gradlew.bat test --tests "*B2Test"      (Windows)
./gradlew test --tests "*B2Test"        (Linux)
```

Que salgan en verde quiere decir que funciona. Además, tienes que saber explicar por qué.
