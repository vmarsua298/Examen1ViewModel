package com.example.examen1viewmodel.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.examen1viewmodel.data.ApostarUIState
import com.example.examen1viewmodel.data.DataSource
import com.example.examen1viewmodel.data.LoteriaTipo
import com.example.examen1viewmodel.ui.ApostarViewModel

@Composable
fun ScreenMain(
    modifier: Modifier = Modifier,
    loterias: ArrayList<LoteriaTipo> = DataSource.loterias,
    viewModelApostar: ApostarViewModel,
    uiState: ApostarUIState,
    onClickCambiarPantalla: () -> Unit){

    Column {
        Text(
            text = "Bienvenido a la loteria VMS",
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(start = 20.dp, top = 50.dp)
        )
        Spacer(modifier = modifier.height(5.dp))
        ScrollLoteria(modifier, loterias, viewModelApostar, uiState)
        Spacer(modifier = modifier.height(15.dp))
        ApostarLoteria(modifier, viewModelApostar,uiState)
        Spacer(modifier = modifier.height(10.dp))
        Button(onClick = { viewModelApostar.intentarApuesta(uiState.loteriaNombre, uiState.loteriaCantidadApostada) },
            modifier = modifier.align(Alignment.CenterHorizontally)) {
            Text("Jugar loteria escrita")
        }
        Spacer(modifier = modifier.height(15.dp))
        TextosActualizandose(modifier, viewModelApostar, uiState)
        Spacer(modifier = modifier.height(10.dp))
        Button(onClick = onClickCambiarPantalla,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).align(Alignment.CenterHorizontally)) {
            Text(text = "Cambiar de pantalla")
        }
    }
}

@Composable
fun TextosActualizandose(modifier: Modifier, viewModelApostar: ApostarViewModel, uiState: ApostarUIState) {
    Column (modifier = modifier
        .fillMaxWidth()
        .background(Color.Gray).padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text(text = uiState.textoMostrar)
        Text(text = "Has jugado " + uiState.jugadoVeces + " a la loteria")
        Text(text = "Has ganado un total de " + uiState.ganadoTotal + " de la loteria")
        Text(text = "Has gastado un total de " + uiState.gastadoTotal + " de la loteria")
    }
}

@Composable
fun ScrollLoteria(
    modifier: Modifier,
    loterias: ArrayList<LoteriaTipo>,
    viewModelApostar: ApostarViewModel,
    uiState: ApostarUIState
) {
    LazyRow(){
        items(loterias){ loteria ->
            Card (
                modifier = modifier
                    .padding(8.dp)
                    .width(250.dp)){
                Text(
                    text = "Nombre: " + loteria.nombre,
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.Yellow).padding(5.dp)
                )
                Text(
                    text = "Premio: " + loteria.premio,
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.Cyan).padding(5.dp)
                )
                Button(
                    onClick = {viewModelApostar.setLoteriaApostar(loteria.nombre)},
                    modifier = modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = "Apostar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApostarLoteria(modifier: Modifier, viewModelApostar: ApostarViewModel, uiState: ApostarUIState) {

    Row {
        TextField(
            value = uiState.loteriaNombre,
            singleLine = true,
            onValueChange = { viewModelApostar.setLoteriaApostar(it)} ,
            label = { Text(text = "Loteria")},
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            value = uiState.loteriaCantidadApostada,
            singleLine = true,
            onValueChange = { viewModelApostar.setCantidadApostar(it)},
            label = { Text(text = "Dinero Apostado")},
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
    }
}