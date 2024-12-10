package com.lbg.pensionsdemo.data.local.database

import androidx.room.TypeConverter
import com.lbg.pensionsdemo.domain.model.House

class HouseConverter {
    @TypeConverter
    fun fromHouse(house: House?): String {
        return house?.name ?: House.UNKNOWN.name
    }

    @TypeConverter
    fun toHouse(houseName: String): House {
        return try {
                House.valueOf(houseName.uppercase())
            } catch (e: IllegalArgumentException) {
                House.UNKNOWN
            }

    }
}