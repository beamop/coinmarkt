package me.bmop.coinmarkt.ui.adapter.cc

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*
import me.bmop.coinmarkt.extension.listen
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.data.db.entity.cc.news.CryptoControlNewsEntry

class NewsAdapter(
    private val newsList: List<CryptoControlNewsEntry>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        return NewsViewHolder(
            itemView = itemView
        ).listen { position, _ ->
            val item = newsList[position]
            val newsUrl = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))

            startActivity(itemView.context, newsUrl, null)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
        val currentItem = newsList[position]

        Picasso.get()
            .load(currentItem.thumbnail)
            .into(holder.newsImage)

        if(currentItem.title.length > 35)
            holder.newsTitle.text = currentItem.title.plus("...")
        else
            holder.newsTitle.text = currentItem.title
        holder.newsDomain.text = currentItem.sourceDomain
        holder.newsCategory.text = currentItem.primaryCategory
    }

    override fun getItemCount(): Int = newsList.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.news_image
        val newsTitle: TextView = itemView.news_title
        val newsDomain: TextView = itemView.news_domain
        val newsCategory: TextView = itemView.news_category
    }

}