package com.androidstudy.branch.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidstudy.branch.R
import kotlinx.android.synthetic.main.content_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        shimmerRecyclerView.showShimmerAdapter()
    }
}
