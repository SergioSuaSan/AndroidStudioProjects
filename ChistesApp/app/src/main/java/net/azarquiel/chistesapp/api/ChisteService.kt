package net.azarquiel.chistesapp.api

import kotlinx.coroutines.Deferred
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.model.Punto
import retrofit2.Response
import retrofit2.http.*
import net.azarquiel.chistesapp.model.Result
import net.azarquiel.chistesapp.model.Usuario

/**
 * Created by Paco Pulido.
 */
interface ChisteService {
    // No necesita nada para trabajar
    @GET("categorias")
    fun getCategorias(): Deferred<Response<Result>>

    // variable idbar en la ruta de la url => @Path
    @GET("categoria/{idcategoria}/chistes")
    fun getChistesPorCategoria(@Path("idcategoria") idcategoria: String): Deferred<Response<Result>>

    // variable idbar en la ruta de la url => @Path
    @GET("chiste/{idchiste}/avgpuntos")
    fun getMedia(@Path("idchiste") idchiste: String): Deferred<Response<Result>>

    // nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
    @GET("usuario")
    fun getDataUsuarioPorNickPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Result>>

    // post con objeto => @Body
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>

    // post con objeto => @Body
    @POST("chiste")
    fun saveChiste(@Body chiste: Chiste): Deferred<Response<Result>>

    // post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
    @FormUrlEncoded
    @POST("chiste/{idchiste}/punto")
    fun savePuntos(@Path("idchiste") idchiste: String,
                   @Body punto: Punto): Deferred<Response<Result>>

// ……..   resto de métodos de la interfaz ………..
}
