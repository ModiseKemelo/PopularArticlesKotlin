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
import com.mkemelo.kotlinpopulararticles.databinding.ArticlesListItemBinding
import java.util.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<ArticleData>()

    fun setListData(data: ArrayList<ArticleData>) {
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ArticlesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

        val binding = ArticlesListItemBinding.bind(view)
        fun bind(data: ArticleData) {
            // Set values displayed each card
            val tvTitle = binding.tvTitle
            val abstract = binding.tvAbstract
            val tvByline = binding.tvByline
            val imageThumb = binding.imageThumb
            val published = binding.tvPublished
            with(binding) {
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

            }

        }

        init {
            view.setOnClickListener(this)
        }

    }

}