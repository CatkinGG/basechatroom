package com.dabenxiang.jys.basechatroom.ui.main.contactpicture

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dabenxiang.jys.basechatroom.R
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import kotlinx.android.synthetic.main.item_picture.view.*

class PictureAdapter(val msg: ChatMsg): RecyclerView.Adapter<PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false))
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.onBind(msg.message?.split("&&")?.get(position) ?: (msg.message?:""))
    }

    override fun getItemCount() = msg.message?.split("&&")?.size ?: 1
}

class PictureViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val ivPic = itemView.iv_pic
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
    fun onBind(res: String) {
        Glide.with(ivPic.context).load(Uri.parse(res)).apply(options).into(ivPic)
    }
}