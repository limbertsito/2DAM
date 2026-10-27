package vb.progra.limonada




import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vb.progra.limonada.ui.theme.LimonadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            HomeScreen()
        }
    }
}

fun textoImagenLimonada(): Int {
    TODO("Not yet implemented")
}

@Composable
fun HomeScreen() {
    var estadoActual by remember { mutableStateOf(1) }
    var contador by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { TopBarLemon() }
    ) {innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding ),
            color = MaterialTheme.colorScheme.background
        ) {
            when (estadoActual) {
                1 -> textoImagenLimonada(
                    onImageClick = {
                        estadoActual=2
                        contador=(2..4).random()
                    },
                    textLabelResourceId = R.string.lemon_tree,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree_content_description
                )
                2 -> textoImagenLimonada(
                    onImageClick ={
                        contador--
                        if(contador==0){
                            estadoActual=3
                        }
                    },
                    textLabelResourceId = R.string.lemon,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_content_description


                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLemon() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer

        )
    )
}
@Composable
fun textoImagenLimonada(onImageClick: () -> Unit = {}
              ,textLabelResourceId:Int
              ,drawableResourceId: Int
              ,contentDescriptionResourceId: Int
              , modifier: Modifier = Modifier
){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = null

            )
        }

}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LimonadaTheme {
        HomeScreen()
    }
}


