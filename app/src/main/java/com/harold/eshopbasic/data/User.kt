package com.harold.eshopbasic.data

data class User (
    val firstName: String,
    val lastName: String,
    var email: String,
    var password: String,
    var address: String,
    var imagePath: String = "",

    ){
    constructor(): this("","","","","","")
    constructor(firstName: String, lastName: String, email: String, imageUrl: String) : this()

}