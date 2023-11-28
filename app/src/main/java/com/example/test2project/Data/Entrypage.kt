package com.example.test2project.Data

import androidx.annotation.DrawableRes

data class Entrypage(
    val name: String,
    val email: String,
    val password: String,
    @DrawableRes val photo: Int,

)
