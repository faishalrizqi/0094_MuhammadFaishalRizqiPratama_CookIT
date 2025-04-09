package com.muhammadfaishalrizqipratama0094.cookit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muhammadfaishalrizqipratama0094.cookit.R
import com.muhammadfaishalrizqipratama0094.cookit.model.ResepData
import com.muhammadfaishalrizqipratama0094.cookit.navigation.Screen
import com.muhammadfaishalrizqipratama0094.cookit.ui.components.KartuResep

@Composable
fun HomeScreen(navController: NavController) {
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            placeholder = { Text(stringResource(id = R.string.search_recipes)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        Text(
            text = stringResource(id = R.string.all_recipes),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            val filteredRecipes = if (search.isBlank()) {
                ResepData.resepList
            } else {
                ResepData.resepList.filter {
                    it.name.contains(search, ignoreCase = true) ||
                            it.description.contains(search, ignoreCase = true) ||
                            it.category.contains(search, ignoreCase = true)
                }
            }

            items(filteredRecipes) { resep ->
                KartuResep (
                    resep = resep,
                    onClick = {
                        navController.navigate(Screen.ResepDetail.createRoute(resep.id))
                    }
                )
            }
        }
    }
}