package com.example.sastaspotify.data

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class firebaseDataFile(
    val imageurl:String="",
    val name:String="",
    val songurl:String=""
): Serializable


