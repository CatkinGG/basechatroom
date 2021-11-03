package com.dabenxiang.jys.basechatroom.ui.main.holder

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.holder.BaseReceivedTextViewHolder
import com.dabenxiang.jys.chat.view.custom.ChatIconDrawable
import kotlinx.android.synthetic.main.item_chat_received_text.view.*

class ReceivedTextViewHolder(itemView: View, private val nickName: String): BaseReceivedTextViewHolder(itemView) {

    private val clRoot: ConstraintLayout = itemView.cl_root

    override fun onBind(item: ChatMessage, contactRecordFuncItem: ChatContentFuncItem) {
        super.onBind(item, contactRecordFuncItem)
        ivAvatar?.setImageDrawable(ChatIconDrawable(ivAvatar, nickName.first().toString()))
        resetConstrain()
    }

    private fun resetConstrain() {
        val constraintSet = ConstraintSet()
        tvContent?.let{
            constraintSet.clone(clRoot)
            constraintSet.constrainMaxWidth(it.id, ConstraintSet.WRAP_CONTENT)
            constraintSet.constrainPercentWidth(it.id, 0.5f)
            constraintSet.connect(it.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            constraintSet.applyTo(clRoot)
        }
    }

}