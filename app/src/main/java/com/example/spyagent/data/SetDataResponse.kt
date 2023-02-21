package com.example.spyagent.data

import com.google.gson.annotations.SerializedName

data class SetDataResponse (
    @SerializedName("setId")
    val setId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("listWords")
    val listWords: List<Words>
    )


data class Words(
    @SerializedName("word1") val word1: String,
    @SerializedName("word2") val word2: String,
    @SerializedName("word3") val word3: String,
    @SerializedName("word4") val word4: String,
    @SerializedName("word5") val word5: String,
    @SerializedName("word6") val word6: String,
    @SerializedName("word7") val word7: String,
    @SerializedName("word8") val word8: String,
    @SerializedName("word9") val word9: String,
    @SerializedName("word10") val word10: String
)


