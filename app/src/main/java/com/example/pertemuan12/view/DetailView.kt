package com.example.pertemuan12.view

import com.example.pertemuan12.navigation.DestinasiNavigasi

object DestinasiDetail: DestinasiNavigasi{
    override val route = "detail"
    override val titleRes ="Detail Mhs"
    const val Nim = "nim"
    val routesWithArg = "$route/{${Nim}im}"
}