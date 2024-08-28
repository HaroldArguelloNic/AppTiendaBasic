package com.harold.eshopbasic.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harold.eshopbasic.R

class CategoriasViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val nameCategoria = view.findViewById<TextView>(R.id.tvCategoriaItem)

    }
