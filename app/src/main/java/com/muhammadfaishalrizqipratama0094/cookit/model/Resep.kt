package com.muhammadfaishalrizqipratama0094.cookit.model

data class Resep(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val ingredients: List<String>,
    val steps: List<String>,
    val cookingTime: Int, // dalam menit
    val servings: Int,
    val category: String,
)