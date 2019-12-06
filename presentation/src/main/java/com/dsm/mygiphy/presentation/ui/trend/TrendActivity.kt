package com.dsm.mygiphy.presentation.ui.trend

import android.view.View
import androidx.lifecycle.Observer
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityTrendBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.paging.NetworkState
import com.dsm.mygiphy.presentation.ui.adapter.TrendListAdapter
import com.dsm.mygiphy.presentation.util.retrySnackbar
import kotlinx.android.synthetic.main.activity_trend.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendActivity : BaseActivity<ActivityTrendBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_trend

    private val viewModel: TrendViewModel by viewModel()

    private val adapter = TrendListAdapter()

    override fun viewInit() {
        rv_trend.adapter = adapter
    }

    override fun observeViewModel() {
        viewModel.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
            pb_trend_loading.visibility = View.GONE
            if (it == NetworkState.FAILED || it == NetworkState.LOCAL)
                retrySnackbar(ll_trend_parent) { viewModel.refreshTrendList() }
        })

        viewModel.trendPagedList.observe(this, Observer { adapter.submitList(it) })
    }
}
