package com.solbao.fetch_rewards.domain.usecases



interface UseCase<R>{
    suspend fun execute():R
}
