package com.mkemelo.kotlinpopulararticles.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mkemelo.kotlinpopulararticles.databinding.ActivityDetailBinding
import android.content.Intent
import android.net.Uri


class DetailView  : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding

    var articleUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        articleUrl = "www.google.com"
        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.btnReadMore.setOnClickListener(this)
        setContentView(binding.root)
        val url = "https://static01.nyt.com/images/2021/09/05/multimedia/05XP-Lakeland-02/merlin_194254932_7db13d4e-85b5-4b55-91d5-35f3732760fa-mediumThreeByTwo440.jpg"
        val imageThumb = binding.img
        Glide.with(imageThumb)
            .load(url)
            /*.placeholder(R.drawable.thumbnail)
            .error(R.drawable.thumbnail)
            .fallback(R.drawable.thumbnail)*/
            .into(imageThumb)

    }

    override fun onClick(p0: View?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl))
        startActivity(browserIntent)
    }
}
