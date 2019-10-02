package com.androidstudy.branch.ui.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.androidstudy.branch.Branch
import com.androidstudy.branch.R
import com.androidstudy.branch.ui.adapter.OnboardRecyclerViewAdapter
import com.androidstudy.branch.ui.model.OnboardModel
import com.androidstudy.branch.util.getSnapPosition
import kotlinx.android.synthetic.main.activity_onboard.*

class OnboardingActivity : AppCompatActivity() {

    private lateinit var app: Branch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_onboard)

        app = application as Branch
        setUpViews()
    }

    private fun setUpViews() {

        val layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_onboard.layoutManager = layoutManager

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recycler_view_onboard)

        val models = prepareOnboardingModels()

        val adapter = OnboardRecyclerViewAdapter(models)
        recycler_view_onboard.adapter = adapter

        indicator.attachToRecyclerView(recycler_view_onboard, snapHelper)
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        b_onboarding_next.setOnClickListener {
            (recycler_view_onboard.layoutManager as? LinearLayoutManager)?.scrollToPosition(
                snapHelper.getSnapPosition(
                    recycler_view_onboard
                ) + 1
            )
        }

        b_onboarding_previous.setOnClickListener {
            (recycler_view_onboard.layoutManager as? LinearLayoutManager)?.scrollToPosition(
                snapHelper.getSnapPosition(
                    recycler_view_onboard
                ) - 1
            )
        }
    }

    private fun prepareOnboardingModels(): List<OnboardModel> {
        val models = ArrayList<OnboardModel>(3)
        models.add(
            OnboardModel(
                getString(R.string.onboarding_support_title),
                getString(R.string.onboarding_support_description),
                R.drawable.ic_launcher_background
            )
        )
        models.add(
            OnboardModel(
                getString(R.string.onboarding_support_title),
                getString(R.string.onboarding_support_description),
                R.drawable.ic_launcher_background
            )
        )
        models.add(
            OnboardModel(
                getString(R.string.onboarding_support_title),
                getString(R.string.onboarding_support_description),
                R.drawable.ic_launcher_background
            )
        )
        return models
    }

    fun openLoginScreen(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onResume() {
        super.onResume()

        if (app.settings.isLoggedIn()!!) {
            val intent = Intent(applicationContext, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
