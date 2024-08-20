package com.harold.eshopbasic.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.harold.eshopbasic.R
import com.harold.eshopbasic.data.User
import com.harold.eshopbasic.databinding.FragmentRegisterBinding
import com.harold.eshopbasic.firebase.FirebaseDb


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnRegister = binding.btnRegisterRegister

        btnRegister.setOnClickListener {

            registerUser()
        }

    }

    private fun registerUser() {
        val txtName = binding.etName.getText().toString().trim()
        val txtLastName = binding.etLastName.getText().toString().trim()
        val txtEmail = binding.etEmail.getText().toString().trim()
        val txtPassword = binding.etPassword.getText().toString().trim()

        if (txtName.isEmpty()) {
            binding.etName.apply {
                error = resources.getString(R.string.name_cant_be_empty)
                requestFocus()
            }


        }
        if (txtLastName.isEmpty()) {
            binding.etLastName.apply {
                error = resources.getString(R.string.last_name_cant_be_empty)
                requestFocus()
            }

        }
        if (txtEmail.isEmpty()) {
            binding.etEmail.apply {
                error = resources.getString(R.string.email_cant_be_empty)
                requestFocus()
            }

        }
        if (txtPassword.isEmpty() || txtPassword.length < 6) {
            binding.etPassword.apply {
                error = resources.getString(R.string.password_cant_be_empty)
                requestFocus()
            }
        }
        val user= User(txtName,txtLastName,txtEmail,txtPassword,"","")
        FirebaseDb().createNewUser(txtEmail,txtPassword)
            .addOnCompleteListener() {task ->
                if (task.isSuccessful) {
//                     Sign in success, update UI with the signed-in user's information

                    val userUid=FirebaseDb().getUser()

                    FirebaseDb().saveUserInformation(userUid.toString(),user)

                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, "Fallo en el registro", Toast.LENGTH_SHORT).show()
                }
            }




//        auth.createUserWithEmailAndPassword(txtEmail, txtPassword)
//            .addOnCompleteListener() { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        context,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//
//                }
//
//            }
       }
    }

