package com.example.listaslazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listaslazy.repository.GameCard
import com.example.listaslazy.repository.Studiocard
import com.example.listaslazy.repository.getAllGames
import com.example.listaslazy.repository.getGamesByStudio
import com.example.listaslazy.ui.theme.ListasLazyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListasLazyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GamesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GamesScreen(modifier: Modifier = Modifier) {

    var studio by remember { mutableStateOf("") }

    var gamesListByStudio by remember {
        mutableStateOf(getGamesByStudio(""))
    }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Meus jogos favoritos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = studio,
            onValueChange = { studio = it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome do estudio") },
            trailingIcon = {
                IconButton(onClick = { gamesListByStudio = getGamesByStudio(studio) }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                }
            }
        )

        LazyRow() {
            items(gamesListByStudio) {
                Studiocard(it)
            }
        }

        LazyColumn() {
            items (gamesListByStudio) {
                GameCard(it)
            }
        }






    }
}