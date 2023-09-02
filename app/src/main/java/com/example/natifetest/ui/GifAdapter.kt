package com.example.natifetest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.natifetest.databinding.GifItemLayoutBinding
import com.example.natifetest.model.Data

interface GifActionListener{
    fun onGifDetails(data: Data)
}

class GifAdapter(
    private val gifActionListener: GifActionListener
): RecyclerView.Adapter<GifAdapter.GifViewHolder>(), View.OnClickListener {
    class GifViewHolder(val binding: GifItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    var gifs: List<Data> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GifItemLayoutBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return GifViewHolder(binding )
    }

    override fun getItemCount() = gifs.size

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val currentGif = gifs[position]
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