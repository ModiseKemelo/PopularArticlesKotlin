package com.mkemelo.kotlinpopulararticles.model

import com.google.gson.annotations.SerializedName

data class Media(val type : String,
                 val subtype : String,
                 val caption : String,
                 val copyright : String,
                 val approved_for_syndication : Int,
                 @SerializedName("media-metadata") val metadata : List<MediaMetadata>)