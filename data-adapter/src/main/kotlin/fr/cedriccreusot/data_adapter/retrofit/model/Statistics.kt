package fr.cedriccreusot.data_adapter.retrofit.model

import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("id") val id : String,
    @SerializedName("downloads") val downloads : Stat,
    @SerializedName("views") val views : Stat,
    @SerializedName("likes") val likes : Stat
)

data class Stat(
    @SerializedName("total") val total : Int,
    @SerializedName("historical") val historical : Historical
)

data class Historical(
    @SerializedName("change") val change : Int,
    @SerializedName("resolution") val resolution : String,
    @SerializedName("quantity") val quantity : Int,
    @SerializedName("values") val values : List<Values>
)

data class Values(
    @SerializedName("date") val date : String,
    @SerializedName("value") val value : Int
)