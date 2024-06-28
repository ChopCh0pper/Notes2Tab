package com.example.notes2tab.dataModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    //хранение и изменение данных пользователя
    private val usersData = MutableLiveData<User>()

    // геттер для данных пользователю (liveData имеет неизменяемый доступ к данным, чтобы другие
    // компоненты могли наблюдать за изменениями, но не могли изменять данные напрямую)
    val user: LiveData<User> get() = usersData

    // Метод для обновления данных пользователя
    fun updateUser(user: User) {
        usersData.value = user
    }
}