package com.sakura.easyrent.ui.activitys.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakura.easyrent.control.repositories.APIRepository
import com.sakura.easyrent.model.Contract
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
class MainViewModel @Inject constructor(private val repository: APIRepository) : ViewModel() {

    // Channels:
    val channel: Channel<MainActivityIntentions> = Channel(Channel.UNLIMITED)

    // States:
    private val _states: MutableSharedFlow<MainActivityStates> = MutableSharedFlow(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val states: SharedFlow<MainActivityStates> get() = _states

    // Initializing:
    init {
        // Processing:
        intentionsProcessing()
    }

    // Companion:
    companion object {

        // Tags:
        const val TAG: String = "MainViewModel"
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
    private fun reducingStates(intentions: MainActivityIntentions) {
        // Reducing:
        viewModelScope.launch {
            // Trying:
            try {
                // Checking:
                when (intentions) {
                    // Developing:
                    is MainActivityIntentions.Contracts -> onContracts(intentions)
                }
            } catch (e: Exception) {
                // Emitting:
                _states.emit(MainActivityStates.FailureState(e.message!!, e))
            }
        }
    }

    // Method(OnContracts):
    private suspend fun onContracts(intention: MainActivityIntentions.Contracts) = request(
        // Fields:
        intention,
        repository.contract(intention.token)
    )

    // Method(Request):
    private suspend fun <T> request(intention: MainActivityIntentions, response: Response<T>) {
        // Checking:
        if (response.isSuccessful && response.body() != null) {
            // Initializing:
            val body = response.body()!!
            // Checking:
            when (intention) {
                // Emitting:
                is MainActivityIntentions.Contracts -> _states.emit(MainActivityStates.ContractsState(body as ArrayList<Contract>))
            }
        } else _states.emit(MainActivityStates.FailureState(response.toString(), null))
    }
}