package com.androidstudy.branch.ui.views

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.branch.R
import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.data.model.ChatMessage
import com.androidstudy.branch.ui.adapter.ChatRecyclerViewAdapter
import com.androidstudy.branch.ui.adapter.CustomItemClickListener
import com.androidstudy.branch.ui.adapter.StockMessageRecyclerViewAdapter
import com.androidstudy.branch.ui.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.chat_toolbar.*
import kotlinx.android.synthetic.main.content_chat.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ChatActivity : AppCompatActivity() {

    private val vm: ChatViewModel by viewModel()
    private lateinit var threadId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        threadId = intent.getStringExtra("thread_id")
        val userId = intent.getStringExtra("user_id")

        tvToolBarTitle.text = userId

        vm.fetchMessagesPerThread(threadId).observe(this, Observer {
            setUpViews(it)
        })

        vm.fetchStockMessages().observe(this, Observer {
            setUpStockMessageViews(it)
        })

        buttonSendMessage.setOnClickListener {
            validateMessage()
        }
    }

    private fun validateMessage() {
        val message = editTextMessageBody.text.toString().trim()

        if (message.isEmpty()) {
            editTextMessageBody.error = "Write a message"
            return
        }

        sendMessage(message)
    }

    private fun sendMessage(message: String) {
        vm.sendMessage(threadId, message)
    }

    private fun setUpViews(chatMessageList: List<ChatMessage>?) {
        if (chatMessageList.isNullOrEmpty()) {
            recyclerViewChat.visibility = View.GONE
        } else {

            Timber.d("Chats " + chatMessageList.size)
            chatMessageList.forEach {
                it.thread_id
            }
            recyclerViewChat.visibility = View.VISIBLE

            val itemDecor =
                DividerItemDecoration(this, LinearLayout.VERTICAL)
            val layoutManager =
                LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            recyclerViewChat.layoutManager = layoutManager
            recyclerViewChat.addItemDecoration(itemDecor)

            val customerAdapter = ChatRecyclerViewAdapter(chatMessageList)
            recyclerViewChat.adapter = customerAdapter
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
                        editTextMessageBody.setText(stockMessage.body)
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
