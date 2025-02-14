package net.azarquiel.chistesapp.api

import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.model.Punto
import net.azarquiel.chistesapp.model.Usuario

class MainRepository() {
    val service = WebAccess.chistesService

    suspend fun getCategorias(): List<Categoria> {
        val webResponse = service.getCategorias().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.categorias
        }
        return emptyList()
    }

    suspend fun getChistesByCategoria(idcategoria: String): List<Chiste> {
        val webResponse = service.getChistesByCategoria(idcategoria).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chistes
        }
        return emptyList()
    }

    suspend fun getAvgChiste(idchiste: String): String {
        val webResponse = service.getAvgChiste(idchiste).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avg
        }
        return ""
    }

    suspend fun getDataUsuarioPorNickPass(nick: String,pass: String): Usuario? {
        val webResponse = service.getDataUsuarioPorNickPass(nick,pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }


    suspend fun saveChiste(chiste: Chiste): Chiste? {
        val webResponse = service.saveChiste(chiste).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chiste
        }
        return null
    }

    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun savePuntos(idchiste: String, punto: Punto): Punto? {
        val webResponse = service.savePuntos(idchiste, punto).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.punto
        }
        return null
    }




// ……..   resto de métodos del Repository ………..
}
