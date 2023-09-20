package com.example.githubuser.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.example.githubuser.data.response.GithubDetail
import com.example.githubuser.ui.DetailViewModel
import com.example.githubuser.ui.adapter.FollowPagerAdapter

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "EXTRA_DETAIL"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_DETAIL) ?: ""
        Log.d("DetailActivity", "OnCreate: DATA USERNAME: $username")
        initView(username)
        observeViewModel()
        detailViewModel.getDetailUser(username)
    }


    private fun initView(username: String){
        val followPagerAdapter = FollowPagerAdapter(this, username)
        binding.viewPager.adapter = followPagerAdapter

        val tabsTittle = arrayListOf("Followers", "Following")
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, position ->
            tab.text = tabsTittle[position]
        }.attach()

    }

    private fun observeViewModel(){
        detailViewModel = DetailViewModel()
        detailViewModel.userDetail.observe(this) {userDetail ->
            showDetailUser(userDetail)
        }
    }

    private fun showDetailUser (data: GithubDetail){
        Glide.with(binding.root)
            .load(data.avatarUrl)
            .into(binding.imgProfile)
        binding.tvUsername.text = data.login
        binding.tvNamee.text = data.login
    }
}