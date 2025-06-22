package com.solbao.fetch_rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solbao.fetch_rewards.data.models.ResponseItem
import com.solbao.fetch_rewards.domain.usecases.FetchListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val dataSetValue = MutableStateFlow(listOf<ResponseItem>())
    val dataSet get() = dataSetValue.asStateFlow()

    init {
        fetchList()
    }
    fun sort(){
        val list = dataSetValue.value
        val max = list.maxOf { it.listId }
        val newList = mutableListOf<List<ResponseItem>>()
        repeat(max){
            newList.add(mutableListOf<ResponseItem>())
        }
        for(item in list){
           val modList = newList[item.listId-1] as MutableList<ResponseItem>
            modList.add(item)
            newList[item.listId-1] = modList

        }
        dataSetValue.value = newList.flatMap{innerList ->
            innerList.sortedByDescending{ it.name?.split(" ")?.get(1)?.toInt() }

        }}

    private fun fetchList() = viewModelScope.launch(Dispatchers.IO) {
            val list = FetchListUseCase(Dispatchers.IO).execute()
            dataSetValue.value = list.filter { !it.name.isNullOrBlank() }

    }
}