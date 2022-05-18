@file:Suppress("UNUSED_PARAMETER")

package com.sakura.easyrent.ui.activitys

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sakura.easyrent.R
import com.sakura.easyrent.control.managers.SPManager
import com.sakura.easyrent.control.utils.APIUtils
import com.sakura.easyrent.databinding.ActivityAddRepairRequestBinding
import com.sakura.easyrent.model.RepairRequest
import com.sakura.easyrent.ui.activitys.main.MainActivityIntentions
import com.sakura.easyrent.ui.activitys.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddRepairRequestActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    // Fields:
    private lateinit var date: String
    @Inject lateinit var manager: SPManager

    // Fields:
    private val binding: ActivityAddRepairRequestBinding by lazy { ActivityAddRepairRequestBinding.inflate(layoutInflater) }
    private val model: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "AddRRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Super:
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // Setting content to the root of binding.
        // Initializing:
        val units = MainViewModel.staticUnits
        val status = arrayListOf(
            // Listing:
            RepairRequest.StatusTypes.PENDING.name.lowercase(),
            RepairRequest.StatusTypes.CLOSED.name.lowercase(),
            RepairRequest.StatusTypes.CANCELLED.name.lowercase()
        )
        // Checking:
        if (units.isEmpty()) {
            // Toasting:
            Toast.makeText(this, getString(R.string.add_repair_request_warn), Toast.LENGTH_SHORT).show()
            // Returning:
            return
        }
        // Setting:
        binding.spinnerStatus.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, status)
        binding.spinnerUnits.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        // Developing:
        binding.calendarImageView.setOnClickListener(::onDate)
        binding.addButton.setOnClickListener(::onAdd)
    }

    // Method(OnAdd)
    private fun onAdd(view: View?) {
        // Initializing:
        val token = APIUtils.generateBearerToken(manager.read(SPManager.ACCESS_TOKEN, "") as String)
        val details = binding.Details.text.toString()
        val notes = binding.Notes.text.toString()
        val status = binding.spinnerStatus.selectedItem.toString()
        val units = binding.spinnerUnits.selectedItem.toString().toLong()
        val request = RepairRequest(details, date, units, notes, status)
        // Adding:
        lifecycleScope.launchWhenCreated { model.channel.send(MainActivityIntentions.AddRepairRequests(token, request)) }
    }

    // Method(OnDate):
    private fun onDate(view: View?) {
        // Initializing:
        val calendar = Calendar.getInstance()
        // Showing:
        DatePickerDialog(
            this,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // Method(OnDateSet):
    override fun onDateSet(picker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Initializing:
        date = "$year-$month-$dayOfMonth"
        // Setting:
        binding.EditDate.text = date
    }
}