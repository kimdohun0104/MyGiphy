package com.dsm.mygiphy.presentation.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.mygiphy.databinding.ItemSearchHistoryBinding
import com.dsm.mygiphy.presentation.ui.search.SearchViewModel
import com.dsm.mygiphy.presentation.ui.search.searchResult.SearchResultActivity
import org.jetbrains.anko.startActivity


class SearchHistoryListAdapter(
    private val viewModel: SearchViewModel
) : RecyclerView.Adapter<SearchHistoryListAdapter.HistoryHolder>() {

    private val historyItems = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder =
        HistoryHolder(ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = historyItems.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) = holder.bind()

    fun setItems(items: List<String>) {
        historyItems.clear()
        historyItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class HistoryHolder(private val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.viewModel = viewModel
            binding.search = historyItems[adapterPosition]
            binding.root.setOnClickListener {
                (it.context as Activity).finish()
                it.context.startActivity<SearchResultActivity>("search" to historyItems[adapterPosition])
            }
        }
    }
}