package com.harold.eshopbasic.data
import kotlin.collections.HashMap


data class Product(
    val id: String? = "",
    val title: String? = "",
    val description: String? = "",
    val category: String? = "",
    val newPrice:String?="",
    val price: String? = "",
    val seller: String? = "",

    val images: String? ="",
    val colors: String? ="",
    val sizes: String? ="",
    val orders:Int = 0,


    )
{
    constructor(
        id : String? = "",
        title: String? = "",
        description: String? = "",
        category: String? = "",
        price: String? = "",
        seller: String? = "",
        images: String?,
        colors: String?,
        sizes: String?
    ) : this(id,title,description,category,null,price,seller, images, colors, sizes,0)

    constructor():this("","","","","",null,null,"")
}

