package com.example.notes2tab.utils

import android.util.Log
import com.example.notes2tab.dataModels.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

lateinit var AUTH: FirebaseAuth
lateinit var USER: User
lateinit var CURRENT_UID: String

lateinit var DATABASE: FirebaseFirestore
const val COLLECTION_USERS = "users"
const val COLLECTION_SONGS = "songs"
const val COLLECTION_FAVORITES = "favorites"
/*
   users: |
          |...
          |______favorites(collection)
*/

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    DATABASE = FirebaseFirestore.getInstance()
}

fun initUser() {
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    DATABASE.collection(COLLECTION_USERS).document(CURRENT_UID).get()
        .addOnSuccessListener { task ->
            if (task != null) {
                USER = task.toObject(User::class.java) ?: User()
            }
        }
}