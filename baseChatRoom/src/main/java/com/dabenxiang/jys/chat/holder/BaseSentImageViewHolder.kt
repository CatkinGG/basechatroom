package com.dabenxiang.jys.chat.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.dabenxiang.jys.basechat.R
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.widget.utility.GeneralUtils
import com.dabenxiang.jys.view.exchange.chat.base.style.MessagesListStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Base view holder for outcoming message
 */
open class BaseSentImageViewHolder(itemView: View) : MessageViewHolder(itemView),
    DefaultMessageViewHolder {
    protected val tvTime: TextView? = itemView.findViewById(R.id.tv_time)
    protected val iv1: ImageView = itemView.findViewById(R.id.imageView1)
    protected val iv2: ImageView = itemView.findViewById(R.id.imageView2)
    protected val tvPicNum: TextView = itemView.findViewById(R.id.tv_pic_num)
    protected val cardView: CardView = itemView.findViewById(R.id.cardView)
    protected val BITMAPS_SEPARATOR = "&"

    override fun onBind(item: ChatMessage, contactRecordFuncItem: ChatContentFuncItem) {
        tvTime?.text =
                GeneralUtils.convertTimestampToString(item.createdAt.toString().toLongOrNull() ?: 0)

        item.getImageUrl()?.takeIf { it.contains(BITMAPS_SEPARATOR) }?.run {
            val bitmapStrings = this.split(BITMAPS_SEPARATOR) as ArrayList<String>
            loadImage(bitmapStrings[0], iv1)
            loadImage(bitmapStrings[1], iv2)
            iv2.visibility = View.VISIBLE

            tvPicNum.text = StringBuffer().append("+ ").append(bitmapStrings.size - 1)
            tvPicNum.visibility = View.VISIBLE
        } ?: run {
            loadImage(item.message, iv1)
            iv2.visibility = View.GONE
            tvPicNum.visibility = View.GONE
        }

        cardView.setOnClickListener { contactRecordFuncItem.onPictureClick.invoke(item) }
    }

    override fun applyStyle(style: MessagesListStyle?) {

    }

    private fun loadImage(src: String, imageView: ImageView) {
        CoroutineScope(Dispatchers.Main).launch {
            flowOf(GeneralUtils.decodeBase64Image(src))
                .flowOn(Dispatchers.Default)
                .collect {
                    Glide.with(imageView.context).load(it).into(imageView)
                }
        }
    }
}

