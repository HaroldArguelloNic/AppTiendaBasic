package com.harold.eshopbasic.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harold.eshopbasic.R
import com.harold.eshopbasic.databinding.FragmentLoginBinding
import com.harold.eshopbasic.firebase.FirebaseDb
import kotlin.concurrent.timerTask


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = binding.btnLoginLogin

        btnLogin.setOnClickListener {
            var txtEmail = binding.textInputEmail.getText().toString().trim()
            var txtPassword = binding.textInputPassword.getText().toString().trim()

            if(txtEmail.isEmpty()){
                binding.etName.apply {
                    error="El campo nombre no puede estar vacio"
                    requestFocus()
                }
            }
            if(txtPassword.isEmpty()){
                binding.editText2.apply {
                    error="El campo nombre no puede estar vacio"
                    requestFocus()
                }
            }

            FirebaseDb().loginUser(txtEmail,txtPassword)
                .addOnCompleteListener() { task ->
                    if(task.isSuccessful){
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }else {
                        Toast.makeText(context, "Error en correo o password", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

}