package com.example.test2project.Data

import androidx.annotation.DrawableRes

data class Entry(
    val id : Int,
    val title: String,
    val category: String,
    @DrawableRes val photo: Int,
)
