package com.mkemelo.kotlinpopulararticles.adapter

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mkemelo.kotlinpopulararticles.ArticleData
import com.mkemelo.kotlinpopulararticles.activity.DetailView
import com.mkemelo.kotlinpopulararticles.R
import kotlinx.android.synthetic.main.articles_list_item.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<ArticleData>()

    fun setListData(data: ArrayList<ArticleData>) {
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.articles_list_item, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(p0: View?) {
            Toast.makeText(itemView.context, "****** $p0", Toast.LENGTH_LONG).show()
            val intent = Intent(itemView.context, DetailView::class.java)
            intent.putExtra("desc", "Description -lnkkb")
            itemView.context.startActivity(intent)
        }

        // Set values displayed each card
        val tvTitle = view.tvTitle
        val abstract = view.tvAbstract
        val tvByline = view.tvByline
        val imageThumb = view.imageThumb
        val published = view.tvPublished

        fun bind(data: ArticleData) {
            tvTitle.text = data.title
            tvByline.text = data.byline
            published.text = data.published_date
            abstract.text = data.abstract



            if (data?.media.isNotEmpty() && data?.media[0]?.metadata.isNotEmpty() && !TextUtils.isEmpty(data?.media[0]?.metadata[0].url)) {
                val img = data?.media[0]?.metadata[0]?.url ?: "No image"
                Glide.with(imageThumb)
                    .load(img)
                    .placeholder(R.drawable.thumbnail)
                    .error(R.drawable.thumbnail)
                    .fallback(R.drawable.thumbnail)
                    .into(imageThumb)

            } else {
                println("**************** NOTHING TO PRINT HERE");
                Glide.with(imageThumb)
                    .load(R.drawable.thumbnail)
                    .into(imageThumb)
            }
           /* if(!TextUtils.isEmpty(data?.media[0]?.metadata[0]?.url)) {
                val img = data?.media[0]?.metadata[0]?.url
                println("**************** $img");

                *//*Glide.with(imageThumb)
                    .load(data.media[0].metadata[0].url)
                    .circleCrop()
                    .placeholder(R.drawable.default_thumb)
                    .error(R.drawable.default_thumb)
                    .fallback(R.drawable.default_thumb)
                    .into(imageThumb)*//*
            } else {

                println("**************** NOTHING TO SHOW HERE");
            }*/
        }

        init {
            view.setOnClickListener(this)
        }

    }

}