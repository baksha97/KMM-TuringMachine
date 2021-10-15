package com.baksha97.turingmachineapp.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baksha97.turingmachineapp.android.R
import com.baksha97.turingmachineapp.android.courses
import com.baksha97.turingmachineapp.models.TuringMachine
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun RunMachineScreen(
    machineId: String?,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight())
        }
        item {
            MachineAppBar()
        }
        item {
            Text(machineId ?: "null")
        }
    }
}

@Preview
@Composable
fun RunMachineScreenPreview() {
    RunMachineScreen("")
}