package com.harold.eshopbasic.adapter

import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.harold.eshopbasic.R
import com.harold.eshopbasic.data.Categorias


class CategoriasAdapter(private val categorias: List<Categorias>) :
    RecyclerView.Adapter<CategoriasViewHolder>() {
    private val rows = mutableListOf<Categorias>()

    init {
        rows.addAll(0, categorias)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return CategoriasViewHolder(layoutInflater.inflate(R.layout.categorias_item, parent, false))
    }

    override fun getItemCount(): Int {

        return rows.size
    }

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {
        holder.nameCategoria.text = rows[position].name

    }

    fun updateList(categorias: List<Categorias>) {
        rows.clear()
        rows.addAll(categorias)
        notifyDataSetChanged()
    }
}