package com.dsm.mygiphy.presentation.ui.trend

import android.view.View
import androidx.lifecycle.Observer
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityTrendBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.paging.NetworkState
import com.dsm.mygiphy.presentation.ui.adapter.GifListAdapter
import com.dsm.mygiphy.presentation.ui.favorite.FavoriteActivity
import com.dsm.mygiphy.presentation.ui.search.SearchActivity
import com.dsm.mygiphy.presentation.util.retrySnackbar
import com.dsm.mygiphy.presentation.util.setStaggeredGridSpanCount
import kotlinx.android.synthetic.main.activity_trend.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendActivity : BaseActivity<ActivityTrendBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_trend

    private val viewModel: TrendViewModel by viewModel()

    private val adapter: GifListAdapter by lazy { GifListAdapter() }

    override fun viewInit() {
        ib_trend_favorite.setOnClickListener { startActivity<FavoriteActivity>() }

        ll_trend_search.setOnClickListener { startActivity<SearchActivity>() }

        rv_trend.adapter = adapter
        rv_trend.setStaggeredGridSpanCount()
    }

    override fun observeViewModel() {
        viewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)

            if (it == NetworkState.FAILED || it == NetworkState.LOCAL)
                retrySnackbar(cl_trend_parent) {
                    viewModel.refreshTrendList()
                    pb_trend_loading.visibility = View.VISIBLE
                }

            if (it != NetworkState.LOADING) pb_trend_loading.visibility = View.GONE
        })

        viewModel.trendPagedList.observe(this, Observer { adapter.submitList(it) })
    }
}
