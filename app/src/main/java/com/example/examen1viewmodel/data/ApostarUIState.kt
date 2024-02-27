package com.example.examen1viewmodel.data

/**
 * Se crea una clase de datos es decir un objeto con distintos contenidos en el que se estará almacenando
 *  y cambiando datos constantemente segun las condiciones de cada programa.
 */
data class ApostarUIState(
    val loteriaNombre: String = "",
    val loteriaCantidadApostada: String = "0",
    val jugadoVeces:Int =0,
    val gastadoTotal: Int=0,
    val ganadoTotal: Int=0,
    val textoMostrar: String ="No has jugado ninguna loteria",
) {
}