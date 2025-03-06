package net.azarquiel.avesretrofit.api


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Paco Pulido.
 */

object GafaAccess {

    val gafaService : GafaService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apigafas/")
            .build()

        return@lazy retrofit.create(GafaService::class.java)
    }
}


