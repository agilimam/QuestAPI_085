package com.example.pertemuan12.viewmodel

import com.example.pertemuan12.Mahasiswa.Mahasiswa

sealed class HomeUiState{
    data class Succes(val mahasiswa : List<Mahasiswa>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}


