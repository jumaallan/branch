package com.androidstudy.branch.ui.views

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.androidstudy.branch.R
import java.util.*

class SignOutDialog : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_sign_out, null, false)


        return MaterialDialog.Builder(Objects.requireNonNull<FragmentActivity>(activity))
            .customView(view, false)
            .positiveText("Sign out")
            .onPositive(callback)
            .build()
    }

    companion object {

        private lateinit var callback: MaterialDialog.SingleButtonCallback

        fun newInstance(buttonCallback: MaterialDialog.SingleButtonCallback): SignOutDialog {
            callback = buttonCallback
            return SignOutDialog()
        }

    }
}