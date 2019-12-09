package com.dsm.mygiphy.presentation.ui.favorite

import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityFavoriteBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.ui.adapter.FavoriteListAdapter
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_favorite

    private val viewModel: FavoriteViewModel by viewModel()

    private val adapter: FavoriteListAdapter by lazy { FavoriteListAdapter() }

    override fun viewInit() {
        ib_favorite_back.setOnClickListener { finish() }

        rv_favorite.adapter = adapter
        (rv_favorite.layoutManager as StaggeredGridLayoutManager).spanCount =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

        viewModel.getFavoriteGifList()
    }

    override fun observeViewModel() {
        viewModel.favoriteGifList.observe(this, Observer { adapter.setItems(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteGifList()
    }
}
