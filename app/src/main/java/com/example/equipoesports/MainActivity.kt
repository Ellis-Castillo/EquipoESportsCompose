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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import java.util.UUID

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var listaJugadores = rememberSaveable { mutableStateListOf<Jugador>() }

            listaJugadores.add(Jugador(
                nombre = "Sharx",
                nickname = "Trinchas",
                rol = "Bárbara",
                edad = 38,
                nivel = 18
                ))
            listaJugadores.add(Jugador(
                nombre = "Likura",
                nickname = "Tiff",
                rol = "Paladín",
                edad = 32,
                nivel = 15
            ))
            listaJugadores.add(Jugador(
                nombre = "Marcus",
                nickname = "Vex",
                rol = "Brujo",
                edad = 42,
                nivel = 16
            ))
            listaJugadores.add(Jugador(
                nombre = "Timaxius",
                nickname = "Cáos",
                rol = "Hechicero",
                edad = 34,
                nivel = 15
            ))
            listaJugadores.add(Jugador(
                nombre = "Maigrika",
                nickname = "Jefecita",
                rol = "Bardo",
                edad = 40,
                nivel = 15
            ))

            EquipoESportsTheme {
                PantallaPrincipal(listaJugadores)
            }
        }
    }

    @Composable
    fun PantallaPrincipal(
        listaJugadores: List<Jugador>
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(colorResource(R.color.blue_bigboy))
                .padding(horizontal = 6.dp)
        ) {
            TextoTitulo("TEAM TITANS")

            BotonAgregarJugador()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = listaJugadores,
                    key = {jugador -> jugador.id}
                ){
                    jugador ->
                    FichaJugador()
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
                color = MaterialTheme.colorScheme.primary,
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
    fun FichaJugador(
        jugador: Jugador,
        @DrawableRes foto: Int
    ){
        Separador()

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
                    contentDescription = jugador.nombre,
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
                    text = jugador.nombre.uppercase(),
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 22.sp,
                    lineHeight = 22.sp,
                    maxLines = 1,
                )
                Text(
                    text = jugador.nickname,
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    maxLines = 1,
                )
                Text(
                    text = "${jugador.edad} años",
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

    @Composable
    fun BotonAgregarJugador(
        alAgregar: () -> Unit,
        alEliminar: () -> Unit,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = alAgregar
            ) {
                Text("Añadir Jugador")
            }
        }
    }

    @Composable
    fun BotonesFichaJugador(){} //TODO
}

//DEBERÍA ELIMINAR LOS ATRIBUTOS DE LA PRÁCTICA ANTERIO?????????
data class Jugador constructor(
    val id: UUID = UUID.randomUUID(), //Para ids automáticos
    val nombre: String,
    val nickname: String,
    val rol: String,
    var edad: Int, //Estos dos atributos son heredados de Jugador de la actividad anterior
    var nivel: Int
)