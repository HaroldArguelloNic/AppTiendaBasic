package com.harold.eshopbasic.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.harold.eshopbasic.adapter.CategoriasAdapter
import com.harold.eshopbasic.adapter.ProductosAdapter
import com.harold.eshopbasic.databinding.FragmentPrincipalShoppingBinding
import com.harold.eshopbasic.firebase.FirebaseDb
import com.harold.eshopbasic.provider.CategoriasProvider


class PrincipalShoppingFragment : Fragment() {
    private var _binding: FragmentPrincipalShoppingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPrincipalShoppingBinding.inflate(inflater, container, false)
        initRecyclerView()
        initRecyclerVProductos()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initRecyclerView() {
        val rvCategoria = binding.rvCategorias
        rvCategoria.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapterCategoria = CategoriasAdapter(emptyList())
        rvCategoria.adapter = adapterCategoria
        FirebaseDb().getCategorias(adapterCategoria)

    }

     fun initRecyclerVProductos() {
        val rvProductos = binding.rvProductos
        rvProductos.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapterProducto = ProductosAdapter(emptyList())
        rvProductos.adapter = adapterProducto
        FirebaseDb().getProduct(adapterProducto)
    }

}