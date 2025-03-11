package net.azarquiel.chatdam.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import net.azarquiel.chatdam.MainActivity
import net.azarquiel.chatdam.model.Post


class MainViewModel(mainActivity: MainActivity): ViewModel()  {
    companion object {
        const val TAG = "ChatDam"
    }
    val db = Firebase.firestore
    val mainActivity by lazy { mainActivity }

    private  var _openDialog = MutableLiveData(false)
    val openDialog: MutableLiveData<Boolean> = _openDialog

    private  var _mensajes = MutableLiveData<List<Post>>()
    val mensajes: MutableLiveData<List<Post>> = _mensajes

    init {
        setListener()
    }

    fun setDialog(value: Boolean) {
        _openDialog.value = value
    }
    fun addPost(post: Post) {
        val postDoc = mutableMapOf(
            "user" to post.user,
            "msg" to post.msg
        )
        db.collection("posts")
            .add(postDoc)
            .addOnSuccessListener(OnSuccessListener { documentReference ->
                Log.d(TAG,"DocumentSnapshot added with ID: " + documentReference.id)
            })
            .addOnFailureListener(OnFailureListener { e ->
                Log.d(TAG,"Error adding document", e)
            })
    }
    private fun setListener() {
        db.collection("posts").addSnapshotListener { snapshot, e ->
            // .orderBy("campoaordenar", Query.Direction.DESCENDING)
            snapshot?.let {
                documentToList(snapshot.documents)
            }

            e?.let {
                Log.d(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
        }
    }


    private fun documentToList(documents: List<DocumentSnapshot>) {
        val mensajes = ArrayList<Post>()
        documents.forEach { d ->
            val user = d["user"] as String
            val msg = d["msg"] as String
            mensajes.add(Post(user, msg))
        }
        _mensajes.value = mensajes
    }

}
