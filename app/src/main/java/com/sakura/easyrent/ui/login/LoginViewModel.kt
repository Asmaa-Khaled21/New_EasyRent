package com.sakura.easyrent.ui.login

import androidx.databinding.ObservableField
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sakura.easyrent.Data
import com.sakura.easyrent.base.BaseViewModel
import com.sakura.easyrent.database.dao.UsersDao
import com.sakura.easyrent.database.model.User

class LoginViewModel:BaseViewModel<Navigator>() {
    //Login
    val emailLogin = ObservableField<String>()
    val passwordLogin = ObservableField<String>()
    val emailErrorLogin = ObservableField<Boolean>(false)
    val passwordErrorLogin = ObservableField<Boolean>(false)
    val firebaseAuth = Firebase.auth

    // Register
    val userNameRegister = ObservableField<String>()
    val emailRegister = ObservableField<String>()
    val firstNameRegister =ObservableField<String>()
    val lastNameRegister =ObservableField<String>()
    val passwordRegister = ObservableField<String>()
    val nameErrorRegister = ObservableField<Boolean>()
    val userNameErrorRegister = ObservableField<Boolean>()
    val emailErrorRegister = ObservableField<Boolean>()
    val passwordErrorRegister = ObservableField<Boolean>()
    //////////////////////////////////////////////////////
   // Login
    fun login(){
        // 1- validate input
        // 2- show or hide errors if there
        if(!validLogin())return
        showLoading.value=true
        firebaseAuth.signInWithEmailAndPassword(emailLogin.get().toString(),passwordLogin.get().toString())
            .addOnCompleteListener( { task ->
                if(task.isSuccessful){
                    // retrieve User from db
                    // redirect to home
                    firebaseAuth.currentUser?.let { getUserData(it.uid) }
                }else {
                    showLoading.value=false
                    messageLiveData.value = task.exception?.localizedMessage
                }
            })
    }
    fun getUserData(userId:String){
        UsersDao.getUserData(userId, OnCompleteListener {
                task->
            showLoading.value=false
            if(task.isSuccessful){
                val user = task.result?.toObject(User::class.java)// user retrevied
//                Log.e("user",user?.email?:"")
                Data.user=com.sakura.easyrent.database.model.User()
                navigator?.gotoMainActivity()
            }else {
                messageLiveData.value=task.exception?.localizedMessage
            }

//            firebaseAuth.confirmPasswordReset()
        })
    }
    fun validLogin():Boolean{
        var validlogin = true
        if(emailLogin.get().isNullOrBlank()){
            // show email error
            emailErrorLogin.set(true)
            validlogin=false
        }else {
            // hide email error
            emailErrorLogin.set(false)
        }
        if(passwordLogin.get().isNullOrBlank() ||passwordLogin.get()?.length?:0 <6 ){
            // show password error
            passwordErrorLogin.set(true)
            validlogin=false
        }else {
            // hide password error
            passwordErrorLogin.set(false)
        }
        return validlogin
    }

    ////////////////////////////////////////////////////
    //Register

    fun register(){
        if(!validRegiter())return
        // validation is success
        showLoading.value= true
        firebaseAuth.createUserWithEmailAndPassword(emailRegister.get().toString(),passwordRegister.get().toString())
            .addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful){
                        val firebaseUser = firebaseAuth.currentUser;
                        val user =User(firebaseUser?.uid,
                        userNameRegister.get(),emailRegister.get()
                        ,firstNameRegister.get(),lastNameRegister.get())
                        addUserToDB(user)
                }else {
                    showLoading.value=false
                    messageLiveData.value = task.exception?.localizedMessage
                }})
    }

    fun addUserToDB(user: User){
        UsersDao.addUser(user, OnCompleteListener {
            showLoading.value=false
            if(it.isSuccessful){
                // goto homePage
                // messageLiveData.value ="user added in db"
                Data.user=user
                navigator?.gotoMainActivity()
            }else {
                messageLiveData.value = it.exception?.localizedMessage
            }
        })
    }
    fun validRegiter():Boolean{
        var isValidRegiter = true;
        if(userNameRegister.get().isNullOrBlank()){
            nameErrorRegister.set(true)
            isValidRegiter=false
        }else {
            nameErrorRegister.set(false)
        }
        if(emailRegister.get().isNullOrBlank()){
            emailErrorRegister.set(true)
            isValidRegiter=false
        }else {
            emailErrorRegister.set(false)
        }
        if(userNameRegister.get().isNullOrBlank()){
            userNameErrorRegister.set(true)
            isValidRegiter=false
        }else {
            userNameErrorRegister.set(false)
        }
        if(passwordRegister.get().isNullOrBlank()){
            passwordErrorRegister.set(true)
            isValidRegiter=false
        }else {
            passwordErrorRegister.set(false)
        }
        return isValidRegiter
    }
}