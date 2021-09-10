package com.mkemelo.kotlinpopulararticles.network

import com.mkemelo.kotlinpopulararticles.model.ArticlesListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("{q}.json?api-key=HFjtY5zpH7xOM6Mz3gnPazWaDiRBjp22")
    fun getArticlesDataViaAPI(@Path(value="q") path: String): Call<ArticlesListData>

}