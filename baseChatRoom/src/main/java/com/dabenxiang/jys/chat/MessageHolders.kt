package com.dabenxiang.jys.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.dabenxiang.jys.basechat.R
import com.dabenxiang.jys.chat.config.ContentTypeConfig
import com.dabenxiang.jys.chat.config.HolderConfig
import com.dabenxiang.jys.chat.holder.*
import com.dabenxiang.jys.view.exchange.chat.base.style.MessagesListStyle
import java.lang.reflect.Constructor
import java.util.*

/*
* Created by troy379 on 31.03.17.
*/
class MessageHolders {
    private var dateHeaderHolder: Class<out MessageViewHolder>
    private var dateHeaderLayout: Int = -1
    private var receivedTextConfig: HolderConfig
    private var sentTextConfig: HolderConfig
    private var receivedImageConfig: HolderConfig
    private var sentImageConfig: HolderConfig
    private val customContentTypes: MutableList<ContentTypeConfig> = ArrayList()
    private var hasContentFor: ((ChatMessage, Int) -> Boolean) = { _, _-> false}

    companion object {
        const val VIEW_TYPE_DATE_HEADER: Int = 130
        const val VIEW_TYPE_TEXT_MESSAGE: Int = 131
        const val VIEW_TYPE_IMAGE_MESSAGE: Int = 132
    }

    init {
        dateHeaderHolder = BaseNotificationViewHolder::class.java
        dateHeaderLayout = R.layout.item_chat_notification

        receivedTextConfig = HolderConfig(BaseReceivedTextViewHolder::class.java, R.layout.item_chat_received_text)
        sentTextConfig = HolderConfig(BaseSentTextViewHolder::class.java, R.layout.item_chat_sent_text)
        receivedImageConfig = HolderConfig(BaseReceivedImageViewHolder::class.java, R.layout.item_chat_received_pic)
        sentImageConfig = HolderConfig(BaseSentImageViewHolder::class.java, R.layout.item_chat_sent_pic)
    }

    /**
     * Sets both of custom view holder class and layout resource for received text message.
     *
     * @param holder holder class.
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedTextConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int): MessageHolders {
        receivedTextConfig.holder = holder
        receivedTextConfig.layout = layout
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for received text message.
     *
     * @param holder  holder class.
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedTextConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int,
        payload: Array<Any>): MessageHolders {
        receivedTextConfig.holder = holder
        receivedTextConfig.layout = layout
        receivedTextConfig.payload = payload
        payload.forEach {
            receivedTextConfig.argTypes = receivedTextConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom view holder class for received text message.
     *
     * @param holder holder class.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedTextHolder(
            holder: Class<out MessageViewHolder>): MessageHolders {
        receivedTextConfig.holder = holder
        return this
    }

    /**
     * Sets custom view holder class for received text message.
     *
     * @param holder  holder class.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedTextHolder(
        holder: Class<out MessageViewHolder>,
        payload: Array<Any>): MessageHolders {
        receivedTextConfig.holder = holder
        receivedTextConfig.payload = payload
        payload.forEach {
            receivedTextConfig.argTypes = receivedTextConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom layout resource for received text message.
     *
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedTextLayout(@LayoutRes layout: Int): MessageHolders {
        receivedTextConfig.layout = layout
        return this
    }

    /**
     * Sets custom layout resource for received text message.
     *
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedTextLayout(@LayoutRes layout: Int, payload: Array<Any>): MessageHolders {
        receivedTextConfig.layout = layout
        receivedTextConfig.payload = payload
        payload.forEach {
            receivedTextConfig.argTypes = receivedTextConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for sent text message.
     *
     * @param holder holder class.
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentTextConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int): MessageHolders {
        sentTextConfig.holder = holder
        sentTextConfig.layout = layout
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for sent text message.
     *
     * @param holder  holder class.
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentTextConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int,
        payload: Array<Any>): MessageHolders {
        sentTextConfig.holder = holder
        sentTextConfig.layout = layout
        sentTextConfig.payload = payload
        payload.forEach {
            sentTextConfig.argTypes = sentTextConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom view holder class for sent text message.
     *
     * @param holder holder class.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentTextHolder(
            holder: Class<out MessageViewHolder>): MessageHolders {
        sentTextConfig.holder = holder
        return this
    }

    /**
     * Sets custom view holder class for sent text message.
     *
     * @param holder  holder class.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentTextHolder(
        holder: Class<out MessageViewHolder>,
        payload: Array<Any>): MessageHolders {
        sentTextConfig.holder = holder
        sentTextConfig.payload = payload
        payload.forEach {
            sentTextConfig.argTypes = sentTextConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom layout resource for sent text message.
     *
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentTextLayout(@LayoutRes layout: Int): MessageHolders {
        sentTextConfig.layout = layout
        return this
    }

    /**
     * Sets custom layout resource for sent text message.
     *
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentTextLayout(@LayoutRes layout: Int, payload: Array<Any>): MessageHolders {
        sentTextConfig.layout = layout
        sentTextConfig.payload = payload
        payload.forEach {
            sentTextConfig.argTypes = sentTextConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for received image message.
     *
     * @param holder holder class.
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedImageConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int): MessageHolders {
        receivedImageConfig.holder = holder
        receivedImageConfig.layout = layout
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for received image message.
     *
     * @param holder  holder class.
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedImageConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int,
        payload: Array<Any>): MessageHolders {
        receivedImageConfig.holder = holder
        receivedImageConfig.layout = layout
        receivedImageConfig.payload = payload
        payload.forEach {
            receivedImageConfig.argTypes = receivedImageConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom view holder class for received image message.
     *
     * @param holder holder class.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedImageHolder(
            holder: Class<out MessageViewHolder>): MessageHolders {
        receivedImageConfig.holder = holder
        return this
    }

    /**
     * Sets custom view holder class for received image message.
     *
     * @param holder  holder class.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedImageHolder(
        holder: Class<out MessageViewHolder>,
        payload: Array<Any>): MessageHolders {
        receivedImageConfig.holder = holder
        receivedImageConfig.payload = payload
        payload.forEach {
            receivedImageConfig.argTypes = receivedImageConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom layout resource for received image message.
     *
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedImageLayout(@LayoutRes layout: Int): MessageHolders {
        receivedImageConfig.layout = layout
        return this
    }

    /**
     * Sets custom layout resource for received image message.
     *
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setReceivedImageLayout(@LayoutRes layout: Int, payload: Array<Any>): MessageHolders {
        receivedImageConfig.layout = layout
        receivedImageConfig.payload = payload
        payload.forEach {
            receivedImageConfig.argTypes = receivedImageConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for sent image message.
     *
     * @param holder holder class.
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentImageConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int): MessageHolders {
        sentImageConfig.holder = holder
        sentImageConfig.layout = layout
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for sent image message.
     *
     * @param holder  holder class.
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentImageConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int,
        payload: Array<Any>): MessageHolders {
        sentImageConfig.holder = holder
        sentImageConfig.layout = layout
        sentImageConfig.payload = payload
        payload.forEach {
            sentImageConfig.argTypes = sentImageConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom view holder class for sent image message.
     *
     * @param holder holder class.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentImageHolder(
            holder: Class<out MessageViewHolder>): MessageHolders {
        sentImageConfig.holder = holder
        return this
    }

    /**
     * Sets custom view holder class for sent image message.
     *
     * @param holder  holder class.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentImageHolder(
        holder: Class<out MessageViewHolder>,
        payload: Array<Any>): MessageHolders {
        sentImageConfig.holder = holder
        sentImageConfig.payload = payload
        payload.forEach {
            sentImageConfig.argTypes = sentImageConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets custom layout resource for sent image message.
     *
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentImageLayout(@LayoutRes layout: Int): MessageHolders {
        sentImageConfig.layout = layout
        return this
    }

    /**
     * Sets custom layout resource for sent image message.
     *
     * @param layout  layout resource.
     * @param payload custom data.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setSentImageLayout(@LayoutRes layout: Int, payload: Array<Any>): MessageHolders {
        sentImageConfig.layout = layout
        sentImageConfig.payload = payload
        payload.forEach {
            sentImageConfig.argTypes = sentImageConfig.argTypes.plus(it.javaClass)
        }
        return this
    }

    /**
     * Sets both of custom view holder class and layout resource for date header.
     *
     * @param holder holder class.
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setDateHeaderConfig(
        holder: Class<out MessageViewHolder>,
        @LayoutRes layout: Int): MessageHolders {
        dateHeaderHolder = holder
        dateHeaderLayout = layout
        return this
    }

    /**
     * Sets custom view holder class for date header.
     *
     * @param holder holder class.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setDateHeaderHolder(holder: Class<MessageViewHolder>): MessageHolders {
        dateHeaderHolder = holder
        return this
    }

    /**
     * Sets custom layout reource for date header.
     *
     * @param layout layout resource.
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun setDateHeaderLayout(@LayoutRes layout: Int): MessageHolders {
        dateHeaderLayout = layout
        return this
    }

    /**
     * Registers custom content type (e.g. multimedia, events etc.)
     *
     * @param type            unique id for content type
     * @param holder          holder class for received and sent messages
     * @param receivedLayout  layout resource for received message
     * @param sentLayout layout resource for sent message
     * @param contentChecker  [ContentChecker] for registered type
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun registerContentType(
        type: Int, holder: Class<out MessageViewHolder>,
        @LayoutRes receivedLayout: Int,
        @LayoutRes sentLayout: Int,
        hasContentFor: ((ChatMessage, Int) -> Boolean)
    ){
       registerContentType(type,
               holder, receivedLayout,
               holder, sentLayout,
               hasContentFor)
    }

    /**
     * Registers custom content type (e.g. multimedia, events etc.)
     *
     * @param type            unique id for content type
     * @param receivedHolder  holder class for received message
     * @param sentHolder holder class for sent message
     * @param receivedLayout  layout resource for received message
     * @param sentLayout layout resource for sent message
     * @param contentChecker  [ContentChecker] for registered type
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun registerContentType(
        type: Int,
        receivedHolder: Class<out MessageViewHolder>, @LayoutRes receivedLayout: Int,
        sentHolder: Class<out MessageViewHolder>, @LayoutRes sentLayout: Int,
        hasContentFor: ((ChatMessage, Int) -> Boolean)
    ){
        require(type.toInt() != 0) { "content type must be greater or less than '0'!" }
        customContentTypes.add(
                ContentTypeConfig(type,
                        HolderConfig(receivedHolder, receivedLayout),
                        HolderConfig(sentHolder, sentLayout)
                )
        )
        this.hasContentFor = hasContentFor
    }

    /**
     * Registers custom content type (e.g. multimedia, events etc.)
     *
     * @param type             unique id for content type
     * @param receivedHolder   holder class for received message
     * @param sentHolder  holder class for sent message
     * @param receivedPayload  payload for received message
     * @param sentPayload payload for sent message
     * @param receivedLayout   layout resource for received message
     * @param sentLayout  layout resource for sent message
     * @param contentChecker   [com.stfalcon.chatkit.messages.MessageHolders.ContentChecker] for registered type
     * @return [com.stfalcon.chatkit.messages.MessageHolders] for subsequent configuration.
     */
    fun registerContentType(
        type: Int,
        receivedHolder: Class<out MessageViewHolder>,
        receivedArgTypes: Array<Class<*>>,
        receivedPayload: Array<Any>,
        @LayoutRes receivedLayout: Int,
        sentHolder: Class<out MessageViewHolder>,
        sentArgTypes: Array<Class<*>>,
        sentPayload: Array<Any>,
        @LayoutRes sentLayout: Int,
        hasContentFor: ((ChatMessage, Int) -> Boolean)
    ) {
        require(type != 0) { "content type must be greater or less than '0'!" }
        customContentTypes.add(
                ContentTypeConfig(type,
                        HolderConfig(receivedHolder, receivedLayout, receivedArgTypes, receivedPayload),
                        HolderConfig(sentHolder, sentLayout, sentArgTypes,sentPayload)
                )
        )
        this.hasContentFor = hasContentFor
    }

    /*
     * PRIVATE METHODS: negative number is from me
     * */
    fun getHolder(parent: ViewGroup, viewType: Int, messagesListStyle: MessagesListStyle?): MessageViewHolder {
        when (viewType) {
            VIEW_TYPE_DATE_HEADER -> return getHolder(parent, dateHeaderLayout, dateHeaderHolder, messagesListStyle)
            VIEW_TYPE_TEXT_MESSAGE -> return getHolder(parent, receivedTextConfig, messagesListStyle)
            -VIEW_TYPE_TEXT_MESSAGE -> return getHolder(parent, sentTextConfig, messagesListStyle)
            VIEW_TYPE_IMAGE_MESSAGE -> return getHolder(parent, receivedImageConfig, messagesListStyle)
            -VIEW_TYPE_IMAGE_MESSAGE -> return getHolder(parent, sentImageConfig, messagesListStyle)
            else -> for (typeConfig in customContentTypes) {
                if (Math.abs(typeConfig.type) == Math.abs(viewType)) {
                    return if (viewType > 0)
                        getHolder(parent, typeConfig.receivedConfig, messagesListStyle)
                    else
                        getHolder(parent, typeConfig.sentConfig, messagesListStyle)
                }
            }
        }
        throw IllegalStateException("Wrong message view type. Please, report this issue on GitHub with full stacktrace in description.")
    }

    protected fun bind(holder: MessageViewHolder, item: ChatMessage, chatContentFuncItem: ChatContentFuncItem) {
        holder.onBind(item, chatContentFuncItem)
    }

    fun getViewType(item: ChatMessage, userID: Int): Int {
        val isMe = item.userID == userID
        val viewType = getContentViewType(item)
        return if (isMe) viewType * -1 else viewType
    }

    private fun getHolder(parent: ViewGroup, holderConfig: HolderConfig,
                          style: MessagesListStyle?): MessageViewHolder {
        return getHolder(parent, holderConfig.layout, holderConfig.holder, style, holderConfig.argTypes, holderConfig.payload)
    }

    private fun <HOLDER : MessageViewHolder?>getHolder(parent: ViewGroup, @LayoutRes layout: Int, holderClass: Class<HOLDER>,
                                                       style: MessagesListStyle?, argTypes: Array<Class<*>> = arrayOf(), payload: Array<Any> = arrayOf()): HOLDER {
        val v: View = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false)
            var constructor: Constructor<HOLDER>? = null
            val holder: HOLDER
            if(payload.isEmpty()) {
                constructor = holderClass.getDeclaredConstructor(View::class.java)
                constructor!!.isAccessible = true
                holder = constructor.newInstance(v)
            } else {
                constructor = holderClass.getDeclaredConstructor(View::class.java, *argTypes)
                constructor!!.isAccessible = true
                holder = constructor.newInstance(v, *payload)
            }
            if (holder is DefaultMessageViewHolder && style != null) {
                (holder as DefaultMessageViewHolder).applyStyle(style)
            }
            return holder
    }

    fun getContentViewType(message: ChatMessage): Int {
        for (i in customContentTypes.indices) {
            val config = customContentTypes[i]
            val hasContent = hasContentFor(message, config.type)
            if (hasContent) return config.type
            }
        return if(message.getImageUrl() != null) VIEW_TYPE_IMAGE_MESSAGE
        else VIEW_TYPE_TEXT_MESSAGE
    }
}