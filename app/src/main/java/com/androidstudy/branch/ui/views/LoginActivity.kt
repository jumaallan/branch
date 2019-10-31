package com.androidstudy.branch.ui.views

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.androidstudy.branch.Branch
import com.androidstudy.branch.R
import com.androidstudy.branch.data.model.UserLogin
import com.androidstudy.branch.data.model.UserResponse
import com.androidstudy.branch.data.remote.BranchAPI
import com.androidstudy.branch.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private val retrofit: Retrofit by inject()
    private var branchInterface: BranchAPI? = null
    private lateinit var app: Branch
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_login)

        setUp()
    }

    private fun setUp() {
        app = application as Branch
        branchInterface = retrofit.create(BranchAPI::class.java)
    }

    fun validateInput(view: View) {

        val username = etPhoneNumber.text.toString().trim()
        val pin = etPin.text.toString().trim()

        if (username.isEmpty()) {
            etPhoneNumber.error = "Username is required"
            etPhoneNumber.requestFocus()
            return
        }

        if (pin.isEmpty()) {
            etPin.error = "PIN is required"
            etPin.requestFocus()
            return
        }

        loginUser(username, pin)
    }

    private fun loginUser(username: String, pin: String) {

        progressDialog = ProgressDialog(this)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val user = UserLogin(username, pin)
        val call = branchInterface?.loginUser(user)
        call?.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                progressDialog.dismiss()
                if (response.isSuccessful) {

                    assert(response.body() != null)
                    val userResponse = response.body()

                    app.settings.setIsLoggedIn(true)
                    app.settings.setBranchAuthToken(userResponse!!.auth_token)

                    startActivity(Intent(applicationContext, DashboardActivity::class.java))
                } else {
                    toast("An error occurred")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                progressDialog.dismiss()
                toast("Server error occurred")
                Timber.d(t.localizedMessage)
            }
        })
    }
}
