package com.sakura.easyrent.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sakura.easyrent.databinding.FragmentReceiptsBinding
import com.sakura.easyrent.ui.activitys.main.MainViewModel

class ReceiptsFragment : Fragment() {

    // Fields:
    private var _binding: FragmentReceiptsBinding? = null
    private val binding: FragmentReceiptsBinding get() = _binding!!
    private val model: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

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
        // Initializing:
    }

    // Method(OnDestroyView):
    override fun onDestroyView() { // This will destroy our binding after destroying all views.
        // Super:
        super.onDestroyView()
        // Destroying:
        _binding = null
    }
}