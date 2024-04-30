package com.example.notes2tab.utils

import com.google.firebase.auth.FirebaseAuth

lateinit var AUTH: FirebaseAuth


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
}