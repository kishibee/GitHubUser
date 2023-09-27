package com.example.githubuser.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.local.entity.Fav
import com.example.githubuser.data.remote.response.GithubDetail
import com.example.githubuser.data.repository.FavRepository
import com.example.githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(): ViewModel() {
//    private val mFavRepository: FavRepository = FavRepository()
//    fun getFavUserByUsername(): LiveData<Fav> = mFavRepository.getFavUserByUsername(username)
    private var _userDetail = MutableLiveData<GithubDetail>()
    val userDetail: LiveData<GithubDetail> get() = _userDetail


    fun getDetailUser(username: String) {
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<GithubDetail> {

            override fun onResponse(
                call: Call<GithubDetail>,
                response: Response<GithubDetail>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _userDetail.value = responseBody
                    }
                } else {
                    Log.e("DetailActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubDetail>, t: Throwable) {
                Log.e("DetailActivity", "onFailure: ${t.message}")
            }
        })
    }

}