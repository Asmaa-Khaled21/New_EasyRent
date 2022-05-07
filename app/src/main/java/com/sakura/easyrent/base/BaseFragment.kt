package com.sakura.easyrent.base
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sakura.easyrent.fragments.homefragment.HomeViewModel
import com.sakura.easyrent.ui.login.LoginViewModel

abstract class BaseFragment<DB: ViewDataBinding,VM:BaseViewModel<*>>:Fragment() {

    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(requireActivity(),getLayoutId())
        viewModel= initializeViewModel()
        viewModel.messageLiveData.observe(this, Observer {message->
            showDialoge(message = message,
                posActionName = "ok",
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        })

    }
    abstract fun getLayoutId(): Int
    abstract fun initializeViewModel(): VM


    fun makeToast(message:String){
        Toast.makeText(context,"Hello user", Toast.LENGTH_LONG).show()
    }
    fun makeToast(messageId:Int){
        Toast.makeText(context,messageId, Toast.LENGTH_LONG).show()
    }

    fun showDialoge(title:String?=null,
                    message:String,
                    posActionName:String?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionName:String?=null,
                    negAction: DialogInterface.OnClickListener?=null){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.show()
    }
    fun showDialoge(titleId:Int?=null,
                    messageId:Int,
                    posActionName:Int?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionName:Int?=null,
                    negAction: DialogInterface.OnClickListener?=null,
                    isCancelable:Boolean=true){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(messageId)
        builder.setCancelable(isCancelable)
        if(titleId!=null)
            builder.setTitle(titleId)
        if (posActionName!=null)
            builder.setPositiveButton(posActionName,posAction)
        if (negActionName!=null)
            builder.setNegativeButton(negActionName,negAction)

        builder.show()
    }

}