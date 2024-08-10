package com.hackathon.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hackathon.model.DisasterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    private val disasterRef = db.getReference("disasters")

    private val _disaster = MutableStateFlow<List<DisasterModel>>(emptyList())
    val disasters: StateFlow<List<DisasterModel>> = _disaster


    init {
        fetchDisasters {
            _disaster.value = it
        }
    }


    private fun fetchDisasters(onResult: (List<DisasterModel>) -> Unit) {

        disasterRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<DisasterModel>()
                for (disasterSnapshot in snapshot.children) {
                    val disaster = disasterSnapshot.getValue(DisasterModel::class.java)

                    disaster?.let {
                        result.add(0, disaster)
                        if (result.size == snapshot.childrenCount.toInt()) {
                            onResult(result)
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}