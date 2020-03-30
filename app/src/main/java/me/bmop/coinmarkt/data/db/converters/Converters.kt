package me.bmop.coinmarkt.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun restoreList(listOfDouble: String?): List<Double?>? {
        return Gson().fromJson(
            listOfDouble,
            object :
                TypeToken<List<Double?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveList(listOfDouble: List<Double?>?): String? {
        return Gson().toJson(listOfDouble)
    }

}