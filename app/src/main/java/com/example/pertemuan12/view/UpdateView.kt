package com.example.pertemuan12.view

import com.example.pertemuan12.navigation.DestinasiNavigasi

object DestinasiUpdate: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Mhs"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}