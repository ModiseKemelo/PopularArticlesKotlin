package com.mkemelo.kotlinpopulararticles

import com.google.gson.annotations.SerializedName
import com.mkemelo.kotlinpopulararticles.model.Media


data class ArticleData(
    val title: String,
    val source: String,
    val byline: String,
    val published_date: String,
    val updated: String,
    val abstract: String,
    val media: List<Media>
)
