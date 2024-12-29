package com.example.pertemuan12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pertemuan12.Mahasiswa.Mahasiswa
import com.example.pertemuan12.Repository.MahasiswaRepository
import kotlinx.coroutines.launch

sealed class HomeUiState{
    data class Succes(val mahasiswa : List<Mahasiswa>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel (
    private val mhs:MahasiswaRepository
) :ViewModel(){
    var mhsUiState:HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set


    init {
        getMhs()
    }
    fun getMhs(){
        viewModelScope.launch {
            mhsUiState = HomeUiState.Loading
            mhsUiState = try {
                HomeUiState.Succes(mhs.getMahasiswa())
            } catch (e:Exception){
                HomeUiState.Error
            } catch (e:HttpException){
                HomeUiState.Error
            }
        }
    }
}