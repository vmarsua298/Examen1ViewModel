package com.example.examen1viewmodel.ui

import androidx.lifecycle.ViewModel
import com.example.examen1viewmodel.data.ApostarUIState
import com.example.examen1viewmodel.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ApostarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ApostarUIState())
    val uiState: StateFlow<ApostarUIState> = _uiState.asStateFlow()
    private val loterias = DataSource.loterias

    fun setLoteriaApostar(escritoEnLoteriaNombre: String){
        _uiState.update { currentState ->
            currentState.copy(
                loteriaNombre = escritoEnLoteriaNombre
            )
        }
    }

    fun setCantidadApostar(escritoEnApostarDinero: String){
        _uiState.update { currentState ->
            currentState.copy(
                loteriaCantidadApostada = escritoEnApostarDinero
            )
        }
    }

    fun intentarApuesta(loteriaNombrePasado: String, loteriaCantidadApostada: String): Unit{
        var jugadoVeces : Int = uiState.value.jugadoVeces
        var gastadoTotal: Int = uiState.value.gastadoTotal
        var ganadoTotal: Int = uiState.value.ganadoTotal
        var textoMostrar: String

        val loteria = loterias.firstOrNull{ it.nombre == loteriaNombrePasado}
        if (loteria == null){
            textoMostrar = "No existe ninguna loteria con ese nombre"
            _uiState.update { currentState ->
                currentState.copy(
                    textoMostrar = textoMostrar
                )
            }
            return // ya no continua mas si entra en el if

        }

        val cantidadApostada: Int

        try{
            cantidadApostada = loteriaCantidadApostada.toInt()
            /**
             * Entra en el if como verdadero si la cantidad que hemos puesto en el TextField es un numero negativo o 0
             */
            if (cantidadApostada<=0){
                textoMostrar = "No se puede comprar la loteria con 0 euros"
                _uiState.update { currentState ->
                    currentState.copy(
                        textoMostrar = textoMostrar
                    )
                }
                return // ya no continua mas si entra en el if
            }

        } catch (e: Exception){
            textoMostrar = "Cantidad apostada no es entero"
            _uiState.update { currentState ->
                currentState.copy(
                    textoMostrar = textoMostrar
                )
            }
            return // ya no continua mas si entra en el if
        }

        val numeroLoteriaSacado = (0..4).random()
        val numeroLoteriaComprado = (0..4).random()

        gastadoTotal += cantidadApostada
        jugadoVeces += 1

        if(numeroLoteriaSacado == numeroLoteriaComprado){
            textoMostrar = "Ha ganado la loteria"
            ganadoTotal += loteria.premio * cantidadApostada
        }else{
            textoMostrar = "No ha ganado la loteria"
        }

        _uiState.update { currentState ->
            currentState.copy(
                textoMostrar = textoMostrar,
                gastadoTotal = gastadoTotal,
                jugadoVeces = jugadoVeces,
                ganadoTotal = ganadoTotal
            )
        }
    }
}