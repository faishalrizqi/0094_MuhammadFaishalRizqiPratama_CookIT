package com.muhammadfaishalrizqipratama0094.cookit.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadfaishalrizqipratama0094.cookit.data.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProfileRepository(application)
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadSavedProfile()
    }

    private fun loadSavedProfile() {
        viewModelScope.launch {
            repository.profileData.collect { profileData ->
                val dataFilled = profileData.name.isNotBlank() && profileData.email.isNotBlank()
                _uiState.value = ProfileUiState(
                    name = profileData.name,
                    email = profileData.email,
                    isEditing = !dataFilled,
                    dataFilled = dataFilled
                )
            }
        }
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(
            name = name,
            nameError = null
        )
        checkIfDataFilled()
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null
        )
        checkIfDataFilled()
    }

    private fun checkIfDataFilled() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            dataFilled = currentState.name.isNotBlank() && currentState.email.isNotBlank()
        )
    }

    fun validateAndSave(): Boolean {
        val currentState = _uiState.value
        var isValid = true
        var nameError: String? = null
        var emailError: String? = null

        if (currentState.name.isBlank()) {
            nameError = "Name cannot be empty"
            isValid = false
        }

        if (currentState.email.isBlank() || !isValidEmail(currentState.email)) {
            emailError = "Please enter a valid email address"
            isValid = false
        }

        _uiState.value = currentState.copy(
            nameError = nameError,
            emailError = emailError
        )

        if (isValid) {
            viewModelScope.launch {
                repository.saveProfile(currentState.name, currentState.email)
                _uiState.value = currentState.copy(
                    isEditing = false,
                    showSavedMessage = true
                )
                kotlinx.coroutines.delay(3000)
                _uiState.value = _uiState.value.copy(showSavedMessage = false)
            }
        }

        return isValid
    }

    fun toggleEditMode() {
        _uiState.value = _uiState.value.copy(
            isEditing = !_uiState.value.isEditing
        )
    }

    fun clearSavedMessage() {
        _uiState.value = _uiState.value.copy(showSavedMessage = false)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val isEditing: Boolean = true,
    val dataFilled: Boolean = false,
    val showSavedMessage: Boolean = false
)