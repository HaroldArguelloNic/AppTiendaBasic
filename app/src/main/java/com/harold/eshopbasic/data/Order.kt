package com.harold.eshopbasic.data

import java.util.Date

data class Order(
    val id: String,
    val date:Date,
    val totalPrice:String,
    val state:String
) {

    constructor():this("", Date(),"","")
}