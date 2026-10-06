package com.example.equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.equipoesports.ui.theme.EquipoESportsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EquipoESportsTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .background(colorResource(R.color.blue_bigboy))
                        .padding(horizontal = 6.dp)
                ){
                    textoTitulo("TEAM TITANS")

                    separador()

                    /*
                    Decidí intencionalmente cambiar los nombres de los jugadores
                    mostrados para que mi entrega sea más personalizada. Para los
                    nombres del resultado aproximado, sacarlos del comentario

                    jugador(
                        "AlexPro",
                        "Atacante",
                        21,
                        87,
                        R.drawable.sharx
                    )

                    separador()

                    jugador(
                        "ShadowX",
                        "Defensor",
                        24,
                        91,
                        R.drawable.irostius
                    )

                    separador()

                    jugador(
                        "MartaGG",
                        "Soporte",
                        20,
                        84,
                        R.drawable.baddie
                    )


                    separador()

                    jugador(
                        "Destroyer",
                        "Atacante",
                        23,
                        89,
                        R.drawable.belehry
                    )
                    */

                    jugador(
                        "Sharx",
                        "Bárbara",
                        21,
                        14,
                        R.drawable.sharx
                    )

                    separador()

                    jugador(
                        "Irostius",
                        "Luchador",
                        43,
                        15,
                        R.drawable.irostius
                    )

                    separador()

                    jugador(
                        "Baddie",
                        "Clériga",
                        26,
                        10,
                        R.drawable.baddie
                    )


                    separador()

                    jugador(
                        "Belehry",
                        "Maga",
                        32,
                        14,
                        R.drawable.belehry
                    )
                }
            }
        }
    }

    @Composable
    fun textoTitulo(titulo: String){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "$titulo",
                color = colorResource(R.color.purple_cosmic),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                    .background(
                        colorResource(R.color.gray_neutralviolet),
                        shape = RoundedCornerShape(25)
                    )
                    .weight(10.0f)
            )
        }
    }

    @Composable
    fun separador(){
        HorizontalDivider(
            modifier = Modifier
                .padding(6.dp)
                .clip(shape = RoundedCornerShape(50)),
            thickness = 12.dp,
            colorResource(R.color.purple_cosmic)
        )
    }

    @Composable
    fun jugador(
        nombre: String,
        rol: String,
        edad: Int,
        nivel: Int,
        @DrawableRes foto: Int
    ){
        Row(modifier = Modifier
            .statusBarsPadding()
            .background(
                colorResource(R.color.cream_tawnytan),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 6.dp)
            .height(108.dp)
            .fillMaxWidth()
        ){
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(foto),
                    contentDescription = "$nombre",
                    // contentScale = ContentScale.Crop,
                    //Nota: decidí deshabilitar el ContentScale porque fastidia la forma de la imagen
                    modifier = Modifier
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                Text(
                    text = nombre.uppercase(),
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 22.sp,
                    lineHeight = 22.sp
                )
                Text(
                    text = "$edad años",
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(rol,
                    textAlign = TextAlign.Right,
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 22.sp
                )
                Text("Lvl" + nivel,
                    textAlign = TextAlign.Right,
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun jugadorVista(){
        jugador(
            "Kath",
            "Programadora",
            26,
            120,
            R.drawable.ic_launcher_background
        )
    }
}