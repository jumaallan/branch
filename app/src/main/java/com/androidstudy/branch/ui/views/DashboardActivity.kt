package com.androidstudy.branch.ui.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.branch.Branch
import com.androidstudy.branch.R
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.ui.adapter.CustomItemClickListener
import com.androidstudy.branch.ui.adapter.ThreadRecyclerViewAdapter
import com.androidstudy.branch.ui.viewmodel.StockMessageViewModel
import com.androidstudy.branch.ui.viewmodel.ThreadViewModel
import com.androidstudy.branch.util.livedata.nonNull
import com.androidstudy.branch.util.livedata.observe
import kotlinx.android.synthetic.main.content_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val threadViewModel: ThreadViewModel by viewModel()
    private val stockMessageViewModel: StockMessageViewModel by viewModel()
    private lateinit var app: Branch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setUp()

        imageViewUserAvatar.setOnClickListener {

        }

        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorAccent,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        if (app.settings.isFirstTime()!!) {
            threadViewModel.getMessageThreads()
            stockMessageViewModel.getStockMessageThreads()
        }

        observeFirstSyncLiveData()

        threadViewModel.fetchThreads().observe(this, Observer {
            setUpViews(it)
        })
    }

    private fun setUp() {
        app = application as Branch
    }

    private fun setUpViews(messageThreadList: List<MessageThread>?) {
        swipeRefreshLayout.isRefreshing = false
        if (messageThreadList.isNullOrEmpty()) {
            recyclerView.visibility = View.GONE
        } else {
            recyclerView.visibility = View.VISIBLE

            val itemDecor =
                DividerItemDecoration(this, LinearLayout.VERTICAL)
            val layoutManager =
                LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(itemDecor)

            val customerAdapter = ThreadRecyclerViewAdapter(messageThreadList, this, object :
                CustomItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    val messageThread = messageThreadList[position]

                    val intent = Intent(this@DashboardActivity, ChatActivity::class.java)
                    intent.putExtra("thread_id", messageThread.thread_id.toString())
                    intent.putExtra("user_id", messageThread.user_id)
                    startActivity(intent)
                }
            })

            recyclerView.adapter = customerAdapter
        }
    }

    private fun observeFirstSyncLiveData() {
        threadViewModel.getThreadsResponse().nonNull().observe(this) { list ->
            if (list.isNotEmpty()) {
                swipeRefreshLayout.isRefreshing = false
                app.settings.setIsFirstTime(false)

                threadViewModel.fetchThreads().observe(this, Observer {
                    setUpViews(it)
                })
            }
        }
        threadViewModel.getThreadsError().nonNull().observe(this) {
            swipeRefreshLayout.isRefreshing = false
            app.settings.setIsFirstTime(true)

            threadViewModel.fetchThreads().observe(this, Observer {
                setUpViews(it)
            })
        }

        stockMessageViewModel.getStockMessageResponse().nonNull().observe(this) { list ->
            if (list.isNotEmpty()) {
                swipeRefreshLayout.isRefreshing = false
                app.settings.setIsFirstTime(false)
            }
        }
        stockMessageViewModel.getStockMessageError().nonNull().observe(this) {
            swipeRefreshLayout.isRefreshing = false
            app.settings.setIsFirstTime(true)
        }
    }

    private fun logout() {
        if (app.settings.isLoggedIn()!!) {
            return
        }
    }

    override fun onResume() {
        super.onResume()

        if (app.settings.isFirstTime()!!) {
            threadViewModel.getMessageThreads()
        }
        threadViewModel.fetchThreads().observe(this, Observer {
            setUpViews(it)
        })
    }
}
