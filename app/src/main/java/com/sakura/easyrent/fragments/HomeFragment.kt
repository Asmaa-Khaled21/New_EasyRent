package com.sakura.easyrent.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.ui.pay.PayActivity
import com.sakura.easyrent.R
import com.sakura.easyrent.recycleView.SettingsAdapter
import com.sakura.easyrent.recycleView.SettingsItem


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    lateinit var recycleView: RecyclerView
    lateinit var settingsAdapter: SettingsAdapter
    lateinit var itemList:MutableList<SettingsItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }
        fun initRecycleView(){

            recycleView = requireView().findViewById(R.id.RecycleView_Ajar)
            //createItemSetttingslist()
            loadDate()
            settingsAdapter=SettingsAdapter(itemList)
            settingsAdapter.onItemClickListener =object :SettingsAdapter.OnItemClickListener{
                override fun onItemClick(pos: Int, item: SettingsItem) {

                    activity?.let{
                        val intent = Intent (it, PayActivity::class.java)
                        it.startActivity(intent)
                    }
                }
            }
            recycleView.adapter=settingsAdapter
        }


    fun loadDate(){
        itemList= mutableListOf();  //create empty list
        for(i in 1..20)
        { itemList.add(
            SettingsItem(

                oneImage = R.id.image_House_NUMBER, oneText = R.id.House_NUMBER.toString(),
                twoImage = R.id.image_Day, twoText = R.id.Day.toString(),
                threeImage = R.id.image_GBS, threeText = R.id.GBS.toString(),
                fourImage = R.id.image_clock, fourText = R.id.clock.toString(),
                fiveText = R.id.money.toString(), sixItem = R.id.Button_Bay
                ))
        }}

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }}

