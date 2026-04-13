package com.rheivalseptian8600.asesment1mobpro.ui.screen
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rheivalseptian8600.asesment1mobpro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var nama by rememberSaveable { mutableStateOf("") }
    var namaTerakhir by rememberSaveable { mutableStateOf("") }
    var angkaDadu by rememberSaveable { mutableIntStateOf(1) }
    var sudahDilempar by rememberSaveable { mutableStateOf(false) }
    var namaError by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = { navController.navigate("about") }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "About")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nama,
                onValueChange = {
                    nama = it
                    namaError = false
                },
                label = { Text(stringResource(id = R.string.label_nama)) },
                isError = namaError,
                supportingText = {
                    if (namaError) {
                        Text(text = "Tidak boleh kosong.")
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                singleLine = true
            )

            val imageRes = when (angkaDadu) {
                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                else -> R.drawable.dice6
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(200.dp).padding(32.dp)
            )
            Button(
                onClick = {
                    if (nama.isNotBlank()) {
                        namaError = false
                        angkaDadu = (1..6).random()
                        sudahDilempar = true
                        namaTerakhir = nama
                    } else {
                        namaError = true
                        sudahDilempar = false
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.lempar_dadu))
            }

            if (sudahDilempar && nama.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(id = R.string.hasil_dadu, namaTerakhir, angkaDadu),
                    style = MaterialTheme.typography.titleMedium
                )

                Button(
                    onClick = { shareData(context, namaTerakhir, angkaDadu) },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.bagikan))
                }
            }
        }
    }
}

private fun shareData(context: Context, nama: String, hasil: Int) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.hasil_dadu, nama, hasil))
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.bagikan)))
}