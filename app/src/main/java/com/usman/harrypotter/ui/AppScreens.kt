package com.usman.harrypotter.ui

import androidx.annotation.StringRes
import com.usman.harrypotter.R

enum class AppScreens(val route: String, @StringRes val title: Int) {
    HOME("characterList", title = R.string.home_title),
    DETAIL("characterDetails", title = R.string.title_details)
}