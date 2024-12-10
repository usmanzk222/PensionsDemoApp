package com.lbg.pensionsdemo.ui

import androidx.annotation.StringRes
import com.lbg.pensionsdemo.R

enum class AppScreens(val route: String, @StringRes val title: Int) {
    HOME("pensionsHome", title = R.string.home_title),
    DETAIL("pensionDetail", title = R.string.title_details)
}