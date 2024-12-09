package com.usman.pensionsdemo.ui

import androidx.annotation.StringRes
import com.usman.pensionsdemo.R

enum class AppScreens(val route: String, @StringRes val title: Int) {
    HOME("pensionsHome", title = R.string.home_title),
    DETAIL("pensionDetail", title = R.string.title_details)
}