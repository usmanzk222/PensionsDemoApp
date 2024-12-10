package com.lbg.pensionsdemo.data.remote.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.lbg.pensionsdemo.domain.model.House

class HouseTypeAdapter : TypeAdapter<House>() {
    override fun write(out: JsonWriter, value: House?) {
        if (value == null || value == House.UNKNOWN) {
            out.nullValue()
        } else {
            out.value(value.name)
        }
    }

    override fun read(input: JsonReader): House {
        val stringValue = input.nextString()
        return try {
            House.valueOf(stringValue.uppercase())
        } catch (e: IllegalArgumentException) {
            House.UNKNOWN
        }
    }
}