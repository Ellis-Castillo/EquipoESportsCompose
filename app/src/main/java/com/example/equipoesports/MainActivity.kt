package com.example.equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.equipoesports.ui.theme.EquipoESportsTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val listaJugadores = remember { mutableStateListOf(
                Jugador(
                    nombre = "Sharx",
                    nickname = "Trinchas",
                    rol = "Bárbara"
                ),
                Jugador(
                    nombre = "Likura",
                    nickname = "Tiff",
                    rol = "Paladín"
                ),
                Jugador(
                    nombre = "Marcus",
                    nickname = "Vex",
                    rol = "Brujo"
                ),
                Jugador(
                    nombre = "Timaxius",
                    nickname = "Cáos",
                    rol = "Hechicero"
                ),
                Jugador(
                    nombre = "Maigrika",
                    nickname = "Jefecita",
                    rol = "Bardo"
                )
            ) }

            EquipoESportsTheme {
                PantallaPrincipal(
                    listaJugadores,
                    alAgregarJugador = {
                        listaJugadores.add(it)
                    },
                    alEliminarJugador = {
                        listaJugadores.remove(it)
                    },
                    alEditarJugador = {
                        jugador, i -> listaJugadores[i] = jugador
                    }
                )
            }
        }
    }

    @Composable
    fun PantallaPrincipal(
        listaJugadores: SnapshotStateList<Jugador>,
        alAgregarJugador: (Jugador) -> Unit,
        alEliminarJugador: (Jugador) -> Unit,
        alEditarJugador: (Jugador, Int) -> Unit
    ){
        var modificandoJugadorExistente by rememberSaveable { mutableStateOf(false) }
        var nombreInput by rememberSaveable { mutableStateOf("") }
        var nicknameInput by rememberSaveable { mutableStateOf("") }
        var rolInput by rememberSaveable { mutableStateOf("") }
        var idEnEdicion by remember { mutableStateOf<UUID?>(null) }
        var jugadorEnEdicion by remember { mutableStateOf<Jugador?>(null) }
        var abrirError by remember { mutableStateOf(false) }
        var jugadorAEliminar by remember { mutableStateOf<Jugador?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 8.dp)
        ) {
            Spacer( modifier = Modifier.height(8.dp) )

            TextoTitulo(
                "TEAM TITANS",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = RoundedCornerShape(25)
                    )
            )

            Spacer( modifier = Modifier.height(8.dp) )

            if (modificandoJugadorExistente){

                FormularioJugador(
                    nombreInput = nombreInput,
                    cambioNombre = { nombreInput = it },
                    nicknameInput = nicknameInput,
                    cambioNickname = { nicknameInput = it },
                    rolInput = rolInput,
                    cambioRol = { rolInput = it },
                    modifier = Modifier
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            if (
                                nombreInput.isNotBlank()
                                && nicknameInput.isNotBlank()
                                && rolInput.isNotBlank()
                            ){
                                val jugadorActualizado = jugadorEnEdicion!!.copy(
                                    nombre = nombreInput,
                                    nickname = nicknameInput,
                                    rol = rolInput
                                )
                                val indice = listaJugadores.indexOfFirst {
                                    it.key == idEnEdicion
                                }

                                if (indice != -1) {
                                    alEditarJugador(jugadorActualizado, indice)
                                }

                                idEnEdicion = null
                                jugadorEnEdicion = null
                                modificandoJugadorExistente = false
                                nombreInput = ""
                                nicknameInput = ""
                                rolInput = ""

                            } else{
                                abrirError = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text("Confirmar")
                    }
                    Button(
                        onClick = {
                            nombreInput = ""
                            nicknameInput = ""
                            rolInput = ""
                            modificandoJugadorExistente = false
                            idEnEdicion = null
                            jugadorEnEdicion = null
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text("Cancelar")
                    }
                }

                TextoTitulo(
                    "Editando Jugador",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(50)
                        )
                )

                Spacer( modifier = Modifier.height(12.dp) )

            } else{
                FormularioJugador(
                    nombreInput = nombreInput,
                    cambioNombre = { nombreInput = it },
                    nicknameInput = nicknameInput,
                    cambioNickname = { nicknameInput = it },
                    rolInput = rolInput,
                    cambioRol = { rolInput = it },
                    modifier = Modifier
                )
                Button(
                    onClick = {
                        if (
                            nombreInput.isNotBlank()
                            && nicknameInput.isNotBlank()
                            && rolInput.isNotBlank()
                        ){
                            jugadorEnEdicion = Jugador(
                                nombre = nombreInput,
                                nickname = nicknameInput,
                                rol = rolInput
                            )
                            alAgregarJugador(jugadorEnEdicion!!)
                            nombreInput = ""
                            nicknameInput = ""
                            rolInput = ""
                            jugadorEnEdicion = null
                        } else{
                            abrirError = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                ) {
                    Text("Añadir Jugador")
                }

            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(4.dp)
            ) {
                items(
                    items = listaJugadores,
                    key = { jugador -> jugador.key }
                ){
                    jugador ->
                    FichaJugador(jugador = jugador)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        BotonEditarJugador(
                            editarJugador = {
                                jugadorEnEdicion = jugador
                                idEnEdicion = jugador.key

                                nombreInput = jugador.nombre
                                nicknameInput = jugador.nickname
                                rolInput = jugador.rol

                                modificandoJugadorExistente = true
                            }
                        )

                        BotonEliminarJugador(
                            alEliminar = { jugadorAEliminar = jugador }
                        )
                    }
                }
            }

            if (jugadorAEliminar != null) {
                PedirConfirmacionEliminar(
                    jugador = jugadorAEliminar!!,
                    alEliminar = {
                        alEliminarJugador(jugadorAEliminar!!)
                        jugadorAEliminar = null
                    },
                    alCancelar = {
                        jugadorAEliminar = null
                    }
                )
            }

            if (abrirError){
                PopupError(
                    onDismissRequest = {
                        abrirError = false
                    },
                    "Error: Los campos no pueden estar vacíos"
                )
            }
        }
    }

    @Composable
    fun PopupError(onDismissRequest: () -> Unit, mensaje: String){
        Dialog(onDismissRequest = {onDismissRequest()}) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Text(
                    text = mensaje,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun TextoTitulo(
        titulo: String,
        color: Color,
        style: TextStyle,
        modifier: Modifier
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = titulo,
                color = color,
                style = style,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            )
        }
    }

    @Composable
    fun Separador(){
        HorizontalDivider(
            modifier = Modifier
                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                .clip(shape = RoundedCornerShape(75)),
            thickness = 8.dp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }

    @Composable
    fun FichaJugador(
        jugador: Jugador
    ){
        Separador()

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(50)
            )
            .padding(start = 24.dp, end = 8.dp)
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
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 22.sp,
                    lineHeight = 22.sp,
                    maxLines = 1,
                )
                Text(
                    text = jugador.nickname,
                    color = MaterialTheme.colorScheme.onSecondary,
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
                    color = MaterialTheme.colorScheme.onSecondary,
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
        Button(
            onClick = alEliminar,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text("Eliminar Jugador")
        }
    }

    @Composable
    fun PedirConfirmacionEliminar(
        jugador: Jugador,
        alEliminar: () -> Unit,
        alCancelar: () -> Unit
    ){
        AlertDialog(
            onDismissRequest = alCancelar,
            title = {
                Text("Confirmar eliminación")
            },
            text = {
                Text("¿Realmente deseas eliminar a ${jugador.nombre}?")
            },
            confirmButton = {
                Button(
                    onClick = alEliminar
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                Button(
                    onClick = alCancelar
                ) {
                    Text("Cancelar")
                }
            },
            containerColor = MaterialTheme.colorScheme.errorContainer,
            textContentColor = MaterialTheme.colorScheme.onErrorContainer,
            titleContentColor = MaterialTheme.colorScheme.error
        )
    }

    @Composable
    fun FormularioJugador(
        nombreInput: String,
        cambioNombre: (String) -> Unit,
        nicknameInput: String,
        cambioNickname: (String) -> Unit,
        rolInput: String,
        cambioRol: (String) -> Unit,
        modifier: Modifier
    ) {
        Column(
            modifier = modifier
        ) {
            TextoEditable(
                etiqueta = "Nombre",
                valor = nombreInput,
                cambioTexto = cambioNombre,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            TextoEditable(
                etiqueta = "Nickname",
                valor = nicknameInput,
                cambioTexto = cambioNickname,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            TextoEditable(
                etiqueta = "Rol",
                valor = rolInput,
                cambioTexto = cambioRol,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 8.dp)
            )
        }
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
            modifier = modifier,
            label = {
                Text(etiqueta)
            },
            singleLine = true,
            keyboardOptions = keyboardOptions,
        )
    }

    @Composable
    fun BotonEditarJugador(
        editarJugador: () -> Unit
    ){
        Button(
            onClick = editarJugador,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text("Editar Jugador")
        }
    }
}

data class Jugador(
    val key: UUID = UUID.randomUUID(),
    var nombre: String,
    var nickname: String,
    var rol: String
)