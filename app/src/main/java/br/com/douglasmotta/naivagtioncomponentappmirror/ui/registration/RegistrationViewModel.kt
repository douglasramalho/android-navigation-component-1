package br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.douglasmotta.naivagtioncomponentappmirror.R

class RegistrationViewModel : ViewModel() {

    sealed class RegistrationState {
        object CollectProfileData :  RegistrationState()
        object CollectCredentials : RegistrationState()
        object RegistrationCompleted : RegistrationState()
        class InvalidProfileData(val fields: List<Pair<String, Int>>) : RegistrationState()
        class InvalidCredentials(val fields: List<Pair<String, Int>>) : RegistrationState()
    }

    private val _registrationStateEvent = MutableLiveData<RegistrationState>(RegistrationState.CollectProfileData)
    val registrationStateEvent: LiveData<RegistrationState>
        get() = _registrationStateEvent

    var authToken = ""
        private set

    fun collectProfileData(name: String, bio: String) {
        if (isValidProfileData(name, bio)) {
            // Persist data
            _registrationStateEvent.value = RegistrationState.CollectCredentials
        }
    }

    private fun isValidProfileData(name: String, bio: String): Boolean {
        val invalidFields = arrayListOf<Pair<String, Int>>()
        if (name.isEmpty()) {
            invalidFields.add(INPUT_NAME)
        }

        if (bio.isEmpty()) {
            invalidFields.add(INPUT_BIO)
        }

        if (invalidFields.isNotEmpty()) {
            _registrationStateEvent.value = RegistrationState.InvalidProfileData(invalidFields)
            return false
        }

        return true
    }

    fun createCredentials(username: String, password: String) {
        if (isValidCredentials(username, password)) {
            // ... create account
            // ... authenticate
            this.authToken = "token"
            _registrationStateEvent.value = RegistrationState.RegistrationCompleted
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        val invalidFields = arrayListOf<Pair<String, Int>>()
        if (username.isEmpty()) {
            invalidFields.add(INPUT_USERNAME)
        }

        if (password.isEmpty()) {
            invalidFields.add(INPUT_PASSWORD)
        }

        if (invalidFields.isNotEmpty()) {
            _registrationStateEvent.value = RegistrationState.InvalidCredentials(invalidFields)
            return false
        }

        return true
    }

    fun userCancelledRegistration() : Boolean {
        authToken = ""
        _registrationStateEvent.value = RegistrationState.CollectProfileData
        return true
    }

    companion object {
        val INPUT_NAME = "INPUT_NAME" to R.string.profile_data_input_layout_error_invalid_name
        val INPUT_BIO = "INPUT_BIO" to R.string.profile_data_input_layout_error_invalid_bio
        val INPUT_USERNAME = "INPUT_USERNAME" to R.string.choose_credentials_input_layout_error_invalid_username
        val INPUT_PASSWORD = "INPUT_PASSWORD" to R.string.choose_credentials_input_layout_error_invalid_password
    }
}