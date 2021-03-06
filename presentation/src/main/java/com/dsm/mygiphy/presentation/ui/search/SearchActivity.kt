package com.dsm.mygiphy.presentation.ui.search

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivitySearchBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.ui.adapter.SearchHistoryListAdapter
import com.dsm.mygiphy.presentation.ui.search.searchResult.SearchResultActivity
import com.dsm.mygiphy.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search

    private val viewModel: SearchViewModel by viewModel()

    private val adapter: SearchHistoryListAdapter by lazy { SearchHistoryListAdapter(viewModel) }

    override fun viewInit() {
        et_search.requestFocus()
        et_search.setEditorActionListener(EditorInfo.IME_ACTION_SEARCH) { viewModel.onSearchGif(getSearchText()) }

        ib_search.setOnClickListener { viewModel.onSearchGif(getSearchText()) }

        ib_search_back.setOnClickListener { finish() }

        rv_search_history.adapter = adapter
    }

    private fun getSearchText() = et_search.text.toString().trim()

    override fun observeViewModel() {
        viewModel.getSearchHistory().observe(this, Observer { adapter.setItems(it) })

        viewModel.intentSearchResultEvent.observe(this, Observer { startActivity<SearchResultActivity>("search" to it) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })
    }

}
