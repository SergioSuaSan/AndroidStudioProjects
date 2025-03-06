package net.azarquiel.avesretrofit.api


import kotlinx.coroutines.Deferred
import net.azarquiel.gafasretrofitjpc.model.Comentario
import net.azarquiel.gafasretrofitjpc.model.Usuario
import net.azarquiel.gafasretrofitjpc.model.Result
import retrofit2.Response
import retrofit2.http.*
/**
 * Created by Paco Pulido.
 */
interface GafaService {
    // No necesita nada para trabajar
    @GET("marcas")
    fun getMarcas(): Deferred<Response<Result>>

    // variable idbar en la ruta de la url => @Path
    @GET("marca/{idmarca}/gafas")
    fun getGafasbyMarca(@Path("idmarca") idmarca: Int): Deferred<Response<Result>>


    @GET("gafa/{idgafa}/comentarios")
    fun getComentarios(@Path("idgafa") idgafa: Int): Deferred<Response<Result>>



   @GET("usuarios")
    fun getUsuarios(): Deferred<Response<Result>>


    // nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
    @GET("usuario")
    fun getUsuarioPorNickPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Result>>

    // post con objeto => @Body
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>

    // post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
    @FormUrlEncoded
    @POST("gafa/{idgafa}/comentario")
    fun saveComentario(@Path("idgafa") idgafa: Int,
                       @Field("usuario") usuario: Int,
                       @Field("fecha") fecha: String,
                       @Field("comentario") comentario: String
    ): Deferred<Response<Result>>

}


