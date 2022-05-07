package com.sakura.easyrent.database.dao


import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import com.sakura.easyrent.database.DataBase
import com.sakura.easyrent.database.model.Post

class UsersDao {
    companion object{
        fun addUser(post: Post, onCompleteListener: OnCompleteListener<Void>){
            DataBase.getUsersCollection()
                .document((post.id?:"") as String)
                .set(post)
                .addOnCompleteListener(onCompleteListener)
        }
        fun getUserData(userId:String,onCompleteListener: OnCompleteListener<DocumentSnapshot>){
            DataBase.getUsersCollection()
                .document(userId)
                .get()
                .addOnCompleteListener(onCompleteListener)

        }

    }
}