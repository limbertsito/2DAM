package com.example.practicaexamenjetpack_activity

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun HomeSaludo(modifier: Modifier){

    var saludoIntroducido by rememberSaveable() { mutableStateOf("") }
    //Esta variable nos permitira saber que pantallla estamos trabajndp ahora mismo
    val context = LocalContext.current

    Column(
        modifier ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        ),

    ) {
        Text(stringResource(R.string.enviar_mensaje))
        CampoTexto(
            label= R.string.mensaje,
            value = saludoIntroducido,
            onValueChange = {
                //Aqui validamos que no  tenga mas dfe 15 caracteres
                entrada -> if (entrada.length<15){
                saludoIntroducido = entrada

            }
            }
        )
        Button(
            onClick = {
                //context : donde estamos -> hacia donde qeuremos "ir"

                //SegundaActivity::class.java: Es el destino ("a dónde voy").
                // El ::class.java es obligatorio en Kotlin para convertir la clase de Kotlin a la referencia
                // .class que entiende el motor de Java/Android internamente
                // apply lo que nos permite es que metamos los putExtra sin tener quie llamar a intent to-do el rato
                val intent = Intent(context, MainActivity2::class.java).apply {
                    putExtra(EXTRA_MENSAJE,saludoIntroducido)

                }
                    //LANZAMOS EL ACTIVITY y que cambie de pantalla
                context.startActivity(intent)

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            //Con esto vlaidamos que no estevacio
            enabled = saludoIntroducido.isNotBlank()
        ) {
            Text(stringResource(R.string.enviar))
        }


    }
}
@Composable
fun CampoTexto(
    label:Int,
    value :String,
    onValueChange:(String) -> Unit,

){
    TextField(
        label= {stringResource(label)},
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )

    )
}
@Composable
fun SegundaPantalla(
    mensaje:String,
    //Recibe una aaccion
    onVolver:()->Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.mensaje_recibido,
                modifier.background(MaterialTheme.colorScheme.primary)),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(10.dp))
        Text(mensaje)
        Button(
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(
                //Aqui le pintamos el color al boton de volver
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            )
        )
        {
            Text(stringResource(R.string.volver))
        }
    }
}
//En los top app bar hay que poner esto para que se nos quite el error
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTop(
    title : String
){
    TopAppBar(
        title = {Text(title)},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
            , titleContentColor= MaterialTheme.colorScheme.onPrimary
        )
    )

}