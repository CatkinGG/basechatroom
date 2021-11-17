package com.dabenxiang.jys.basechatroom.ui.main.holder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dabenxiang.jys.basechatroom.R
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.holder.BaseSentImageViewHolder


class SentPicViewHolder(itemView: View) : BaseSentImageViewHolder(itemView) {
    override fun onBind(item: ChatMessage, chatContentFuncItem: ChatContentFuncItem) {
        super.onBind(item, chatContentFuncItem)
    }

    override fun loadImage(src: String, imageView: ImageView) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(imageView.context).load(Uri.parse(src)).apply(options).into(imageView)
    }
}