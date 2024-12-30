package com.example.pertemuan12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pertemuan12.Mahasiswa.Mahasiswa
import com.example.pertemuan12.Repository.MahasiswaRepository
import com.example.pertemuan12.view.DestinasiDetail
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState{
    data class Succes(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val Mhs: MahasiswaRepository
) :ViewModel(){
    var mahasiswaDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _nim :String = checkNotNull(savedStateHandle[DestinasiDetail.Nim])

    init {
        getMahasiswabyNim()

    }

    fun getMahasiswabyNim(){
        viewModelScope.launch {
            mahasiswaDetailState = DetailUiState.Loading
            mahasiswaDetailState = try {
                val mahasiswa = Mhs.getMahasiswaByNim(_nim)
                DetailUiState.Succes(mahasiswa)
            }catch (e: IOException){
                DetailUiState.Error
            }catch (e: HttpException){
                DetailUiState.Error
            }
        }
    }
}


