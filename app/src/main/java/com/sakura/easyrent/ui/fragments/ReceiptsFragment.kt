package com.sakura.easyrent.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sakura.easyrent.control.adapters.ReceiptsAdapter
import com.sakura.easyrent.control.managers.SPManager
import com.sakura.easyrent.control.utils.APIUtils
import com.sakura.easyrent.databinding.FragmentReceiptsBinding
import com.sakura.easyrent.ui.activitys.main.MainActivityIntentions
import com.sakura.easyrent.ui.activitys.main.MainActivityStates
import com.sakura.easyrent.ui.activitys.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReceiptsFragment : Fragment() {

    // Fields:
    private var _binding: FragmentReceiptsBinding? = null
    private val binding: FragmentReceiptsBinding get() = _binding!!
    private val model: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    // Fields(Adapters):
    private lateinit var adapter: ReceiptsAdapter

    // Fields(Managers):
    @Inject lateinit var manager: SPManager

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "ReceiptsFragment"
    }

    // Method(OnCreateView):
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Initializing:
        _binding = FragmentReceiptsBinding.inflate(layoutInflater, container, false)
        // Returning:
        return binding.root
    }

    // Method(OnViewCreated):
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // Here we will do our work.
        // Super:
        super.onViewCreated(view, savedInstanceState)
        // Initializing(UI):
        progressBar()
        // Rendering:
        lifecycleScope.launchWhenCreated { render() }
    }

    // Method(Render)
    private suspend fun render() {
        // Sending:
        model.channel.send(
            // Fields(Channel):
            MainActivityIntentions.Contracts(
                // Fields(Contracts):
                APIUtils.generateBearerToken((manager.read(SPManager.ACCESS_TOKEN, "")) as String)
            )
        )
        // Collecting:
        model.states.collect {
            // Checking:
            when (it) {
                // Developing:
                is MainActivityStates.ContractsState -> contractsState(it)
                is MainActivityStates.FailureState -> Log.e(HomeFragment.TAG, "render: ${it.message}", it.exception)
            }
        }
    }

    // Method(ContractsState):
    private fun contractsState(state: MainActivityStates.ContractsState) {
        // Initializing:
        adapter = ReceiptsAdapter(requireContext(), state.contracts)
        // Hiding(ProgressBar):
        progressBar(false)
        // Setting:
        binding.receiptsRecyclerView.adapter = adapter
    }

    // Method(ProgressBar):
    private fun progressBar(show: Boolean = true) {
        // Visibility:
        binding.receiptsProgressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.receiptsRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }

    // Method(OnDestroyView):
    override fun onDestroyView() { // This will destroy our binding after destroying all views.
        // Super:
        super.onDestroyView()
        // Destroying:
        _binding = null
    }
}