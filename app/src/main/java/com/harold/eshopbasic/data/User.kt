package com.harold.eshopbasic.data

data class User (
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    var address: String,
    var imagePath: String = "",

    ){
    constructor(): this("","","","","","")

}