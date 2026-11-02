package com.example.equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                    TextoTitulo("TEAM TITANS")

                    Separador()

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

                    Jugador(
                        "Sharx",
                        "Bárbara",
                        21,
                        14,
                        R.drawable.sharx
                    )

                    Separador()

                    Jugador(
                        "Irostius",
                        "Luchador",
                        43,
                        15,
                        R.drawable.irostius
                    )

                    Separador()

                    Jugador(
                        "Baddie",
                        "Clériga",
                        26,
                        10,
                        R.drawable.baddie
                    )


                    Separador()

                    Jugador(
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
    fun TextoTitulo(titulo: String){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = titulo,
                color =
                    //colorResource(R.color.purple_cosmic),
                    MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                    .background(
                        colorResource(R.color.gray_neutralviolet),
                        shape = RoundedCornerShape(25)
                    )
                    .weight(1f)
            )
        }
    }

    @Composable
    fun Separador(){
        HorizontalDivider(
            modifier = Modifier
                .padding(6.dp)
                .clip(shape = RoundedCornerShape(50)),
            thickness = 12.dp,
            colorResource(R.color.purple_cosmic)
        )
    }

    @Composable
    fun Jugador(
        nombre: String,
        rol: String,
        edad: Int,
        nivel: Int,
        @DrawableRes foto: Int
    ){
        Row(modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .background(
                colorResource(R.color.cream_tawnytan),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 6.dp)
            .height(108.dp)
        ){
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight()
                    .weight(4f),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(foto),
                    contentDescription = nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(56.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 12.dp)
                    .weight(5f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = nombre.uppercase(),
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 22.sp,
                    lineHeight = 22.sp,
                    maxLines = 1,
                )
                Text(
                    text = "$edad años",
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
            }

            Spacer(Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        start = 8.dp,
                        end = 24.dp
                    )
                    .weight(5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = rol,
                    textAlign = TextAlign.Right,
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 22.sp,
                    maxLines = 1,
                )

                Text(
                    text = "Lvl$nivel",
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
    fun JugadorVista(){
        Jugador(
            "Kath",
            "Programadora",
            26,
            120,
            R.drawable.ic_launcher_background
        )
    }
}