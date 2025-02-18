package net.azarquiel.parquesnretrofitjpc.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import net.azarquiel.parquesnretrofitjpc.model.Result
/**
 * Created by Paco Pulido.
 */
interface ParqueService {
    // No necesita nada para trabajar
    @GET("comunidades")
    fun getComunidades(): Deferred<Response<Result>>

    @GET("parques")
    fun getParques(): Deferred<Response<Result>>

    // variable idbar en la ruta de la url => @Path
    @GET("comunidad/{idcomunidad}/parques")
    fun getParquesByComunidad(@Path("idcomunidad") idcomunidad: Int): Deferred<Response<Result>>

   // variable idbar en la ruta de la url => @Path
    @GET("parque/{idparque}/imagenes")
    fun getImagenesByParque(@Path("idparque") idparque: Int): Deferred<Response<Result>>

    // post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
    @FormUrlEncoded
    @POST("parque/{idparque}/like")
    fun darLike(@Path("idparque") idparque: Int): Deferred<Response<Result>>


}
