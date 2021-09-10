package com.mkemelo.kotlinpopulararticles.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mkemelo.kotlinpopulararticles.databinding.ActivityDetailBinding
import android.content.Intent
import android.net.Uri
import com.mkemelo.kotlinpopulararticles.R


class DetailView  : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding

    var articleUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleUrl = "www.google.com"
        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.btnReadMore.setOnClickListener(this)

        setContentView(binding.root)
        val intent = getIntent()
        binding.tvTitle.text  = intent.extras?.getString("title").toString()
        binding.tvByline.text  = intent.extras?.getString("by").toString()
        binding.tvPublished.text  = "Published: " +intent.extras?.getString("published").toString()
        binding.tvAbstract.text  = intent.extras?.getString("articleAbstract").toString()
        binding.tvCaption.text  = "Caption: " +intent.extras?.getString("caption").toString()
        val url = intent.extras?.getString("img")

            val imageThumb = binding.img
        Glide.with(binding.img)
            .load(url)
            .error(R.drawable.thumbnail)
            .fallback(R.drawable.thumbnail)
            .into(binding.img)

    }

    override fun onClick(p0: View?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl))
        startActivity(browserIntent)
    }
}
