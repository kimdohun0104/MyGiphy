package com.dsm.mygiphy.presentation.ui.trend

import android.content.res.Configuration
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        (rv_trend.layoutManager as StaggeredGridLayoutManager).spanCount =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
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
