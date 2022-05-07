package com.sakura.easyrent.fragments.homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.R
import com.sakura.easyrent.databinding.FragmentHomeBinding
import com.sakura.easyrent.recycleView.HomeAdapter
import com.sakura.easyrent.recycleView.HomeItem
import com.sakura.easyrent.ui.pay.PayActivity


class HomeFragment : Fragment(),HomeNavigator{


    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val viewModel=ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel=viewModel
        viewModel.navigator=this
        return binding.root
    }

    lateinit var recycleView: RecyclerView
    lateinit var homeAdapter: HomeAdapter
    lateinit var itemList:MutableList<HomeItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }
        fun initRecycleView(){

            recycleView = requireView().findViewById(R.id.RecycleView_Ajar)
            //createItemSetttingslist()
            loadDate()
            homeAdapter=HomeAdapter(itemList)
            homeAdapter.onItemClickListener =object :HomeAdapter.OnItemClickListener{
                override fun onItemClick(pos: Int, item: HomeItem) {

                    activity?.let{
                        val intent = Intent (it, PayActivity::class.java)
                        it.startActivity(intent)
                    }
                }
            }
            recycleView.adapter=homeAdapter
        }


    fun loadDate(){
        itemList= mutableListOf();  //create empty list
        for(i in 1..20)
        { itemList.add(
            HomeItem(
                houseNumberImage = R.id.image_House_NUMBER, houseNumber = R.id.House_NUMBER.toString(),
                dayImage = R.id.image_Day, day = R.id.Day.toString(),
                gbsImage = R.id.image_GBS, gbs = R.id.GBS.toString(),
                clockImage = R.id.image_clock, clock = R.id.clock.toString(),
                money = R.id.money.toString(), buttonBay = R.id.Button_Bay
                ))
        }}

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun gotopayActivity() {

        val intent = Intent(activity, PayActivity::class.java)
        startActivity(intent)
    }

}

