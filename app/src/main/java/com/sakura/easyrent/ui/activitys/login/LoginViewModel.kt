package com.sakura.easyrent.ui.activitys.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakura.easyrent.control.repositories.APIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: APIRepository) : ViewModel() {

    // Channels:
    val channel: Channel<LoginActivityIntentions> = Channel(Channel.UNLIMITED)

    // States:
    private val _states: MutableSharedFlow<LoginActivityStates> =
        MutableSharedFlow(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val states: SharedFlow<LoginActivityStates> get() = _states

    // Initializing:
    init {
        // Processing:
        intentionsProcessing()
    }

    // Companion:
    companion object {

        // Tags:
        const val TAG: String = "HomeViewModel"
    }

    /** [intentionsProcessing] this kotlin function is used to process any sent intention throw any channels */
    private fun intentionsProcessing() {
        // Processing:
        viewModelScope.launch {
            // Collecting:
            channel.consumeAsFlow().collect {
                // Logging:
                Log.d(TAG, "intentionsProcessing: $it")
                // Reducing:
                reducingStates(it)
            }
        }
    }

    /** [reducingStates] this kotlin function is used to reduce states of sent intentions by [intentionsProcessing] */
    private fun reducingStates(intentions: LoginActivityIntentions) {
        // Reducing:
        viewModelScope.launch {
            // Trying:
            try {
                // Checking:
                when (intentions) {
                    // Developing:
                    is LoginActivityIntentions.Register -> onRegister(intentions)
                    is LoginActivityIntentions.Login -> onLogin(intentions)
                }
            } catch (e: Exception) {
                // Emitting:
                _states.emit(LoginActivityStates.FailureState(e.message!!, e))
            }
        }
    }

    // Method(OnRegister):
    private fun onRegister(intention: LoginActivityIntentions.Register) {
        // Lunching:
        viewModelScope.launch {
            // Requesting:
            request(repository.register(intention.user)) {
                // Emitting:
                _states.emit(LoginActivityStates.RegisterState(true))
            }
        }
    }

    // Method(OnLogin):
    private fun onLogin(intention: LoginActivityIntentions.Login) {
        // Lunching:
        viewModelScope.launch {
            // Requesting:
            request(repository.login(intention.user)) {
                // Emitting:
                _states.emit(LoginActivityStates.LoginState(true, it))
            }
        }
    }

    // Method(Request):
    private suspend fun <T> request(response: Response<T>, onSuccess: suspend (response: T) -> Unit) {
        // Checking:
        if (response.isSuccessful && response.body() != null) onSuccess(response.body()!!)
        else _states.emit(LoginActivityStates.FailureState(response.toString(), null))
    }
}