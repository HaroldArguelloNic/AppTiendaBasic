package com.harold.eshopbasic.data

data class Categorias(
    val name:String,
    val productos: String,
    val ranks: String,
    val image: String
) {
    constructor(): this(
        "",
        "0",
        "0",
        ""
    )
}

