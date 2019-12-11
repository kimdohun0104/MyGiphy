package com.dsm.mygiphy.presentation.ui.detail

import android.graphics.Rect
import android.util.TypedValue
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
            scrollToPosition(intent.getIntExtra("position", 0))
            PagerSnapHelper().attachToRecyclerView(this)

            clipToPadding = false
            val paddingValue = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                18f,
                resources.displayMetrics
            ).toInt()
            setPadding(paddingValue, 0, paddingValue, 0)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.right += (paddingValue / 2)
                    outRect.left += (paddingValue / 2)
                }
            })
        }
    }

    override fun observeViewModel() {
    }
}
