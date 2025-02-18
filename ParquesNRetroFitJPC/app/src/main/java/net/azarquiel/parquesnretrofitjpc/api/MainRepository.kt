package net.azarquiel.parquesnretrofitjpc.api

import net.azarquiel.parquesnretrofitjpc.model.Comunidad
import net.azarquiel.parquesnretrofitjpc.model.Imagen
import net.azarquiel.parquesnretrofitjpc.model.Parques


class MainRepository() {
    val service = WebAccess.parqueService


    //FUNCIONES GET
    suspend fun getComunidades(): List<Comunidad> {
        val webResponse = service.getComunidades().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comunidades
        }
        return emptyList()
    }
    suspend fun getParques(): List<Parques> {
        val webResponse = service.getParques().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.parques
        }
        return emptyList()
    }
    suspend fun getParquesByComunidad(idcomunidad: Int): List<Parques> {
        val webResponse = service.getParquesByComunidad(idcomunidad).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.parques
        }
        return emptyList()
    }

    suspend fun getImagenesByParque(idparque: Int): List<Imagen> {
        val webResponse = service.getImagenesByParque(idparque).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.imagenes
        }
        return emptyList()
    }

    //FUNCIONES POST
    suspend fun darLike(idparque: Int): String? {
        val webResponse = service.darLike(idparque).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.msg
        }
        return null
    }




}
