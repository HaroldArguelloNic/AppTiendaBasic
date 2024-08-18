package com.harold.eshopbasic.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.harold.eshopbasic.R


class AccountOptionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_account_option, container, false)

        val btnLoginAcc = root.findViewById<Button>(R.id.btn_login_account)
        val btnRegisterAcc= root.findViewById<Button>(R.id.btn_register_account)

        btnLoginAcc.setOnClickListener {
            findNavController().navigate(R.id.action_accountOptionFragment_to_loginFragment)
        }

        btnRegisterAcc.setOnClickListener {
            findNavController().navigate(R.id.action_accountOptionFragment_to_registerFragment)
        }

        return root
    }


}