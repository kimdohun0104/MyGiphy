package com.dsm.mygiphy.presentation.ui.search.searchResult

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivitySearchResultBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.paging.NetworkState
import com.dsm.mygiphy.presentation.ui.adapter.GifListAdapter
import com.dsm.mygiphy.presentation.util.retrySnackbar
import com.dsm.mygiphy.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_search_result.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search_result

    private val search: String by lazy { intent.getStringExtra("search") }
    private val viewModel: SearchResultViewModel by viewModel { parametersOf(search) }

    private val adapter: GifListAdapter by lazy {
        GifListAdapter(Resources.getSystem().displayMetrics.widthPixels / (if (isPortrait()) 2 else 4) / 200.0)
    }

    override fun viewInit() {
        ib_search_result_back.setOnClickListener { finish() }

        ib_result_search.setOnClickListener { startSearchResult() }

        et_search_result.setEditorActionListener(EditorInfo.IME_ACTION_SEARCH) { startSearchResult() }

        rv_search_result.adapter = adapter
        (rv_search_result.layoutManager as StaggeredGridLayoutManager).spanCount =
            if (isPortrait()) 2 else 4
    }

    override fun observeViewModel() {
        viewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)

            if (it == NetworkState.FAILED || it == NetworkState.LOCAL)
                retrySnackbar(cl_search_result_parent) {
                    viewModel.refreshResultList()
                    pb_result_loading.visibility = View.VISIBLE
                }

            if (it != NetworkState.LOADING) pb_result_loading.visibility = View.GONE
        })

        viewModel.resultPagedList.observe(this, Observer { adapter.submitList(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    private fun startSearchResult() {
        et_search_result.text.toString().trim().let {
            if (it.isNotBlank()) {
                startActivity<SearchResultActivity>("search" to it)
                finish()
            }
        }
    }

    private fun isPortrait() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}
