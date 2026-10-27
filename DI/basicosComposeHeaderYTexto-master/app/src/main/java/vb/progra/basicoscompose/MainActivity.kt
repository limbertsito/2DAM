package vb.progra.basicoscompose


import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text
import vb.progra.basicoscompose.ui.theme.BasicosComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicosComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    interfaz(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun interfaz( modifier: Modifier = Modifier) {
        val image= painterResource(R.drawable.bg_compose_background)
        val title= stringResource(R.string.title)
        val mid= stringResource(R.string.mid)
        val body=stringResource(R.string.body)
    Box{
        Column() {
            Image(
                painter=image,
                contentDescription= null
            )
            Column(

            ) {
                Text(
                    text=title,
                    fontSize = 24.sp,
                    modifier= Modifier.padding(16.dp)
                )
                Text(
                    text =mid ,
                    modifier= Modifier.padding(start = 16.dp,end=16.dp),
                    textAlign = TextAlign.Justify

                )
                Text(
                    text=body,
                    modifier= Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Justify
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicosComposeTheme {
        interfaz()
    }
}