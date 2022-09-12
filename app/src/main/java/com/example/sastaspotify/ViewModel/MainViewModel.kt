package com.example.sastaspotify.ViewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sastaspotify.data.firebaseDataFile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel()  {
//    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)
    val temList= mutableListOf<firebaseDataFile>()
    init {
        FetchDataFromDatabase()
    }
    private fun FetchDataFromDatabase(){

//        response.value=DataState.Loading
        FirebaseDatabase.getInstance().getReference().child("Alka Yagnik")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    for(DataSnap in snapshot.children) {
                        val Song = DataSnap.getValue(firebaseDataFile::class.java)
//                        if (Song != null) {
                        if (Song != null) {
                            temList.add(Song)
                        }
//                        }
                    }
                }


                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "loadPost:onCancelled")
                }
            }
            )
    }
//    private val firebaseStorage=FirebaseStorage.getInstance()
//    private  val SingerSongCollection=firebaseStorage.getReference("Alka Yagnik")
//    suspend fun getAllString():List<firebaseDataFile>{
//        return try {
//            SingerSongCollection.
//        }
//        catch (e:Exception){
//            emptyList()
//        }
//    }
}