package com.harold.eshopbasic.data

data class Categorias(
    val name:String,
    val productos: Int,
    val ranks: Int,
    val image: String
) {
  constructor() : this("",0,0,"")
}