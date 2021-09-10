package com.mkemelo.kotlinpopulararticles.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mkemelo.kotlinpopulararticles.model.ArticlesListData
import com.mkemelo.kotlinpopulararticles.network.RetrofitInstance
import com.mkemelo.kotlinpopulararticles.network.RetrofitService
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    lateinit var articlesListData: MutableLiveData<ArticlesListData>


    init {
        articlesListData = MutableLiveData()
    }


    fun getArticlesDataObserver(): MutableLiveData<ArticlesListData> {
        return articlesListData
    }

    fun fetchArticlesDataFromServer(numberOfDays: String) {
        val retroInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val call = retroInstance.getArticlesDataViaAPI(numberOfDays)
        call.enqueue(object : retrofit2.Callback<ArticlesListData>{
            override fun onResponse(call: Call<ArticlesListData>, response: Response<ArticlesListData>) {
                if(response.isSuccessful) {
                    articlesListData.postValue(response.body())
                } else {
                    articlesListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ArticlesListData>, t: Throwable) {
                articlesListData.postValue(null)
            }
        })
    }
}