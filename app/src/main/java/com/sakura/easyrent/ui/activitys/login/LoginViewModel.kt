package com.sakura.easyrent.ui.activitys.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakura.easyrent.control.repositories.APIRepository
import com.sakura.easyrent.model.AccessToken
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
        const val TAG: String = "LoginViewModel"
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
    private suspend fun onRegister(intention: LoginActivityIntentions.Register) = request(
        // Fields(Request):
        intention,
        repository.register(intention.user)
    )

    // Method(OnLogin):
    private suspend fun onLogin(intention: LoginActivityIntentions.Login) = request(
        // Fields(Request):
        intention,
        repository.login(intention.user)
    )

    // Method(Request):
    private suspend fun <T> request(intention: LoginActivityIntentions, response: Response<T>) {
        // Checking:
        if (response.isSuccessful && response.body() != null) {
            // Initializing:
            val body = response.body()!!
            // Checking:
            when (intention) {
                // Emitting:
                is LoginActivityIntentions.Register -> _states.emit(LoginActivityStates.RegisterState(true, intention.user))
                is LoginActivityIntentions.Login -> _states.emit(LoginActivityStates.LoginState(true, body as AccessToken))
            }
        } else _states.emit(LoginActivityStates.FailureState(response.toString(), null))
    }
}