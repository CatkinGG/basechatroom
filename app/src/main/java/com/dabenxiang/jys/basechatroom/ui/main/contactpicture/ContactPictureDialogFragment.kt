package com.dabenxiang.jys.basechatroom.ui.main.contactpicture

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.dabenxiang.jys.basechatroom.R
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import kotlinx.android.synthetic.main.fragment_dialog_contact_picture.*

class ContactPictureDialogFragment(val msg: ChatMsg) : DialogFragment(R.layout.fragment_dialog_contact_picture) {
    fun isFullLayout() = false
    private val pictureAdapter by lazy { PictureAdapter(msg) }

    private var currentPos = 0

    override fun onStart() {
        super.onStart()
        if (isFullLayout()) {
            val window = dialog?.window
            window?.setWindowAnimations(R.style.DialogNoAnimation)
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        } else {
            val window = dialog?.window
            val widthPixels = (resources.displayMetrics.widthPixels * 0.8).toInt()
            window?.setLayout(widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private val onScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        currentPos =
                            (rv_picture.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                        when (currentPos) {
                            0 -> {
                                ib_left.visibility = View.GONE
                                if (msg.message?.contains("&&") == true)
                                    ib_right.visibility = View.VISIBLE
                            }
                            pictureAdapter.itemCount - 1 -> {
                                ib_right.visibility = View.GONE
                                if (msg.message?.contains("&&") == true)
                                    ib_left.visibility = View.VISIBLE
                            }
                            else -> {
                                ib_left.visibility = View.VISIBLE
                                ib_right.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        if (msg.message?.contains("&&") != true) {
            ib_left.visibility = View.GONE
            ib_right.visibility = View.GONE
        }
        rv_picture.adapter = pictureAdapter
        (rv_picture.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        PagerSnapHelper().attachToRecyclerView(rv_picture)
        rv_picture.addOnScrollListener(onScrollListener)

        ib_close.setOnClickListener { dismiss() }
        ib_left.setOnClickListener { rv_picture?.smoothScrollToPosition(currentPos - 1) }
        ib_right.setOnClickListener { rv_picture?.smoothScrollToPosition(currentPos + 1) }
    }

}