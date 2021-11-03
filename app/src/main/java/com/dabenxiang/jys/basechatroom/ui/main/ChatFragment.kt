package com.dabenxiang.jys.basechatroom.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.dabenxiang.jys.basechatroom.R
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType
import com.dabenxiang.jys.basechatroom.ui.main.interfaces.InteractionListener
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chat_fragment.*

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.chat_fragment) {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private val viewModel: ChatViewModel by viewModels()
    var interactionListener: InteractionListener.Base? = null

    private val chatContentAdapter by lazy {
        val adapter = ChatContentAdapter(
            0,
            ChatContentFuncItem(
                onPictureClick = {
                    onPictureClick(it)
                }
            )
        )
        val loadStateListener = { loadStatus: CombinedLoadStates ->
            when (loadStatus.refresh) {
                is LoadState.Error -> {
                    interactionListener?.hideLoadingView()
                }
                is LoadState.Loading -> {
                    interactionListener?.showLoadingView()
                }
                is LoadState.NotLoading -> {
                    interactionListener?.hideLoadingView()

                    if(adapter.isForceRefresh){
                        scrollToBottom(adapter)
                        adapter.isForceRefresh = false
                    }
                }
            }

            when (loadStatus.append) {
                is LoadState.Error -> {
                }
                is LoadState.Loading -> {
                }
                is LoadState.NotLoading -> {
                }
            }
        }
        adapter.addLoadStateListener(loadStateListener)
        adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        setupListener()
    }

    fun setupListener() {

    }

    fun setupObserver() {
        viewModel.pagingDataViewStates.observe(viewLifecycleOwner) {
            chatContentAdapter.submitData(viewLifecycleOwner.lifecycle, it as PagingData<ChatMessage>)
        }
    }

    fun setupUI() {
        viewModel.insert(ChatMsg(1,2,"catkingg",202110281700, ChatMessageType.TEXT, true))
        rv_chat_content.also {
            it.setHasFixedSize(true)
            it.adapter = chatContentAdapter
        }
    }

    private fun scrollToBottom(chatContentAdapter: ChatContentAdapter) {
        chatContentAdapter.run {
            rv_chat_content.scrollToPosition(0)
        }
    }

    private fun onPictureClick(chatMsg: ChatMessage) {
//        val msg = Msg(
//            messages = if(chatMsg.message.contains(BITMAPS_SEPARATOR))
//                chatMsg.message.split(BITMAPS_SEPARATOR ) as ArrayList<String>
//            else arrayListOf(chatMsg.message)
//        )
//        ContactPictureDialogFragment(msg).show(
//            requireActivity().supportFragmentManager,
//            this::class.java.simpleName
//        )
    }
}