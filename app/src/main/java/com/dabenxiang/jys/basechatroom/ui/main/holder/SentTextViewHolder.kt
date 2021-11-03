package com.dabenxiang.jys.basechatroom.ui.main.holder

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.holder.BaseSentTextViewHolder
import kotlinx.android.synthetic.main.item_chat_sent_text.view.*

class SentTextViewHolder(itemView: View): BaseSentTextViewHolder(itemView) {
    private val clRoot: ConstraintLayout = itemView.cl_root

    override fun onBind(item: ChatMessage, chatContentFuncItem: ChatContentFuncItem) {
        super.onBind(item, chatContentFuncItem)
        resetConstrain()
    }

    private fun resetConstrain() {
        val constraintSet = ConstraintSet()
        tvContent?.let {
            constraintSet.clone(clRoot)
            constraintSet.constrainMaxWidth(it.id, ConstraintSet.WRAP_CONTENT)
            constraintSet.constrainPercentWidth(it.id, 0.5f)
            constraintSet.connect(it.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            constraintSet.connect(it.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            constraintSet.applyTo(clRoot)
        }
    }
}