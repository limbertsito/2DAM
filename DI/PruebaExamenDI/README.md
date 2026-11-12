# Hábitos de Limbert 📝

## 📱 Descripción de la App
**Hábitos de Limbert** es una aplicación Android nativa desarrollada con **Jetpack Compose** y **Material Design 3** para la gestión y seguimiento de hábitos diarios. 

### Características principales:
- **Gestión de Hábitos:** Permite agregar nuevos hábitos clasificándolos en categorías y marcarlos como completados.
- **Filtro por Categoría:** Filtro horizontal interactivo (`Todas`, `Salud`, `Estudio`, `Hogar`, `Ocio`) con resaltado de la categoría activa.
- **Barra de Progreso Animada:** Tarjeta de progreso superior que calcula dinámicamente el porcentaje de hábitos completados con animaciones suaves (`animateFloatAsState`).
- **Animaciones e Interacción:**
  - Animación de desvanecido/despliegue de descripciones (`AnimatedVisibility`).
  - Animación en el cambio del color de fondo de las tarjetas al completar un hábito (`animateColorAsState`).
  - Texto tachado visual para los hábitos finalizados.
- **Persistencia de Estado:** Conserva el filtro activo y el texto introducido ante rotaciones de pantalla (`rememberSaveable`).
- **Accesibilidad e Internacionalización:**
  - Cumplimiento de áreas táctiles mínimas de `48.dp` en botones, checkboxes y chips.
  - Accesibilidad con `contentDescription` dinámico para lectores de pantalla.
  - Todos los textos externalizados en `strings.xml`.

---

## 📁 Estructura de Ficheros

```text
PruebaExamenDI/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/pruebaexamendi/
│   │   │   │   ├── Categoria.kt          # Enum con las categorías (Salud, Estudio, Hogar, Ocio) y sus referencias a recursos.
│   │   │   │   ├── Habito.kt             # Data class que representa el modelo de datos de un Hábito.
│   │   │   │   ├── HabitoItem.kt         # Composable reutilizable (stateless) para renderizar cada hábito de la lista con animaciones.
│   │   │   │   ├── MainActivity.kt       # Pantalla principal (HabitosScreen), estado general, Scaffold, LazyRow y ProgresoCard.
│   │   │   │   └── ui/theme/             # Tema personalizado Material 3 (Color.kt, Theme.kt, Type.kt).
│   │   │   └── res/
│   │   │       └── values/
│   │   │           └── strings.xml       # Archivo de recursos con todos los textos externalizados de la app.
│   └── build.gradle.kts                  # Configuración de dependencias de la app.
└── README.md                             # Documentación del proyecto.
```

---

## 🤖 Uso de Agente

Durante la fase de desarrollo e implementación con ayuda de Agentes de IA, se utilizaron diversos prompts para agilizar la maquetación y la lógica en Jetpack Compose. A continuación se presentan 3 ejemplos razonables de prompts utilizados y los ajustes manuales requeridos:

### Prompt 1: Implementación de lista con Scaffold y estado hoistado
> **Prompt:** *"Crea un Scaffold con TopAppBar y una LazyColumn de hábitos. Asegúrate de que el estado de la lista viva en la pantalla principal y pasa los eventos a un componente stateless."*
>
> **Ajuste / Corrección manual:**
> El agente generó la estructura base, pero se tuvo que corregir a mano el uso de la anotación `@OptIn(ExperimentalMaterial3Api::class)` en la función contenedora y aplicar explícitamente el parámetro `innerPadding` proporcionado por el `Scaffold` usando `Modifier.padding(innerPadding)` en la columna contenedora para evitar superposición con el `TopAppBar`.

### Prompt 2: Animaciones de estado y cambio de color
> **Prompt:** *"Añade una animación a la tarjeta del hábito para que despliegue la descripción al hacer clic y cambie el color de fondo suavemente si está completado."*
>
> **Ajuste / Corrección manual:**
> Se ajustó manualmente la condición de `AnimatedVisibility` para validar que `habito.descripcion.isNotBlank()` además de la variable booleana `expandido`, evitando así que tarjetas sin descripción se expandieran mostrando un espacio vacío. También se definió explícitamente el color de destino usando la paleta de colores del tema (`MaterialTheme.colorScheme.secondaryContainer`).

### Prompt 3: Implementación de Previews y Tests UI
> **Prompt:** * Escribe un test con createComposeRule(). Añade dependencias necesarias y usa testTags o textos para localizar los nodos. Haz que pruebe que al marcar un hábito, la tarjeta de progreso pasa a mostrar '1 de 4 completados'."*
>
> **Ajuste / Corrección manual:**
> Este último prompt se utilizó para generar todo el apartado de los tests instrumentados y la previsualización de la UI. A diferencia de los anteriores, en este caso **no tuve que arreglar ni corregir nada manualmente**. El código generado funcionó a la primera en verde, así que me dediqué únicamente a leer el código generado (como las funciones `onNodeWithText` o `performClick`) para intentar entender bien cómo funciona la regla de Compose (`createComposeRule()`) y aprender a hacer este tipo de testing en Android.
