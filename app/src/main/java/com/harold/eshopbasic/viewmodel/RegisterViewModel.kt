package com.harold.eshopbasic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.harold.eshopbasic.data.User
import com.harold.eshopbasic.firebase.FirebaseDb
import com.harold.eshopbasic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val firebaseDataBase: FirebaseDb

) : ViewModel() {


    val saveUserInformationGoogleSignIn = MutableLiveData<Resource<String>>()
    val register = MutableLiveData<Resource<User>>()


    val login = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String>()

    val resetPassword = MutableLiveData<Resource<String>>()

    fun createAccountWithEmailAndPassword(user: User, password: String) {
        val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Loading())
        val register: Flow<Resource<FirebaseUser>> = _register

        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener {
                it.user?.let {
                    _register.value = Resource.Success(it)
                }
            }.addOnFailureListener {
                _register.value = Resource.Error(it.message.toString())

            }
    }

    fun registerNewUser(
        user: User,
        password: String
    ) {
        register.postValue(Resource.Loading())
        firebaseDataBase.createNewUser(user.email, password).addOnCompleteListener {
            if (it.isSuccessful)
                firebaseDataBase.saveUserInformation(firebaseAuth.currentUser!!.uid, user)
                    .addOnCompleteListener { it2 ->
                        if (it2.isSuccessful) {
                            register.postValue(Resource.Success(user))
                        } else
                            register.postValue(Resource.Error(it2.exception.toString()))

                    }
            else
                register.postValue(Resource.Error(it.exception.toString()))
        }
    }

    fun loginUser(
        email: String,
        password: String
    ) = firebaseDataBase.loginUser(email, password).addOnCompleteListener {
        if (it.isSuccessful)
            login.postValue(true)
        else
            loginError.postValue(it.exception.toString())
    }

    fun resetPassword(email: String) {
        resetPassword.postValue(Resource.Loading())
        firebaseDataBase.resetPassword(email).addOnCompleteListener {
            if (it.isSuccessful)
                resetPassword.postValue(Resource.Success(email))
            else
                resetPassword.postValue(Resource.Error(it.exception.toString()))

        }
    }

    fun logOut() {
        firebaseDataBase.logout()
    }

    fun isUserSignedIn(): Boolean {
        if (FirebaseAuth.getInstance().currentUser?.uid != null)
            return true
        return false

    }

}