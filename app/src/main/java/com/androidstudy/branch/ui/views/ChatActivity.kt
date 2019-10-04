package com.androidstudy.branch.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.branch.R
import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.ui.adapter.ChatRecyclerViewAdapter
import com.androidstudy.branch.ui.adapter.CustomItemClickListener
import com.androidstudy.branch.ui.adapter.StockMessageRecyclerViewAdapter
import com.androidstudy.branch.ui.model.StockMessageDemo
import kotlinx.android.synthetic.main.chat_toolbar.*
import kotlinx.android.synthetic.main.content_chat.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerViewAdapter: ChatRecyclerViewAdapter
    private lateinit var stockMessageRecyclerViewAdapter: StockMessageRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        tvToolBarTitle.text = "Juma Allan"

        setupDemoChat(prepareDemoChats())
        setupDemoStock(prepareDemoStockMessages())

    }

    private fun setupDemoStock(stockMessagesList: List<StockMessageDemo>) {

        stockMessageRecyclerViewAdapter =
            StockMessageRecyclerViewAdapter(stockMessagesList, object :
                CustomItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    val stockMessage = stockMessagesList[position]
                    editTextMessageBody.setText(stockMessage.description)
                }
            })

        recyclerViewStockMessage.apply {
            adapter = stockMessageRecyclerViewAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupDemoChat(chatList: List<Chat>) {

        chatRecyclerViewAdapter =
            ChatRecyclerViewAdapter(chatList)
        recyclerViewChat.apply {
            adapter = chatRecyclerViewAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun prepareDemoStockMessages(): List<StockMessageDemo> {
        val models = ArrayList<StockMessageDemo>(5)
        models.add(
            StockMessageDemo(
                "Account: Delete My Account",
                "We are sorry that things did not work out this time but we recommend that you uninstall the app as we close your account. You can always email us on kenya@branch.co should you require assistance."
            )
        )
        models.add(
            StockMessageDemo(
                "Account: Disb. Reversed",
                "We have reversed the amount and updated your account. On the last page, before you submit your next application, select the correct account number from the drop down list and then click 'Apply'. Please be sure to do this to prevent the same issue from happening the next time you make a loan application."
            )
        )
        models.add(
            StockMessageDemo(
                "Account: FB Forgot Password",
                "You can log back into the Facebook account whose password you forgot. At the bottom of the Facebook login screen, there is a 'Forgot Password' option. Kindly click on this so as to reset the password and then access your established Branch account."
            )
        )
        models.add(
            StockMessageDemo(
                "Account: ID In Use Doesn't Know Person",
                "Unfortunately, since your ID number is in use in that other account, you cannot use it in this Branch account you created,and neither can it be used to access future loans at Branch. We have blocked the other account from accessing loans using your ID number. Apologies for this inconvenience."
            )
        )
        models.add(
            StockMessageDemo(
                "Account: Manage Account",
                "To register your mobile money number, go to 'My Account', click 'Edit' next to 'Primary Account', then click on 'Add Account'. Enter the phone number, click 'Validate' and then click 'Continue'. You will receive an SMS with a 4 digit code which should automatically validate your number. If this does not happen, kindly enter the code you received via SMS received on the app to complete validation. Read more here: branch://faq/my_account/new_mobile_money_number"
            )
        )
        return models
    }

    private fun prepareDemoChats(): List<Chat> {
        val models = ArrayList<Chat>(4)
        models.add(
            Chat(
                1,
                23,
                "2884",
                "I hv my transaction messages with me y am i not approved to this time? I urgently need the cash",
                "2019-10-03T04:57:17.943Z",
                "status_open",
                "23"
            )
        )
        models.add(
            Chat(
                1,
                23,
                "1092",
                "My number is 0790898526 help me to validate it please so i can be able to access the loan",
                "2017-02-01T10:49:28.000Z",
                "status_closed",
                ""
            )
        )
        models.add(
            Chat(
                1,
                23,
                "6094",
                "Hi, l have paid my loan on time but, my loan has been rejected. Why has it been rejected?",
                "2017-02-01T10:49:28.000Z",
                "status_open",
                "32"
            )
        )
        models.add(
            Chat(
                1,
                23,
                "2035",
                "Hi,sorry for the short text however Someone used my I.D and did register a line and took mshwari loan but venye nili realize nilipigia safaricom customer care and i did the payment  and cleared a bill of 299now i dont have  any what is the way forward.",
                "2017-02-01T10:49:28.000Z",
                "status_open",
                ""
            )
        )
        return models
    }
}
