package com.example.pertemuan12.viewmodel

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pertemuan12.MahasiswaApplications

object PenyediaViewModel {
    val Factory = viewModelFactory {
        run {
            initializer { HomeViewModel(aplikasiMahasiswa().container.kontakRepository) }
            initializer { InsertViewModel(aplikasiMahasiswa().container.kontakRepository) }
        }
    }
}

fun CreationExtras.aplikasiMahasiswa():MahasiswaApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)