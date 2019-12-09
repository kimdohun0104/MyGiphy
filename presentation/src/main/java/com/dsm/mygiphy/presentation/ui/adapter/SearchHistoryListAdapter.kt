package com.dsm.mygiphy.presentation.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.mygiphy.R
import com.dsm.mygiphy.presentation.ui.search.searchResult.SearchResultActivity
import kotlinx.android.synthetic.main.item_search_history.view.*
import org.jetbrains.anko.startActivity


class SearchHistoryListAdapter : RecyclerView.Adapter<SearchHistoryListAdapter.HistoryHolder>() {

    private val historyItems = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder =
        HistoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false))

    override fun getItemCount(): Int = historyItems.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) = holder.bind()

    fun addItems(items: List<String>) {
        historyItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.tv_search_history.text = historyItems[adapterPosition]
            itemView.setOnClickListener {
                (it.context as Activity).finish()
                it.context.startActivity<SearchResultActivity>("search" to historyItems[adapterPosition])
            }
        }
    }
}