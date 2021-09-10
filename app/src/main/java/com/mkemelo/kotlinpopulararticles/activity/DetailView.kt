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

    private var readMoreLink: String = ""
    private lateinit var binding: ActivityDetailBinding

    var articleUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.btnReadMore.setOnClickListener(this)

        setContentView(binding.root)
        val intent = getIntent()
        binding.tvTitle.text  = intent.extras?.getString("title").toString()
        binding.tvByline.text  = intent.extras?.getString("by").toString()
        binding.tvPublished.text  = "Published: " +intent.extras?.getString("published").toString()
        binding.tvAbstract.text  = intent.extras?.getString("articleAbstract").toString()
        binding.tvCaption.text  = "Caption: " +intent.extras?.getString("caption").toString()
        binding.tvUpdated.text = "Updated: " +intent.extras?.getString("updated").toString()
        val url = intent.extras?.getString("img")
        readMoreLink = intent.extras?.getString("link").toString()

            val imageThumb = binding.img
        Glide.with(binding.img)
            .load(url)
            .error(R.drawable.thumbnail)
            .fallback(R.drawable.thumbnail)
            .into(binding.img)

    }

    fun getUrlFromIntent(view: View) {
        val url = readMoreLink
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            getUrlFromIntent(p0)
        };
    }
}
