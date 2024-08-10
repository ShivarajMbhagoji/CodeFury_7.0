package com.hackathon.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hackathon.model.DisasterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DisasterDetailsViewModel : ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    private val disasterRef = db.getReference("disasters")

    private val _disaster = MutableStateFlow(DisasterModel())
    val disasters: StateFlow<DisasterModel> = _disaster

    fun fetchDisaster(uid:String) {
        disasterRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val disaster = snapshot.getValue(DisasterModel::class.java)
                if (disaster != null) {
                    _disaster.value = disaster
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}