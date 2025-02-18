package net.azarquiel.parquesnretrofitjpc.api


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Paco Pulido.
 */

object WebAccess {

    val parqueService : ParqueService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apiparques/")
            .build()

        return@lazy retrofit.create(ParqueService::class.java)
    }
}
