package com.androidstudy.branch.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.branch.R
import com.androidstudy.branch.data.entities.Message
import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.ui.adapter.CustomItemClickListener
import com.androidstudy.branch.ui.adapter.StockMessageRecyclerViewAdapter
import com.androidstudy.branch.ui.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.chat_toolbar.*
import kotlinx.android.synthetic.main.content_chat.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity : AppCompatActivity() {

    private val vm: ChatViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        tvToolBarTitle.text = "Juma Allan"

        vm.fetchMessagesPerThread("").observe(this, Observer {
            setUpViews(it)
        })

        vm.fetchStockMessages().observe(this, Observer {
            setUpStockMessageViews(it)
        })

    }

    private fun setUpViews(messageList: List<Message>?) {
        if (messageList.isNullOrEmpty()) {
            recyclerViewChat.visibility = View.GONE
        } else {
//            recyclerViewChat.visibility = View.VISIBLE
//
//            val itemDecor =
//                DividerItemDecoration(this, LinearLayout.VERTICAL)
//            val layoutManager =
//                LinearLayoutManager(
//                    this,
//                    LinearLayoutManager.VERTICAL,
//                    false
//                )
//            recyclerViewChat.layoutManager = layoutManager
//            recyclerViewChat.addItemDecoration(itemDecor)
//
//            val customerAdapter = ChatRecyclerViewAdapter(messageList)
//            recyclerViewChat.adapter = customerAdapter
        }
    }

    private fun setUpStockMessageViews(stockMessageList: List<StockMessage>?) {
        if (stockMessageList.isNullOrEmpty()) {
            recyclerViewStockMessage.visibility = View.GONE
        } else {

            val stockMessageRecyclerViewAdapter =
                StockMessageRecyclerViewAdapter(stockMessageList, object :
                    CustomItemClickListener {
                    override fun onItemClick(v: View, position: Int) {
                        val stockMessage = stockMessageList[position]
                        editTextMessageBody.setText(stockMessage.description)
                    }
                })

            recyclerViewStockMessage.apply {
                adapter = stockMessageRecyclerViewAdapter
                layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

}
