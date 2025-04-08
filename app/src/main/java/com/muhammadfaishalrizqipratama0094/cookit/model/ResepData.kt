package com.muhammadfaishalrizqipratama0094.cookit.model

import com.muhammadfaishalrizqipratama0094.cookit.R

object ResepData {
    val resepList = listOf(
        Resep(
            id = 1,
            name = "Nasi Goreng",
            description = "Nasi goreng khas Indonesia dengan berbagai bumbu rempah",
            imageRes = R.drawable.nasi_goreng,
            ingredients = listOf(
                "2 piring nasi putih",
                "2 butir telur",
                "2 sdm kecap manis",
                "1 sdm saus tiram",
                "3 siung bawang putih",
                "5 siung bawang merah",
                "5 buah cabai rawit (sesuai selera)",
                "Garam secukupnya",
                "Penyedap rasa secukupnya",
                "2 sdm minyak goreng"
            ),
            steps = listOf(
                "Haluskan bawang merah, bawang putih, dan cabai rawit",
                "Panaskan minyak, tumis bumbu halus hingga harum",
                "Masukkan telur, aduk hingga setengah matang",
                "Tambahkan nasi putih, aduk hingga tercampur rata",
                "Tambahkan kecap manis, saus tiram, garam, dan penyedap rasa",
                "Aduk hingga semua bahan tercampur rata dan matang",
                "Sajikan dengan hiasan mentimun dan telur ceplok"
            ),
            cookingTime = 15,
            servings = 2,
            category = "Main Course"
        ),
        Resep(
            id = 2,
            name = "Ayam Goreng",
            description = "Ayam goreng renyah dengan bumbu tradisional",
            imageRes = R.drawable.ayam_goreng,
            ingredients = listOf(
                "500 gram ayam potong",
                "3 siung bawang putih",
                "1 sdt ketumbar",
                "1 ruas kunyit",
                "1 ruas jahe",
                "1 ruas lengkuas",
                "2 lembar daun salam",
                "2 lembar daun jeruk",
                "1 batang serai",
                "Garam secukupnya",
                "Air secukupnya",
                "Minyak untuk menggoreng"
            ),
            steps = listOf(
                "Haluskan bawang putih, ketumbar, kunyit, dan jahe",
                "Geprek lengkuas dan serai",
                "Rebus ayam dengan bumbu halus, lengkuas, serai, daun salam, dan daun jeruk",
                "Tambahkan garam secukupnya",
                "Masak hingga ayam empuk dan bumbu meresap",
                "Tiriskan ayam, lalu goreng dalam minyak panas hingga kecokelatan",
                "Sajikan dengan sambal dan lalapan"
            ),
            cookingTime = 45,
            servings = 4,
            category = "Main Course"
        ),
        Resep(
            id = 3,
            name = "Soto Ayam",
            description = "Sup ayam dengan kuah kuning kaya rempah",
            imageRes = R.drawable.soto_ayam,
            ingredients = listOf(
                "500 gram ayam",
                "2 liter air",
                "3 lembar daun salam",
                "3 lembar daun jeruk",
                "2 batang serai",
                "3 cm lengkuas",
                "1 sdt kunyit bubuk",
                "1 sdt ketumbar bubuk",
                "5 siung bawang merah",
                "3 siung bawang putih",
                "Garam secukupnya",
                "Lada secukupnya",
                "Bihun secukupnya",
                "Kol secukupnya",
                "Telur rebus",
                "Jeruk nipis",
                "Bawang goreng"
            ),
            steps = listOf(
                "Rebus ayam dengan air hingga mendidih, buang busa yang mengambang",
                "Haluskan bawang merah, bawang putih, kunyit, dan ketumbar",
                "Tumis bumbu halus hingga harum",
                "Masukkan tumisan bumbu ke dalam rebusan ayam",
                "Tambahkan daun salam, daun jeruk, serai, dan lengkuas",
                "Masukkan garam dan lada, koreksi rasa",
                "Masak hingga ayam empuk, angkat ayam dan suwir-suwir",
                "Rebus bihun dan siapkan kol iris",
                "Sajikan kuah soto dengan bihun, kol, suwiran ayam, telur rebus, perasan jeruk nipis, dan taburan bawang goreng"
            ),
            cookingTime = 60,
            servings = 6,
            category = "Soup"
        )
    )
}