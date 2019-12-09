package com.dsm.mygiphy.presentation.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityDetailBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.model.GifModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_detail

    private val viewModel: DetailViewModel by viewModel()

    override fun viewInit() {
        ib_detail_back.setOnClickListener { finish() }

        viewModel.setGifModel(intent?.getParcelableExtra("gif_model") as GifModel)

        viewModel.getIsFavorite()
    }

    override fun observeViewModel() {
        viewModel.toastEvent.observe(this, Observer { toast(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
