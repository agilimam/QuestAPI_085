package com.example.pertemuan12.viewmodel

import android.provider.ContactsContract.Intents.Insert
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan12.Mahasiswa.Mahasiswa
import com.example.pertemuan12.Repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel (
    private val  mhs:MahasiswaRepository
): ViewModel(){

    var uiState by mutableStateOf(InsertUiState())

        private set

    fun updateInsertMhsState(inserUiEvent.InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertMhs(){
        viewModelScope.launch {
            try {
                mhs.insertMahasiswa(uiState.insertUiEvent.toMhs())
            }
        }
    }
}

fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Mahasiswa.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan=angkatan,
)