package net.azarquiel.chistesapp.api

import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.model.Punto
import net.azarquiel.chistesapp.model.Usuario

class MainRepository() {
    val service = WebAccess.chisteService

    suspend fun getCategorias(): List<Categoria> {
        val webResponse = service.getCategorias().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.categorias
        }
        return emptyList()
    }
    suspend fun getChistesPorCategoria(idcategoria: String): List<Chiste> {
        val webResponse = service.getChistesPorCategoria(idcategoria).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chistes
        }
        return emptyList()
    }

    suspend fun getDataUsuarioPorNickPass(nick: String, pass: String): Usuario? {
        val webResponse = service.getDataUsuarioPorNickPass(nick, pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun getMedia(idchiste: String): String {
        val webResponse = service.getMedia(idchiste ).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avg
        }
        return ""
    }


    suspend fun savePuntos(puntos: Punto): Punto? {
        var puntoResponse:Punto? = null
        val webResponse = service.savePuntos(puntos).await()
        if (webResponse.isSuccessful) {
            puntoResponse = webResponse.body()!!.punto
        }
        return puntoResponse
    }
    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        var usuarioResponse:Usuario? = null
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            usuarioResponse = webResponse.body()!!.usuario
        }
        return usuarioResponse
    }
    suspend fun saveChiste(chiste: Chiste): Chiste? {
        var chisteResponse:Chiste? = null
        val webResponse = service.saveChiste(chiste).await()
        if (webResponse.isSuccessful) {
            chisteResponse = webResponse.body()!!.chiste
        }
        return chisteResponse
    }



// ……..   resto de métodos del Repository ………..
}
