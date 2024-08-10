package com.hackathon.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hackathon.model.DisasterModel
import com.hackathon.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    private val disasterRef = db.getReference("disasters")
    private val usersRef = db.getReference("CodeFury_Users")

    private val _user = MutableStateFlow(UserModel())
    val users: StateFlow<UserModel> = _user

    private val _disaster = MutableStateFlow<List<DisasterModel>>(emptyList())
    val disasters: StateFlow<List<DisasterModel>> = _disaster

    fun fetchUser(uid: String) {
        usersRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(UserModel::class.java)
                if (user != null) {
                    _user.value = user
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun fetchDisasters(uid: String) {
        disasterRef.orderByChild("uid").equalTo(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val disasterList = snapshot.children.mapNotNull {
                        it.getValue(DisasterModel::class.java)
                    }
                    _disaster.value = disasterList
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

    }

}