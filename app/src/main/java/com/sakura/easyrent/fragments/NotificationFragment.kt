package com.sakura.easyrent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.R
import com.sakura.easyrent.recycleView.NotificationAdapter

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)

    }

    lateinit var recyclerView: RecyclerView
    var notificationAdapter =NotificationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView()

    }
   private fun initRecycleView(){
        recyclerView = requireView().findViewById(R.id.RecycleView_Notification)
        recyclerView.adapter=notificationAdapter }



    companion object {
        @JvmStatic
        fun newInstance() =
            NotificationFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}