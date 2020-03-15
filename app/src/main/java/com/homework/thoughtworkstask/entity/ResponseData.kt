package com.homework.thoughtworkstask.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class User(
    @SerializedName("profile-image") val profileImage: String,
    val nick: String, val avatar: String, val username: String
)

@Keep
data class TwitterItem(
    val error: String? = null,
    val content: String? = null, val images: MutableList<ImageItem>? = null,
    val sender: Sender, val comments: MutableList<CommentItem>? = null
)

@Keep
data class ImageItem(val url: String)

@Keep
data class Sender(val username: String, val nick: String, val avatar: String)

@Keep
data class CommentItem(val content: String, val sender: Sender)