package net.azarquiel.gafasretrofitjpc.api

import net.azarquiel.avesretrofit.api.GafaAccess
import net.azarquiel.gafasretrofitjpc.model.Comentario
import net.azarquiel.gafasretrofitjpc.model.Gafa
import net.azarquiel.gafasretrofitjpc.model.Marca
import net.azarquiel.gafasretrofitjpc.model.Usuario

class MainRepository() {
    val service = GafaAccess.gafaService

    //FUNCIONES GET
    suspend fun getMarcas(): List<Marca> {
        val webResponse = service.getMarcas().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.marcas
        }
        return emptyList()
    }

    suspend fun getUsuarioPorNickPass(nick:String, pass:String): Usuario? {
        val webResponse = service.getUsuarioPorNickPass(nick, pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }



    suspend fun getUsuarios(): List<Usuario> {
        val webResponse = service.getUsuarios().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuarios
        }
        return emptyList()
    }

    suspend fun getComentarios(idgafa:Int): List<Comentario> {
        val webResponse = service.getComentarios(idgafa).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comentarios
        }
        return emptyList()
    }
    suspend fun getGafasbyMarca(idmarca:Int): List<Gafa> {
        val webResponse = service.getGafasbyMarca(idmarca).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.gafas
        }
        return emptyList()
    }



   //FUNCIONES POST
    suspend fun saveComentario(
       idgafa: Int, idUsuario: Int, fecha: String, comentario: String
   ): Comentario? {
        val webResponse = service.saveComentario(idgafa, idUsuario, fecha, comentario).await()
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
