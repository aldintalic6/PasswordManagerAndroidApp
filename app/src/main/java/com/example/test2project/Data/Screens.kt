package com.example.test2project.Data

import androidx.annotation.StringRes
import com.example.test2project.R

enum class Screens(@StringRes var title: Int) {
    Register(title = R.string.registerScreen),
    Login(title = R.string.loginScreen),
    Home(title = R.string.homeScreen),
    EntryData(title = R.string.entryDataScreen),
    AddEntry(title = R.string.addEntryScreen),
    EditEntry(title = R.string.editEntryScreen),
    GeneratePassword(title = R.string.generatePass)
}