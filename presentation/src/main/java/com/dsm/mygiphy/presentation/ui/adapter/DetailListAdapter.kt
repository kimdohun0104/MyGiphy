package com.dsm.mygiphy.presentation.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.dsm.mygiphy.databinding.ItemDetailBinding
import com.dsm.mygiphy.presentation.model.GifModel
import com.dsm.mygiphy.presentation.ui.detail.DetailViewModel

class DetailListAdapter(
    private val detailItems: List<GifModel>,
    private val viewModel: DetailViewModel
) : RecyclerView.Adapter<DetailListAdapter.DetailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder =
        DetailHolder(ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = detailItems.size

    override fun onBindViewHolder(holder: DetailHolder, position: Int) = holder.bind()

    inner class DetailHolder(private val binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = detailItems[adapterPosition]
            binding.viewModel = viewModel
            binding.gifModel = item
            viewModel.isFavoriteGif(item.id).observe(binding.root.context as LifecycleOwner, Observer { binding.isFavorite = it })
            binding.ivDetailGif.updateLayoutParams {
                width = MATCH_PARENT
                height = (item.height * (Resources.getSystem().displayMetrics.widthPixels / 200.0)).toInt()
            }
        }
    }
}