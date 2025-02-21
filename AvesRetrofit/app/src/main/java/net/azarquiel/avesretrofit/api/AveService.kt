package net.azarquiel.avesretrofit.api


import kotlinx.coroutines.Deferred
import net.azarquiel.avesretrofit.model.Usuario
import net.azarquiel.avesretrofit.model.Result
import retrofit2.Response
import retrofit2.http.*
/**
 * Created by Paco Pulido.
 */
interface AveService {
    // No necesita nada para trabajar
    @GET("zonas")
    fun getDataZonas(): Deferred<Response<Result>>

   @GET("usuarios")
    fun getDataUsuarios(): Deferred<Response<Result>>

    // variable idbar en la ruta de la url => @Path
    @GET("zona/{idzona}/recursos")
    fun getDataRecursos(@Path("idzona") idzona: Int): Deferred<Response<Result>>
    // variable idbar en la ruta de la url => @Path
    @GET("recurso/{idrecurso}/comentarios")
    fun getDataComentarios(@Path("idrecurso") idrecurso: Int): Deferred<Response<Result>>

    // nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
    @GET("usuario")
    fun getDataUsuarioPorNickPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Result>>

    // post con objeto => @Body
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>

    // post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
    @FormUrlEncoded
    @POST("recurso/{idrecurso}/comentario")
    fun saveComentario(@Field("idrecurso") idrecurso: Int): Deferred<Response<Result>>

}


