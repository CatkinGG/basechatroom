package com.dabenxiang.jys.basechatroom.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.dabenxiang.jys.basechatroom.R
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import com.dabenxiang.jys.basechatroom.ui.main.contactpicture.ContactPictureDialogFragment
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
        btnSend.setOnClickListener {
            (it as ImageView).run {
                val chatRoomMessage = ChatRoomMessage(-1,1,editChat.text.toString(),-1, ChatMessageType.TEXT, false)
                viewModel.addMessage(chatRoomMessage)
                this.setImageResource(R.drawable.btn_send_disable)
                this.isEnabled = false
            }
        }
    }

    fun setupObserver() {
        viewModel.pagingDataViewStates.observe(viewLifecycleOwner) {
            chatContentAdapter.submitData(viewLifecycleOwner.lifecycle, it as PagingData<ChatMessage>)
        }

        viewModel.addMessageListResult.observe(viewLifecycleOwner) {
            btnSend.run {
                this.setImageResource(R.drawable.btn_send_n)
                this.isEnabled = true
                editChat.text.clear()
            }
        }
    }

    fun setupUI() {
//        viewModel.insert(
//            ChatMsg(1,2,"TEST",202110281700, ChatMessageType.TEXT, true),
//            ChatMsg(2,2,"u catkingg?",202110281700, ChatMessageType.TEXT, true),
//            ChatMsg(3,2,"hihi",202111031900, ChatMessageType.TEXT, true),
//            ChatMsg(4,0,"catkingg is me",202111032000, ChatMessageType.TEXT, true),
//            ChatMsg(5,0,"1+1 = 2",202111171700, ChatMessageType.TEXT, true),
//            ChatMsg(6,0,"2+2 = 4",202111171700, ChatMessageType.TEXT, true),
//            ChatMsg(7,0,"3+3 = ?",202111171900, ChatMessageType.TEXT, true),
//            ChatMsg(8,2,"66666666",202111172000, ChatMessageType.TEXT, true),
//            ChatMsg(9,2,"77777777",202111173000, ChatMessageType.TEXT, true),
//            ChatMsg(10,2,"88888888",202111174000, ChatMessageType.TEXT, true),
//            ChatMsg(11,2,"99999999",202111175000, ChatMessageType.TEXT, true),
//            ChatMsg(12,0,"滾!",202111176000, ChatMessageType.TEXT, true),
//            ChatMsg(13,0,"https://truth.bahamut.com.tw/s01/202004/1dea58fbfef9d160a3fea574f51070a3.JPG" ,202111177000, ChatMessageType.PICTURE, true),
//            ChatMsg(14,2,"https://s.yimg.com/ny/api/res/1.2/nIrraUXNlV4Oyhhdj024rQ--/YXBwaWQ9aGlnaGxhbmRlcjt3PTY0MDtoPTM2MA--/https://s.yimg.com/uu/api/res/1.2/_d3IeF4GgXJxVGyDybiy9w--~B/aD02NzU7dz0xMjAwO2FwcGlkPXl0YWNoeW9u/https://media.zenfs.com/zh-tw/nownews.com/abe3b6228c4481b2bb895027e93407cb",202111178000, ChatMessageType.PICTURE, true)
//        )
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
        val msg = ChatMsg(
            chatMsg.id,
            chatMsg.userID,
            chatMsg.message,
            chatMsg.createdAt.toString().toLong(),
            if(chatMsg.getImageByteArray() != null) ChatMessageType.PICTURE else ChatMessageType.TEXT,
            true
        )
        ContactPictureDialogFragment(msg).show(
            requireActivity().supportFragmentManager,
            this::class.java.simpleName
        )
    }
}