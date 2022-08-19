package com.sakura.easyrent.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.sakura.easyrent.R
import com.sakura.easyrent.control.managers.SPManager
import com.sakura.easyrent.databinding.FragmentSettingsBinding
import com.sakura.easyrent.model.User
import com.sakura.easyrent.ui.activitys.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.system.exitProcess

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    // Fields:
    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!
    @Suppress("unused") private val model: MainViewModel by viewModels()

    // Fields(Managers):
    @Inject lateinit var manager: SPManager

    // Fields(Strings):
    private lateinit var userJsonMetaData: String

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "SettingsFragment"
    }

    // Method(OnCreateView):
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Initializing:
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        // Returning:
        return binding.root
    }

    // Method(OnViewCreated):
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // Here we will do our work.
        // Super:
        super.onViewCreated(view, savedInstanceState)
        // Initializing(Application):
        initializeUIWithUserMetaData()
        initializeProgrammableViews()
    }

    // Method(InitializeUIWithUserMetaData):
    private fun initializeUIWithUserMetaData() {
        // Initializing:
        userJsonMetaData = (manager.read(SPManager.USER_META_DATA, "") as String)
        // Checking:
        if (userJsonMetaData.isNotBlank()) {
            // Initializing:
            val user = Gson().fromJson(userJsonMetaData, User::class.java)
            // Applying:
            binding.apply {
                // Setting:

                editNameOnCard.text = user.email
            }
        }
    }

    // Method(InitializeProgrammableViews):
    private fun initializeProgrammableViews() {
        // Developing:
        binding.LogOut.setOnClickListener {
            // Checking:
            if (userJsonMetaData.isNotBlank()) {
                // LoggingOut:
                manager.write(SPManager.USER_META_DATA, "")
                manager.write(SPManager.ACCESS_TOKEN, "")
                manager.write(SPManager.REFRESH_TOKEN, "")
                // Toasting:
                Toast.makeText(requireContext(), getString(R.string.logged_out_successfully_msg), Toast.LENGTH_SHORT).show()
                // Closing:
                requireActivity().finish()
                exitProcess(0)
            }
        }
    }

    // Method(OnDestroyView):
    override fun onDestroyView() { // This will destroy our binding after destroying all views.
        // Super:
        super.onDestroyView()
        // Destroying:
        _binding = null
    }
}