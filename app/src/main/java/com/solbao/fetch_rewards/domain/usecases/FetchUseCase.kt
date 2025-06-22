package com.solbao.fetch_rewards.domain.usecases


import com.solbao.fetch_rewards.data.models.ResponseItem

import kotlinx.coroutines.withContext

import kotlin.coroutines.CoroutineContext

class FetchListUseCase(private val context: CoroutineContext):UseCase<List<ResponseItem>>{
    override suspend fun execute(): List<ResponseItem> = withContext(context) {
        val responseList = ApiService.instance?.list()?.execute()?.body()

        return@withContext responseList ?: emptyList()
    }
}
