package com.dsm.mygiphy.presentation.ui.detail

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.dsm.mygiphy.R
import com.dsm.mygiphy.databinding.ActivityDetailBinding
import com.dsm.mygiphy.presentation.base.BaseActivity
import com.dsm.mygiphy.presentation.ui.adapter.DetailListAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_detail

    private val viewModel: DetailViewModel by viewModel()
    private val adapter: DetailListAdapter by lazy { DetailListAdapter(intent.getParcelableArrayListExtra("gif_list")!!, viewModel) }

    override fun viewInit() {
        ib_detail_back.setOnClickListener { finish() }

        rv_detail.run {
            adapter = this@DetailActivity.adapter
            clipToPadding = false
            setPadding(30, 0, 30, 0)
            scrollToPosition(intent.getIntExtra("position", 0))
            PagerSnapHelper().attachToRecyclerView(this)
            addItemDecoration(object: RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.right += 15
                    outRect.left += 15
                }
            })
        }
    }

    override fun observeViewModel() {
    }
}
