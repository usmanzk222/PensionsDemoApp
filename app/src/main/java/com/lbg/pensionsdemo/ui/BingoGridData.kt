package com.lbg.pensionsdemo.ui

import com.lbg.pensionsdemo.R

data class GridItem(
    val iconId: Int,
    val displayText: Int,
    val isCompleted: Boolean
)

val gridItems = listOf<GridItem>(
    GridItem(
        R.drawable.eyeing_emoji,
        R.string.why_consolidating,
        true
    ),
    GridItem(
        R.drawable.plus,
        R.string.connect_other_accounts,
        false
    ),
    GridItem(
        R.drawable.coin_stack,
        R.string.connect_other_pensions,
        false
    ),
    GridItem(
        R.drawable.person_silhouette,
        R.string.keep_profile_updated,
        false
    ),
    GridItem(
        R.drawable.arrow_head,
        R.string.what_is_WIR,
        true
    ),
    GridItem(
        R.drawable.shield,
        R.string.life_insurance_for_me,
        false
    ),
    GridItem(
        R.drawable.intersected_circles,
        R.string.save_or_invest,
        false
    ),
    GridItem(
        R.drawable.claw,
        R.string.pet_insurance_due,
        false
    ),
    GridItem(
        R.drawable.stacked_circles,
        R.string.compound_interest,
        false
    ),
)