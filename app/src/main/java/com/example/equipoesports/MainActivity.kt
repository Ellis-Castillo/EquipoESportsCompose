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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
                rol = "Bárbara"
                ))
            listaJugadores.add(Jugador(
                nombre = "Likura",
                nickname = "Tiff",
                rol = "Paladín"
            ))
            listaJugadores.add(Jugador(
                nombre = "Marcus",
                nickname = "Vex",
                rol = "Brujo"
            ))
            listaJugadores.add(Jugador(
                nombre = "Timaxius",
                nickname = "Cáos",
                rol = "Hechicero"
            ))
            listaJugadores.add(Jugador(
                nombre = "Maigrika",
                nickname = "Jefecita",
                rol = "Bardo"
            ))

            EquipoESportsTheme {
                PantallaPrincipal(
                    listaJugadores,
                    alAgregarJugador = {
                        listaJugadores.add(it)
                    },
                    alEliminarJugador = {
                        listaJugadores.remove(it)
                    }
                )
            }
        }
    }

    @Composable
    fun PantallaPrincipal(
        listaJugadores: List<Jugador>,
        alAgregarJugador: (Jugador) -> Unit,
        alEliminarJugador: (Jugador) -> Unit
    ){
        var modificandoJugadorExistente by rememberSaveable { mutableStateOf(false) }
        var nombreInput by rememberSaveable { mutableStateOf("") }
        var nicknameInput by rememberSaveable { mutableStateOf("") }
        var rolInput by rememberSaveable { mutableStateOf("") }
        var idEnEdicion by rememberSaveable { mutableStateOf(UUID( 0 , 0 )) }
        var jugadorEnEdicion by rememberSaveable { mutableStateOf(Jugador(UUID( 0 , 0 ), "","", "")) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(colorResource(R.color.blue_bigboy))
                .padding(horizontal = 6.dp)
        ) {
            TextoTitulo("TEAM TITANS")

            Spacer(modifier = Modifier
                .height(12.dp))

            if (modificandoJugadorExistente){
                jugadorEnEdicion = listaJugadores[listaJugadores.indexOfFirst { it.id == idEnEdicion }]
                FormularioEditarJugador(
                    jugador = jugadorEnEdicion,
                    cambioNombre = { nombreInput = it },
                    cambioNickname = { nicknameInput = it },
                    cambioRol = { rolInput = it }
                )
                Button(
                    onClick = {
                        jugadorEnEdicion.nombre = nombreInput
                        jugadorEnEdicion.nickname = nicknameInput
                        jugadorEnEdicion.rol = rolInput
                        modificandoJugadorExistente = false
                    }
                ) {
                    Text("Confirmar")
                }

            } else{
                jugadorEnEdicion = FormularioNuevoJugador(
                    nombreInput = nombreInput,
                    cambioNombre = { nombreInput = it },
                    nicknameInput = nicknameInput,
                    cambioNickname = { nicknameInput = it },
                    rolInput = rolInput,
                    cambioRol = { rolInput = it }
                )
                Button(
                    onClick = { alAgregarJugador(jugadorEnEdicion) }
                ) {
                    Text("Confirmar")
                }

            }
            //JUGADORES
            // - INFO
            // - BOTONES MODIFICAR ELIMINAR

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = listaJugadores,
                    key = {jugador -> jugador.id}
                ){
                    jugador ->
                    FichaJugador(jugador = jugador)
                    Row {
                        BotonModificarJugador(
                            modificarJugador = {
                                nombreInput = jugador.nombre
                                nicknameInput = jugador.nickname
                                rolInput = jugador.rol
                                idEnEdicion = jugador.id
                                modificandoJugadorExistente = true
                            }
                        )
                        BotonEliminarJugador(
                            alEliminar = { alEliminarJugador(jugador) }
                        )
                    }
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
        jugador: Jugador
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
                    text = jugador.rol,
                    textAlign = TextAlign.Right,
                    color = colorResource(R.color.brown_rusty),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 22.sp,
                    maxLines = 1,
                )
            }
        }
    }

    @Composable
    fun BotonEliminarJugador(
        alEliminar: () -> Unit
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = alEliminar
            ) {
                Text("Eliminar Jugador")
            }
        }
    }

    @Composable
    fun FormularioEditarJugador(
        jugador: Jugador,
        cambioNombre: (String) -> Unit,
        cambioNickname: (String) -> Unit,
        cambioRol: (String) -> Unit
    ){

        TextoEditable(
            etiqueta = "Nombre",
            valor = jugador.nombre,
            cambioTexto = cambioNombre,
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        TextoEditable(
            etiqueta = "Nickname",
            valor = jugador.nickname,
            cambioTexto = cambioNickname,
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        TextoEditable(
            etiqueta = "Rol",
            valor = jugador.rol,
            cambioTexto = cambioRol,
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }

    @Composable
    fun FormularioNuevoJugador(
        nombreInput: String,
        cambioNombre: (String) -> Unit,
        nicknameInput: String,
        cambioNickname: (String) -> Unit,
        rolInput: String,
        cambioRol: (String) -> Unit
    ): Jugador{
        TextoEditable(
            etiqueta = "Nombre",
            valor = nombreInput,
            cambioTexto = cambioNombre,
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        TextoEditable(
            etiqueta = "Nickname",
            valor = nicknameInput,
            cambioTexto = cambioNickname,
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        TextoEditable(
            etiqueta = "Rol",
            valor = rolInput,
            cambioTexto = cambioRol,
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        return Jugador(nombre = nombreInput, nickname = nicknameInput, rol = rolInput)
    }

    @Composable
    fun TextoEditable(
        etiqueta: String,
        valor: String,
        cambioTexto: (String) -> Unit,
        keyboardOptions: KeyboardOptions,
        modifier: Modifier = Modifier
    ){
        TextField(
            value = valor,
            onValueChange = cambioTexto,
            modifier = modifier
                .padding(bottom = 12.dp),
            label = {
                Text(etiqueta)
            },
            singleLine = true,
            keyboardOptions = keyboardOptions,
        )
    }

    @Composable
    fun BotonModificarJugador(
        //jugador: Jugador,
        modificarJugador: () -> Unit
    ){
        Button(
            onClick = {
                modificarJugador()
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Modificar")
        }
    }
}

data class Jugador constructor(
    val id: UUID = UUID.randomUUID(), //Para ids automáticos
    var nombre: String,
    var nickname: String,
    var rol: String
)