package com.dsm.mygiphy.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ItemGifBinding
import com.dsm.mygiphy.presentation.model.GifModel
import com.dsm.mygiphy.presentation.paging.NetworkState
import com.dsm.mygiphy.presentation.ui.detail.DetailActivity
import org.jetbrains.anko.startActivity

class GifListAdapter : PagedListAdapter<GifModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GifModel>() {
            override fun areItemsTheSame(oldItem: GifModel, newItem: GifModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GifModel, newItem: GifModel): Boolean =
                oldItem == newItem
        }

        private const val TYPE_TREND = 0
        private const val TYPE_LOADING = 1
    }

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_TREND -> TrendHolder(ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> LoadingHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is TrendHolder -> holder.bind(getItem(position))
            is LoadingHolder -> (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
            else -> Unit
        }

    override fun getItemViewType(position: Int): Int =
        if (hasExtraRow() && position == itemCount - 1) TYPE_LOADING else TYPE_TREND

    private fun hasExtraRow(): Boolean =
        networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    inner class TrendHolder(private val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GifModel?) {
            binding.run {
                item?.let {
                    gifModel = item
                    root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, (item.height * 2.7).toInt())
                    root.setOnClickListener { it.context.startActivity<DetailActivity>("gif_model" to item) }
                }
            }
        }
    }

    inner class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}