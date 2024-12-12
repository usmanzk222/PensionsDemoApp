package com.lbg.pensionsdemo.ui

import androidx.annotation.StringRes
import com.lbg.pensionsdemo.R

enum class AppScreens(val route: String, @StringRes val title: Int) {
    LANDING("landingPageRoute", title = R.string.title_home),
    GREETINGS("greetingsRoute", title = R.string.title_greetings),
    BINGO("bingoRoute", title = R.string.title_greetings),

    GREETINGS_HOME("greetingsHome", title = R.string.title_greetings),
    BINGO_HOME("bingoHome", title = R.string.title_details)
}