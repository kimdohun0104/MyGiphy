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

    private val adapter: SearchHistoryListAdapter by lazy { SearchHistoryListAdapter() }

    private val viewModel: SearchViewModel by viewModel()

    override fun viewInit() {
        ib_search_back.setOnClickListener { finish() }

        rv_search_history.adapter = adapter

        ib_search.setOnClickListener { startSearchResultActivity() }

        et_search.setEditorActionListener(EditorInfo.IME_ACTION_SEARCH) { startSearchResultActivity() }

        viewModel.getSearchHistory()
    }

    override fun observeViewModel() {
        viewModel.historyItems.observe(this, Observer { adapter.addItems(it) })
    }

    private fun startSearchResultActivity() {
        et_search.text.toString().trim().let {
            if (it.isNotBlank()) {
                startActivity<SearchResultActivity>("search" to et_search.text.toString().trim())
                finish()
            }
        }
    }
}
