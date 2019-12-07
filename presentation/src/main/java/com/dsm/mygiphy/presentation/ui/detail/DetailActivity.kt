package com.dsm.mygiphy.presentation.ui.detail

import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityDetailBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.model.GifModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_detail

    private val gifModel: GifModel by lazy { intent?.getParcelableExtra("gif_model") as GifModel }

    override fun viewInit() {
        ib_detail_back.setOnClickListener { finish() }

        binding.gifModel = gifModel

//        binding.ivDetailGif.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, gifModel.height * 4)
    }

    override fun observeViewModel() {

    }
}
