package com.dsm.mygiphy.presentation.ui.search

import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import com.dsm.mygiphy.presentation.base.BaseViewModel

class SearchViewModel(
    private val gifDao: GifDao
) : BaseViewModel() {

    fun getSearchHistory() = gifDao.getSearchHistoryList()

    fun deleteSearchHistory(value: String) =
        addDisposable(gifDao.deleteSearchHistory(SearchHistoryRoomEntity(value)).subscribe())
}