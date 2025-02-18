package net.azarquiel.chistesretrofitjpc.api

import kotlinx.coroutines.Deferred
import net.azarquiel.chistesretrofitjpc.model.Chiste
import net.azarquiel.chistesretrofitjpc.model.Punto
import net.azarquiel.chistesretrofitjpc.model.Result
import net.azarquiel.chistesretrofitjpc.model.Usuario
import retrofit2.Response
import retrofit2.http.*
/**
 * Created by Paco Pulido.
 */
interface ChistesService {
    // No necesita nada para trabajar
    @GET("categorias")
    fun getCategorias(): Deferred<Response<Result>>

    // variable idbar en la ruta de la url => @Path
    @GET("categoria/{idcategoria}/chistes")
    fun getChistesByCategoria(@Path("idcategoria") idcategoria: Int): Deferred<Response<Result>>

    // nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
    @GET("usuario")
    fun getDataUsuarioPorNickPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Result>>

    @GET("chiste/{idchiste}/avgpuntos")
    fun getAvg(@Path("idchiste") idchiste: Int): Deferred<Response<Result>>

    // post con objeto => @Body
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>
    // post con objeto => @Body

    @POST("chiste")
    fun saveChiste(@Body chiste: Chiste): Deferred<Response<Result>>

    @POST("chiste/{idchiste}/punto")
    fun savePunto(@Body punto: Punto): Deferred<Response<Result>>

}
