package com.example.pruebaexamendi

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.pruebaexamendi.ui.theme.PruebaExamenDITheme
import org.junit.Rule
import org.junit.Test

class HabitosScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun alMarcarUnHabito_elProgresoSeActualiza() {
        // Configuramos la UI del test con el tema y la pantalla principal
        composeTestRule.setContent {
            PruebaExamenDITheme {
                HabitosScreen()
            }
        }

        // 1. Verificamos el estado inicial de la tarjeta de progreso
        // Como hay 4 hábitos por defecto (agua, compose, cama, pasear),
        // esperamos que el texto muestre "0 de 4 completados"
        composeTestRule
            .onNodeWithText("0 de 4 completados")
            .assertIsDisplayed()

        // 2. Simulamos la interacción del usuario marcando el primer hábito
        // Buscamos un nodo alternativo (isToggleable captura checkboxes)
        composeTestRule
            .onAllNodes(isToggleable())[0]
            .performClick()

        // 3. Verificamos que el ProgresoCard se ha actualizado correctamente 
        // para mostrar que 1 hábito ha sido completado
        composeTestRule
            .onNodeWithText("1 de 4 completados")
            .assertIsDisplayed()
    }
}
