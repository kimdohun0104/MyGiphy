package com.dsm.mygiphy.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.dsm.mygiphy.databinding.ItemGifBinding
import com.dsm.mygiphy.presentation.model.GifModel
import com.dsm.mygiphy.presentation.ui.detail.DetailActivity
import com.dsm.mygiphy.presentation.util.DimensionUtil
import org.jetbrains.anko.startActivity

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.FavoriteHolder>() {

    private val gifItems = arrayListOf<GifModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder =
        FavoriteHolder(ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = gifItems.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) = holder.bind()

    fun setItems(items: List<GifModel>) {
        gifItems.clear()
        gifItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class FavoriteHolder(private val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = gifItems[adapterPosition]
            binding.run {
                gifModel = item

                root.updateLayoutParams {
                    width = MATCH_PARENT
                    height = DimensionUtil.getHeightByRatioWithSpan(item.height)
                }

                root.setOnClickListener {
                    it.context.startActivity<DetailActivity>(
                        "gif_list" to gifItems,
                        "position" to adapterPosition
                    )
                }
            }
        }
    }
}