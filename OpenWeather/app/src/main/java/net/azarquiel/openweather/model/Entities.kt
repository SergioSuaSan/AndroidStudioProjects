package net.azarquiel.openweather.model

import com.google.gson.annotations.SerializedName


data class Main_weather (var temp_min: Float, var temp_max: Float)
data class Weather (var icon: String, var description: String)
data class Dia (var dt_txt: String, @SerializedName("main") var temperatura: Main_weather, @SerializedName("weather") var pronostico: List<Weather>)
data class Resultado (@SerializedName("list") var dias: List<Dia>)

