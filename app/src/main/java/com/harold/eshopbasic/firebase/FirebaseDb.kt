package com.harold.eshopbasic.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.harold.eshopbasic.data.User


class FirebaseDb {
    private val usersCollectionRef = Firebase.firestore.collection("usuarios")
    private val productsCollection = Firebase.firestore.collection("productos")
    private val categoriesCollection = Firebase.firestore.collection("categorias")
    private val firebaseStorage = Firebase.storage.reference

    val userUid = FirebaseAuth.getInstance().currentUser?.uid

    private val userCartCollection = userUid?.let {
        Firebase.firestore.collection("usuarios").document(it).collection("cart")
    }
    private val userAddressesCollection = userUid?.let {
        Firebase.firestore.collection("usuarios").document(it).collection("direccion")

    }


    private val firebaseAuth = Firebase.auth

    fun createNewUser(
        email: String, password: String
    ) = firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun saveUserInformation(
        userUid: String,
        user: User
    ) = usersCollectionRef.document(userUid).set(user)

    fun loginUser(
        email: String,
        password: String
    ) = firebaseAuth.signInWithEmailAndPassword(email, password)

    fun getUser() = FirebaseAuth.getInstance().currentUser?.let {
        usersCollectionRef
        .document(it.uid)
    }


    fun uploadUserProfileImage(image: ByteArray, imageName: String): UploadTask {
        val imageRef = firebaseStorage.child("profileImages")
            .child(firebaseAuth.currentUser!!.uid)
            .child(imageName)

        return imageRef.putBytes(image)

    }

    fun getImageUrl(
        firstName: String,
        lastName: String,
        email: String,
        imageName: String,
        onResult: (User?, String?) -> Unit,
    ) {
        if (imageName.isNotEmpty())
            firebaseStorage.child("profileImages")
                .child(firebaseAuth.currentUser!!.uid)
                .child(imageName).downloadUrl.addOnCompleteListener {
                    if (it.isSuccessful) {
                        val imageUrl = it.result.toString()
                        val user = User(firstName, lastName, email, imageUrl)
                        onResult(user, null)
                    } else
                        onResult(null, it.exception.toString())

                } else {
            val user = User(firstName, lastName, email, "","")
            onResult(user, null)
        }
    }

    fun updateUserInformation(user: User) =
        Firebase.firestore.runTransaction { transaction ->
            val userPath = usersCollectionRef.document(Firebase.auth.currentUser!!.uid)
            if (user.imagePath.isNotEmpty()) {
                transaction.set(userPath, user)
            } else {
                val imagePath = transaction.get(userPath)["imagePath"] as String
                user.imagePath = imagePath
                transaction.set(userPath, user)
            }

        }


}
