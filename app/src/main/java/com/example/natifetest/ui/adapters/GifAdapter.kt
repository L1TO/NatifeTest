package com.example.natifetest.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.natifetest.databinding.GifItemLayoutBinding
import com.example.natifetest.model.Data

interface GifActionListener {
    fun onGifDetails(data: Data)
}

class GifAdapter(
    private val gifActionListener: GifActionListener
) : PagingDataAdapter<Data, GifAdapter.GifViewHolder>(GifsDiffUtilCallBack()),
    View.OnClickListener {
    class GifViewHolder(val binding: GifItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GifItemLayoutBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val currentGif = getItem(position) ?: return
        holder.itemView.tag = currentGif
        Glide.with(holder.binding.gifIv.context)
            .asGif()
            .load(currentGif.images?.fixed_height_small?.url)
            .into(holder.binding.gifIv)
    }

    override fun onClick(v: View) {
        val gif = v.tag as Data
        gifActionListener.onGifDetails(gif)
    }

}

class GifsDiffUtilCallBack : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}
