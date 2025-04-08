package com.muhammadfaishalrizqipratama0094.cookit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KategoriScreen(navController: NavController) {
    val categories = ResepData.resepList
        .map { it.category }
        .distinct()
        .sorted()

    var selectedCategory by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.categories),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = {
                        selectedCategory = if (selectedCategory == category) null else category
                    },
                    label = { Text(text = category) },
                    leadingIcon = if (selectedCategory == category) {
                        {
                            Icon(
                                imageVector = Icons.Default.Category,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    } else null
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            val filteredRecipes = if (selectedCategory != null) {
                ResepData.resepList.filter { it.category == selectedCategory }
            } else {
                ResepData.resepList
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