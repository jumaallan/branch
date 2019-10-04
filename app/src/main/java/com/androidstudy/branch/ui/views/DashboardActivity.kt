package com.androidstudy.branch.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.branch.R
import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.ui.adapter.ThreadRecyclerViewAdapter
import com.androidstudy.branch.ui.viewmodel.ThreadViewModel
import com.androidstudy.branch.util.NetworkState
import com.androidstudy.branch.util.toast
import kotlinx.android.synthetic.main.content_dashboard.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val threadViewModel: ThreadViewModel by viewModel()
    private lateinit var threadRecyclerViewAdapter: ThreadRecyclerViewAdapter

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupViews()
        setUpThreadList()

    }

    private fun setupViews() {

        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorAccent,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

    }

    @ExperimentalCoroutinesApi
    private fun setUpThreadList() {

        threadRecyclerViewAdapter = ThreadRecyclerViewAdapter()
        shimmerRecyclerView.apply {
            adapter = threadRecyclerViewAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(applicationContext, LinearLayout.VERTICAL)
            )
        }

        threadRecyclerViewAdapter.onThreadClickListener = ::onThreadClick

        threadViewModel.threadDataSource.loadState.observe(this, Observer {
            swipeRefreshLayout.isRefreshing =
                it == NetworkState.LOADING
        })

        threadViewModel.fetchThreads().observe(this, Observer {
            threadRecyclerViewAdapter.submitList(it)
        })

        swipeRefreshLayout.setOnRefreshListener {
            threadViewModel.invalidateDataSource()
        }

    }

    private fun onThreadClick(chat: Chat) {
        toast(chat.thread_id.toString())

        startActivity(Intent(this, ChatActivity::class.java))
    }
}
