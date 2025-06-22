package com.solbao.fetch_rewards.data.models

import kotlinx.serialization.Serializable


@Serializable
data class ResponseItem(
    val id: Int,
    val listId: Int,
    val name: String?
)
