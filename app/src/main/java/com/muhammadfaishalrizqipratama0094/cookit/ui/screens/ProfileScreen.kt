package com.muhammadfaishalrizqipratama0094.cookit.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muhammadfaishalrizqipratama0094.cookit.R
import com.muhammadfaishalrizqipratama0094.cookit.ui.viewmodels.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    val saveText = stringResource(id = R.string.save)
    val editText = stringResource(id = R.string.edit)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.profile),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = uiState.name,
            onValueChange = { viewModel.updateName(it) },
            label = { Text(stringResource(id = R.string.name)) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = uiState.nameError != null,
            supportingText = { uiState.nameError?.let { error -> Text(text = error) } },
            enabled = uiState.isEditing
        )
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text(stringResource(id = R.string.email)) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            isError = uiState.emailError != null,
            supportingText = { uiState.emailError?.let { error -> Text(text = error) } },
            enabled = uiState.isEditing
        )
        Button(
            onClick = {
                if (uiState.isEditing) {
                    val isValid = viewModel.validateAndSave()
                    if (isValid) {
                        scope.launch {
                        }
                    }
                } else {
                    viewModel.toggleEditMode()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = if (uiState.isEditing) saveText else editText)
        }

        if (uiState.showSavedMessage) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = stringResource(id = R.string.profile_updated),
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            LaunchedEffect(uiState.showSavedMessage) {
                kotlinx.coroutines.delay(3000)
                viewModel.clearSavedMessage()
            }
        }
    }
}
