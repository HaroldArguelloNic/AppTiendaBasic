package com.harold.eshopbasic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harold.eshopbasic.data.Categorias
import com.harold.eshopbasic.databinding.CategoriasItemBinding

class CategoriasAdapter(val categorias: List<Categorias>) :
    RecyclerView.Adapter<CategoriasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        val view = CategoriasItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoriasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {
        holder.bind(categorias[position])
    }


}