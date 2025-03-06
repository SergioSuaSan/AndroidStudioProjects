package net.azarquiel.avesretrofit.api

import net.azarquiel.avesretrofit.model.Comentario
import net.azarquiel.avesretrofit.model.Recurso
import net.azarquiel.avesretrofit.model.Usuario
import net.azarquiel.avesretrofit.model.Zona

class MainRepository() {
    val service = AveAccess.aveService

    //FUNCIONES GET
    suspend fun getDataZonas(): List<Zona> {
        val webResponse = service.getDataZonas().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.zonas
        }
        return emptyList()
    }

    suspend fun getDataUsuarioPorNickPass(nick:String, pass:String): Usuario? {
        val webResponse = service.getDataUsuarioPorNickPass(nick, pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }



    suspend fun getDataUsuarios(): List<Usuario> {
        val webResponse = service.getDataUsuarios().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuarios
        }
        return emptyList()
    }

    suspend fun getDataComentarios(idrecurso:Int): List<Comentario> {
        val webResponse = service.getDataComentarios(idrecurso).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comentarios
        }
        return emptyList()
    }
    suspend fun getDataRecursos(idzona:Int): List<Recurso> {
        val webResponse = service.getDataRecursos(idzona).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.recursos
        }
        return emptyList()
    }



   //FUNCIONES POST
    suspend fun saveComentario(
       idrecurso: Int, idUsuario: Int, fecha: String, comentario: String
   ): Comentario? {
        val webResponse = service.saveComentario(idrecurso, idUsuario, fecha, comentario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comentario
        }
        return null
    }
    suspend fun saveUsuario(
        usuario: Usuario
    ): Usuario? {
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }


// ……..   resto de métodos del Repository ………..
}
