package com.example.pertemuan12.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan12.CostumWidget.CostumTopAppBar
import com.example.pertemuan12.Mahasiswa.Mahasiswa
import com.example.pertemuan12.navigation.DestinasiNavigasi
import com.example.pertemuan12.viewmodel.DetailUiState
import com.example.pertemuan12.viewmodel.DetailViewModel
import com.example.pertemuan12.viewmodel.PenyediaViewModel

object DestinasiDetail: DestinasiNavigasi{
    override val route = "detail"
    override val titleRes ="Detail Mhs"
    const val Nim = "nim"
    val routesWithArg = "$route/{${Nim}im}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUi(
    navigateBack: () -> Unit,
    navigateToitemUpdate: ()->Unit,
    modifier: Modifier =Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold (
        topBar = {
            CostumTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getMahasiswabyNim()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToitemUpdate,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mahasiswa"
                )
            }
        }
    ){ innerPadding ->
        DetailStatus(
            modifier = Modifier.padding(innerPadding),
            detailUiState = viewModel.mahasiswaDetailState,
            retryAction = {viewModel.getMahasiswabyNim() }
        )
    }
}

@Composable
fun DetailStatus(
    retryAction:() -> Unit,
    modifier: Modifier =Modifier,
    detailUiState: DetailUiState
) {
    when(detailUiState){
        is DetailUiState.Loading -> Onloading(modifier = modifier.fillMaxSize())

        is DetailUiState.Succes -> {
            if (detailUiState.mahasiswa.nim.isEmpty()){
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Data tidak ditemukan")
                }
            } else {
                itemDetailMhs(
                    mahasiswa = detailUiState.mahasiswa,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }

}

@Composable
fun itemDetailMhs(
    modifier: Modifier = Modifier,
    mahasiswa: Mahasiswa
){
    Card (
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)

    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            DetailMhs(judul = "NIM", isinya = mahasiswa.nim)
            DetailMhs(judul = "NAMA", isinya = mahasiswa.nama)
            DetailMhs(judul = "JenisKelamin", isinya = mahasiswa.jenisKelamin)
            DetailMhs(judul = "Alamat", isinya = mahasiswa.alamat)
            DetailMhs(judul = "Kelas", isinya = mahasiswa.kelas)
            DetailMhs(judul = "Angkatan", isinya = mahasiswa.angkatan)
        }

    }

}

@Composable
fun DetailMhs(
    modifier: Modifier = Modifier,
    judul : String,
    isinya : String,
){
    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "$judul : ",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

}