package com.dsm.mygiphy.presentation.ui.detail

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
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

    private val gifModel: GifModel by lazy { intent?.getParcelableExtra("gif_model") as GifModel }
    private val viewModel: DetailViewModel by viewModel()

    override fun viewInit() {
        ib_detail_back.setOnClickListener { finish() }

        iv_detail_gif.layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, gifModel.height * (Resources.getSystem().displayMetrics.widthPixels / 200)).apply {
            leftToLeft = ConstraintSet.PARENT_ID
            rightToRight = ConstraintSet.PARENT_ID
            topToBottom = ib_detail_back.id
            topMargin = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics
            ).toInt()
        }

        viewModel.setGifModel(gifModel)

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
