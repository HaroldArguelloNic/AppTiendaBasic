package com.harold.eshopbasic.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harold.eshopbasic.EshopApplication
import com.harold.eshopbasic.firebase.FirebaseDb


class ViewModelProviderFactory(
    private val firebaseDb: FirebaseDb
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EshopApplication(firebaseDb) as T
    }
}