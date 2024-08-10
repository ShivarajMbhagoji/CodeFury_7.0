package com.hackathon.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.storage
import com.hackathon.model.DisasterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ReportState(
    val type: String = "Select type", val status: String = "Status"
)

class AddDisasterViewModel : ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    private val disasterRef = db.getReference("disasters")

    private val storageRef = Firebase.storage.reference

    private val _isPosted = MutableStateFlow(false)
    val isPosted: StateFlow<Boolean> = _isPosted

    private val _state = MutableStateFlow(ReportState())
    val uiState: StateFlow<ReportState> = _state.asStateFlow()


    fun setType(type: String) {
        _state.update { cs ->
            cs.copy(
                type = type
            )

        }
    }

    fun setStatus(status: String) {
        _state.update { cs ->
            cs.copy(
                status = status
            )

        }
    }


    fun saveData(disasterModel: DisasterModel, imageUris: MutableList<Uri?>? = null) {
        val uriList: MutableList<String> = mutableListOf()

        if (imageUris.isNullOrEmpty()) {
            // No images to upload, save data immediately
            disasterRef.child(disasterModel.disasterId).setValue(disasterModel)
                .addOnSuccessListener {
                    _isPosted.value = true
                }.addOnFailureListener {
                    _isPosted.value = false
                }
            return
        }

        var completedUploads = 0

        for (uri in imageUris) {
            uri?.let {
                val imageRef = storageRef.child("disaster/${System.currentTimeMillis()}_${uri.lastPathSegment}}.jpg")
                val uploadTask = imageRef.putFile(it)
                uploadTask.addOnSuccessListener {
                    imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        uriList.add(downloadUri.toString())
                        completedUploads++
                        if (completedUploads == imageUris.size) {
                            // All uploads completed, now save data
                            disasterRef.child(disasterModel.disasterId).setValue(disasterModel.copy(imgList = uriList))
                                .addOnSuccessListener {
                                    _isPosted.value = true
                                }.addOnFailureListener {
                                    _isPosted.value = false
                                }
                        }
                    }
                }.addOnFailureListener {
                    // Handle failure of individual upload here if needed
                    _isPosted.value = false
                }
            }
        }
    }
}