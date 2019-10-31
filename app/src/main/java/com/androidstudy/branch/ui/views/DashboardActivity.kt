package com.androidstudy.branch.ui.views

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
import com.androidstudy.branch.ui.viewmodel.ThreadViewModel
import com.androidstudy.branch.util.livedata.nonNull
import com.androidstudy.branch.util.livedata.observe
import com.androidstudy.branch.util.toast
import kotlinx.android.synthetic.main.content_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val vm: ThreadViewModel by viewModel()
    private lateinit var app: Branch

    private var signOutDialog: SignOutDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setUp()

        imageViewUserAvatar.setOnClickListener {
            signOutDialog?.show(supportFragmentManager, "profile")
        }

        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorAccent,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        if (app.settings.isFirstTime()!!) {
            vm.getMessageThreads()
        }

        observeLiveData()

        vm.fetchThreads().observe(this, Observer {
            setUpViews(it)
        })

    }

    private fun setUp() {
        app = application as Branch
//        signOutDialog = SignOutDialog.newInstance({ dialog -> logout() })
    }

    private fun setUpViews(messageThreadList: List<MessageThread>?) {
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
                    toast(messageThread.body)
                }
            })
            recyclerView.adapter = customerAdapter
        }
    }

    private fun observeLiveData() {
        vm.getThreadsResponse().nonNull().observe(this) { list ->
            if (list.isNotEmpty()) {
                app.settings.setIsFirstTime(false)

                vm.fetchThreads().observe(this, Observer {
                    setUpViews(it)
                })
            }
        }
        vm.getThreadsError().nonNull().observe(this) {
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
            vm.getMessageThreads()
        }
        vm.fetchThreads().observe(this, Observer {
            setUpViews(it)
        })
    }
}
