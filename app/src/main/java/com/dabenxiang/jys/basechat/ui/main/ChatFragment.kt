package com.dabenxiang.jys.basechat.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.dabenxiang.jys.basechat.R
import com.dabenxiang.jys.basechat.model.db.ChatMessage
import com.dabenxiang.jys.view.exchange.chat.enums.ChatMessageType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chat_fragment.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow
import timber.log.Timber

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.chat_fragment) {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        setupListener()
    }

    fun setupListener() {
        button.setOnClickListener {
            viewModel.read()
        }
    }

    fun setupObserver() {
        viewModel.readMessageDaoResult.observe(viewLifecycleOwner, {
            textView.text = it.first().message
        })
    }

    fun setupUI() {
        viewModel.insert(ChatMessage(1,2,"catkingg",202110281700, ChatMessageType.TEXT, true))
    }

}