package com.harold.eshopbasic.fragments.loginregister

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.harold.eshopbasic.R
import com.harold.eshopbasic.data.User
import com.harold.eshopbasic.databinding.FragmentRegisterBinding
import com.harold.eshopbasic.utils.Resource
import com.harold.eshopbasic.viewmodel.RegisterViewModel
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    lateinit var viewModel: RegisterViewModel
    lateinit var btnRegister: CircularProgressIndicator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister = view.findViewById(R.id.btnRegister)

        onRegisterBtnClick()
        observeSaveUserInformation()
        onLoginClick()
//
//        binding.apply {
//            btnRegister.setOnClickListener {
//                val user = User(
//                    etName.text.toString().trim(),
//                    etLastName.text.toString().trim(),
//                    etEmail.text.toString().trim(),
//                    etPhone.text.toString().trim(),
//                    etAddress.text.toString().trim(),
//
//                )
//                val password = etPassword.text.toString()
//                viewModel.createAccountWithEmailAndPassword(user, password)
//
//            }
//        }
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    // Process item
//                }
//            }
    }


    private fun onRegisterBtnClick() {
        btnRegister.setOnClickListener {

            val user = getUser()
            val password = getPassword()
            user?.let { user ->
                password?.let { password ->
                    viewModel.registerNewUser(user, password)
                    btnRegister.animation.start()

                }
            }
        }
    }

    private fun getUser(): User? {
        val firstName = binding.etName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        if (firstName.isEmpty()) {
            binding.etName.apply {
                error = resources.getString(R.string.first_name_cant_be_empty)
                requestFocus()
            }
            return null
        }

        if (lastName.isEmpty()) {
            binding.etLastName.apply {
                error = resources.getString(R.string.last_name_cant_be_empty)
                requestFocus()
            }
            return null
        }

        if (email.isEmpty()) {
            binding.etEmail.apply {
                error = resources.getString(R.string.email_cant_be_empty)
                requestFocus()
            }
            return null
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.apply {
                error = resources.getString(R.string.valid_email)
                requestFocus()
            }
            return null
        }


        return User(firstName, lastName, email)
    }

    private fun getPassword(): String? {
        val password = binding.etPassword.text.toString().trim()
        if (password.isEmpty()) {
            binding.etPassword.apply {
                error = resources.getString(R.string.password_cant_be_empty)
                requestFocus()
            }
            return null
        }

        if (password.length < 6) {
            binding.etPassword.apply {
                error = resources.getString(R.string.password_at_least_six)
                requestFocus()
            }
            return null
        }
        return password
    }

    private fun onLoginClick() {
        binding.tvDontHaveAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun observeSaveUserInformation() {
        viewModel.register.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "EmailRegister:Loading")
                    btnRegister.animation.start()
                    return@Observer
                }

                is Resource.Success -> {
                    Log.d(TAG, "EmailRegister:Successful")
                    btnRegister.animation.cancel()
                    Toast.makeText(
                        activity,
                        resources.getText(R.string.signed_up_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.logOut()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    viewModel.register.postValue(null)
                }

                is Resource.Error -> {
                    Log.e(TAG, "EmailRegister:Error ${response.message.toString()}")
                    Toast.makeText(
                        activity,
                        resources.getText(R.string.error_occurred),
                        Toast.LENGTH_LONG
                    ).show()
                    return@Observer
                }
            }
        })
    }

}



