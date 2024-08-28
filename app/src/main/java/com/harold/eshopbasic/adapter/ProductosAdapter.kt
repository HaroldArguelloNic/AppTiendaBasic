package com.harold.eshopbasic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harold.eshopbasic.R
import com.harold.eshopbasic.data.Product

class ProductosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val nameProducto = view.findViewById<TextView>(R.id.tvProductName)
    val descriptionProducto = view.findViewById<TextView>(R.id.tvProductDescription)
    val precio = view.findViewById<TextView>(R.id.tvValuePrice)
    val imgProducto = view.findViewById<ImageView>(R.id.ivProducto)


}

class ProductosAdapter (private val productos: List<Product>) : RecyclerView.Adapter<ProductosViewHolder>()
    {

        private val rows = mutableListOf<Product>()

        init {
            rows.addAll(0, productos)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ProductosViewHolder(layoutInflater.inflate(R.layout.productos_item,parent,false))
    }

    override fun getItemCount(): Int {

        return rows.size
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        holder.nameProducto.text = rows[position].title
        holder.descriptionProducto.text = rows[position].description
        holder.precio.text = rows[position].price

    }

    fun updateList(productos: List<Product>) {
        rows.clear()
        rows.addAll(productos)
        notifyDataSetChanged()
    }

}
