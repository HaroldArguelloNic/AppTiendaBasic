package com.harold.eshopbasic.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.harold.eshopbasic.adapter.CategoriasAdapter
import com.harold.eshopbasic.databinding.FragmentPrincipalShoppingBinding
import com.harold.eshopbasic.firebase.FirebaseDb


class PrincipalShoppingFragment : Fragment() {
    private var _binding : FragmentPrincipalShoppingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPrincipalShoppingBinding.inflate(inflater,container,false)
        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






//            listOf(
//                Categorias(
//                "Zapatos",
//                0,
//                1,
//                "",
//            ),
//                Categorias(
//                    "Camisas",
//                    0,
//                    0,
//                    "",
//                )
//            )
//        )

    }

    private fun initRecyclerView() {
        val lista= FirebaseDb().getCategorias()
        val rvCategoria = binding.rvCategorias

        rvCategoria.adapter = CategoriasAdapter(
            lista
//                Categorias("Zapatos",0,1,""),
//                Categorias("Camisas",0,2,""),
//                Categorias("pantalones",0,3,""),

        )
        rvCategoria.layoutManager = LinearLayoutManager(context)
    }

}