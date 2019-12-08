package com.dsm.mygiphy.presentation.ui.detail

import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityDetailBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.model.GifModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_detail

    private val gifModel: GifModel by lazy { intent?.getParcelableExtra("gif_model") as GifModel }

    private val gifDao: GifDao by inject()

    override fun viewInit() {
        ib_detail_back.setOnClickListener { finish() }

        binding.gifModel = gifModel
        binding.isFavorite = gifDao.isFavoriteGif(gifModel.id)

        iv_detail_favorite.setOnClickListener {
            (!gifDao.isFavoriteGif(gifModel.id)).let {
                gifDao.setFavorite(gifModel.id, it).subscribe()
                binding.isFavorite = it
            }
        }
    }

    override fun observeViewModel() {

    }
}
