package com.dsm.mygiphy.presentation.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityDetailBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.util.retrySnackbar
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_detail

    private val viewModel: DetailViewModel by viewModel()

    private val gifId: String by lazy { intent.getStringExtra("gif_id") ?: "" }

    override fun viewInit() {
        iv_detail_favorite.setOnClickListener { viewModel.onClickFavorite(gifId) }

        ib_detail_back.setOnClickListener { finish() }

        viewModel.getGifDetail(gifId)
    }

    override fun observeViewModel() {
        viewModel.toastEvent.observe(this, Observer { toast(it) })

        viewModel.snackbarRetryEvent.observe(this, Observer {
            retrySnackbar(cl_detail_parent) { viewModel.getGifDetail(gifId) }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }


}
