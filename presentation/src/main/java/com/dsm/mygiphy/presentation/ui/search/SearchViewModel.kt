package com.dsm.mygiphy.presentation.ui.search

import com.dsm.data.local.dao.SearchHistoryDao
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import com.dsm.mygiphy.presentation.base.BaseViewModel

class SearchViewModel(
    private val searchHistoryDao: SearchHistoryDao
) : BaseViewModel() {

    fun getSearchHistory() = searchHistoryDao.getSearchHistoryList()

    fun deleteSearchHistory(value: String) =
        addDisposable(searchHistoryDao.deleteSearchHistory(SearchHistoryRoomEntity(value)).subscribe())
}