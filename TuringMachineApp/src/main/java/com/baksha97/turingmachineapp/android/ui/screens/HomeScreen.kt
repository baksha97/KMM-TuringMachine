package com.baksha97.turingmachineapp.android.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.baksha97.owl.ui.MainDestinations
import com.baksha97.owl.ui.theme.BlueTheme
import com.baksha97.turingmachineapp.android.R
import com.baksha97.turingmachineapp.android.courses
import com.baksha97.turingmachineapp.models.TuringMachine
import com.example.owl.ui.common.MachineListItem
import com.google.accompanist.insets.statusBarsHeight
import timber.log.Timber


@Composable
fun MachineAppBar() {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_lockup_white),
            contentDescription = null
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = stringResource(R.string.home_info)
            )
        }
    }
}

@Composable
fun MyMachines(
    courses: List<TuringMachine>,
    selectMachine: (TuringMachine) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight())
        }
        item {
            MachineAppBar()
        }
        itemsIndexed(courses) { _, course ->
            MyMachine(course, selectMachine)
        }
    }
}

@Composable
fun MyMachine(
    course: TuringMachine,
    selectCourse: (TuringMachine) -> Unit
) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        val navController = rememberNavController()

        Spacer(modifier = Modifier.width(16.dp))
        MachineListItem(
            course = course,
            onClick = {
                selectCourse(course)
                      },
            shape = RoundedCornerShape(topStart = 24.dp),
            modifier = Modifier.height(96.dp)
        )
    }
}



@Preview(name = "My Courses")
@Composable
private fun MyCoursesPreview() {
    BlueTheme {
        MyMachines(
            courses = courses,
            selectMachine = {}
        )
    }
}

@Preview
@Composable
fun HomeScreen(onSelectMachine: (TuringMachine) -> Unit = {}) {
    val scrollState = rememberScrollState()
    MyMachines(courses = courses, selectMachine = {
        Timber.d(it.name)
        onSelectMachine(it)
    })
}
