package com.sakura.easyrent.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sakura.easyrent.control.adapters.RepairRequestAdapter
import com.sakura.easyrent.control.managers.SPManager
import com.sakura.easyrent.control.utils.APIUtils
import com.sakura.easyrent.databinding.FragmentRepairRequestBinding
import com.sakura.easyrent.ui.activitys.AddRepairRequestActivity
import com.sakura.easyrent.ui.activitys.main.MainActivityIntentions
import com.sakura.easyrent.ui.activitys.main.MainActivityStates
import com.sakura.easyrent.ui.activitys.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepairRequestFragment : Fragment() {

    // Fields(Variables):
    private var _binding: FragmentRepairRequestBinding? = null

    // Fields(Values):
    private val binding: FragmentRepairRequestBinding get() = _binding!!
    private val model: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }


    // Fields(Adapters):
    private lateinit var adapter: RepairRequestAdapter

    // Fields(Managers):
    @Inject lateinit var manager: SPManager

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "RepairRequest"
    }

    // Method(OnCreateView):
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Initializing:
        _binding = FragmentRepairRequestBinding.inflate(layoutInflater, container, false)
        // Returning:
        return binding.root
    }

    // Method(OnViewCreated):
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // Here we will do our work.
        // Super:
        super.onViewCreated(view, savedInstanceState)
        // Initializing(UI):
        progressBar()
        // Developing:
        binding.floatingActionButton.setOnClickListener {
            // Starting:
            startActivity(Intent(requireContext(), AddRepairRequestActivity::class.java))
        }
        // Rendering:
        lifecycleScope.launchWhenCreated { render() }
    }

    // Method(Render):
    private suspend fun render() {
        // Initializing:
        val token = APIUtils.generateBearerToken(manager.read(SPManager.ACCESS_TOKEN, "") as String)
        // Sending:
        model.channel.send(MainActivityIntentions.GetRepairRequests(token))
        // Collecting:
        model.states.collect {
            // Checking:
            @Suppress("NON_EXHAUSTIVE_WHEN_STATEMENT")
            when (it) {
                // Developing:
                is MainActivityStates.RepairRequests -> onRepairRequests(it)
                is MainActivityStates.FailureState -> Log.e(TAG, "render: ${it.message}", it.exception)
            }
        }
    }

    // Method(OnRepairRequests):
    private fun onRepairRequests(state: MainActivityStates.RepairRequests) {
        // Initializing:
        adapter = RepairRequestAdapter(requireContext(), state.requests)
        // Setting:
        binding.repairRequestRecyclerview.adapter = adapter
        // Showing:
        progressBar(false)
    }

    // Method(ProgressBar):
    private fun progressBar(show: Boolean = true) {
        // Visibility:
        binding.repairRequestProgressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.repairRequestRecyclerview.visibility = if (show) View.GONE else View.VISIBLE
    }

    // Method(OnDestroyView):
    override fun onDestroyView() { // This will destroy our binding after destroying all views.
        // Super:
        super.onDestroyView()
        // Destroying:
        _binding = null
    }

}