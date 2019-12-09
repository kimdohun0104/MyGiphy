package com.dsm.mygiphy.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.presentation.base.BaseViewModel

class SearchViewModel(
    private val gifDao: GifDao
) : BaseViewModel() {

    val historyItems = MutableLiveData<List<String>>()

    fun getSearchHistory() {
        historyItems.value = gifDao.getSearchHistoryList().map { it.search }
    }
}