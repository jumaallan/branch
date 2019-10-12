package com.androidstudy.branch.ui.views

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.branch.R
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.ui.adapter.CustomItemClickListener
import com.androidstudy.branch.ui.adapter.ThreadRecyclerViewAdapter
import com.androidstudy.branch.ui.viewmodel.ThreadViewModel
import com.androidstudy.branch.util.toast
import kotlinx.android.synthetic.main.content_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val vm: ThreadViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorAccent,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        vm.fetchThreads().observe(this, Observer {
            setUpViews(it)
        })

    }

    private fun setUpViews(messageThreadList: PagedList<MessageThread>?) {
        if (messageThreadList.isNullOrEmpty()) {
            shimmerRecyclerView.visibility = View.GONE
        } else {
            shimmerRecyclerView.visibility = View.VISIBLE

            val itemDecor =
                DividerItemDecoration(this, LinearLayout.VERTICAL)
            val layoutManager =
                LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            shimmerRecyclerView.layoutManager = layoutManager
            shimmerRecyclerView.addItemDecoration(itemDecor)

            val customerAdapter = ThreadRecyclerViewAdapter(messageThreadList, this, object :
                CustomItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    val messageThread = messageThreadList[position]
                    toast(messageThread?.body.toString())
                }
            })
            shimmerRecyclerView.adapter = customerAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        vm.fetchThreads().observe(this, Observer {
            setUpViews(it)
        })

    }
}
