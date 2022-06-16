package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetest.destinations.AnimalPhotoScreenDestination
import com.example.composetest.ui.theme.ComposeTestTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency

class MainActivity : ComponentActivity() {

    private val list: List<String> = listOf(
        "Komodo",
        "Burung Jalak Bali",
        "Harimau Sumatera",
        "Orang Utan Kalimantan",
        "Gajah Sumatera"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                val someList = remember { mutableStateListOf<String>() }
                someList.addAll(list)

                DestinationsNavHost(navGraph = NavGraphs.root, dependenciesContainerBuilder = {
                    dependency(someList)
                })
            }
        }
    }
}

@Destination(start = true)
@Composable
fun ListAnimalScreen(navController: DestinationsNavigator, someList: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize(), content = {
        itemsIndexed(someList) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(AnimalPhotoScreenDestination(item))
//                        navController.navigate("AnimalPhoto/$index/$item")
                    }) {
                Text(someList[index])
            }
        }
    })
}

@Destination
@Composable
fun AnimalPhotoScreen(type: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = type, fontSize = 16.sp)
        when (type) {
            "Komodo" -> {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.komodo),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }
            "Burung Jalak Bali" -> {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.burung_jalak_bali),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }
            "Harimau Sumatera" -> {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.harimau_sumatra),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }
            "Orang Utan Kalimantan" -> {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.orang_utan_kalimantan),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }
            "Gajah Sumatera" -> {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.gajah_sumatera),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}