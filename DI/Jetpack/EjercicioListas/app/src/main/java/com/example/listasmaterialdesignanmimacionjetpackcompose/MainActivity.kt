package com.example.listasmaterialdesignanmimacionjetpackcompose

import android.R.attr.fontStyle
import android.R.attr.label
import android.R.id.bold
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listasmaterialdesignanmimacionjetpackcompose.ui.theme.ListasMaterialDesignAnmimacionJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListasMaterialDesignAnmimacionJetpackComposeTheme {
                Surface {
                    Mensajes()
                }
            }
        }
    }
}

@Composable
fun TarjetaMensaje(mensaje: Mensaje) {
    var expandido by remember { mutableStateOf(false) }
    val colorFondo by animateColorAsState(
        targetValue = if  (expandido) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface,

        label ="ColorFondo")

    Row(
        modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.oip_4148391352),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (
            modifier = Modifier
                .clickable{
                    expandido =!expandido
                }
                .weight(1f)
                .padding(10.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        ){
            Text(
                text = mensaje.autor,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(4.dp))

            Surface(
                color = colorFondo,
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 2.dp,
                //con esto hacemos que el movimiento sea mas suave
                modifier = Modifier.animateContentSize()
            ) {
                Text(
                    text = mensaje.texto,
                    maxLines = if (expandido) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 10.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
                )
            }
        }
    }
}

data class Mensaje(
    val autor: String,
    val texto: String,
)

val mensajes = listOf(
    Mensaje("Laura", "¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?"),
    Mensaje("Mario", "Ya terminé la práctica de Compose.¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?"),
    Mensaje("Ana", "Mañana llevo el proyecto.¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?"),
    Mensaje("Pedro", "Nos vemos mañana.¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?"),
    Mensaje("Sara", "He subido el ejercicio.¿Qué tal ha ido la clase?¿Qué tal ha ido la clase?")
)

@Composable
fun Mensajes() {

    Text(
        "MIS MENSAJES",
        fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
        textDecoration = MaterialTheme.typography.titleLarge.textDecoration,
        modifier = Modifier.padding(10.dp)
    )
    Spacer(modifier = Modifier.height(2.dp))
    LazyColumn (modifier = Modifier.padding(top=10.dp)){
        items(mensajes) { mensaje ->
            TarjetaMensaje(mensaje = mensaje)
        }
    }
}

@Preview ( showBackground = true)
@Composable
fun PruebaPreview() {
    ListasMaterialDesignAnmimacionJetpackComposeTheme {
        Surface {
            Mensajes()
        }
    }
}
@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
    )
@Composable
fun PreviewOscuro(){
    ListasMaterialDesignAnmimacionJetpackComposeTheme {
        Surface {
            Mensajes()
        }
    }
}