package com.hackathon.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hackathon.model.UserModel
import com.hackathon.util.SharedPref
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel:ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()
    private val userRef = db.getReference("CodeFury_Users")
    private val _firebaseUser = MutableStateFlow(auth.currentUser)
    val firebaseUser:StateFlow<FirebaseUser?> = _firebaseUser

    private val _error = MutableStateFlow<String?>(null)
    val error:StateFlow<String?> = _error

    fun login(email: String, password: String, context: Context){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _firebaseUser.value = auth.currentUser
                    getData(auth.currentUser!!.uid,context)
                }else{
                    _error.value = it.exception?.message
                }
            }
    }

    private fun getData(uid:String,context: Context) {
        userRef.child(uid).addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(UserModel::class.java)
                if (userData != null) {
                    SharedPref.saveData(userData.name,userData.email,context)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun register(email:String, password:String,name:String,context:Context){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _firebaseUser.value = auth.currentUser
                    val user = UserModel(email, password, name, auth.currentUser!!.uid)
                    userRef.child(auth.currentUser!!.uid).setValue(user)
                        .addOnSuccessListener {
                            SharedPref.saveData(name,email,context)
                        }
                        .addOnFailureListener {  }
                }else{
                    _error.value = "Something went wrong"
                }
            }
    }

    fun logout(){
        auth.signOut()
        _firebaseUser.value = null
    }

}