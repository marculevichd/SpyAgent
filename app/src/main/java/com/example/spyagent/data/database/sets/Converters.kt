package com.example.spyagent.data.database.sets

import androidx.room.TypeConverter
import java.util.regex.Pattern


class Converters {

    @TypeConverter
    fun converterArrayListToString(listWords: ArrayList<String>): String {
        return listWords.joinToString(separator = "№")

    }

    @TypeConverter
    fun converterStringToArrayList(string: String): ArrayList<String> {
        val listWords: ArrayList<String> = arrayListOf<String>()
        val temple = Pattern.compile("№").split(string)

        for (i in temple) {
            listWords.add(i)
        }
        return listWords
    }

}