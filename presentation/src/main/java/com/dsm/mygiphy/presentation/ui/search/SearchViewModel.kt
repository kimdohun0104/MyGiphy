package com.dsm.mygiphy.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dsm.data.local.dao.SearchHistoryDao
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.base.SingleLiveEvent

class SearchViewModel(
    private val searchHistoryDao: SearchHistoryDao
) : BaseViewModel() {

    val intentSearchResultEvent = SingleLiveEvent<String>()
    val finishActivityEvent = SingleLiveEvent<Unit>()

    fun getSearchHistory(): LiveData<List<String>> =
        Transformations.map(searchHistoryDao.getSearchHistoryList()) { it.map { item -> item.search }.reversed() }

    fun deleteSearchHistory(value: String) =
        addDisposable(searchHistoryDao.deleteSearchHistory(value).subscribe())

    fun onSearchGif(search: String) {
        if (search.isNotBlank()) {
            intentSearchResultEvent.value = search
            finishActivityEvent.call()
        }
    }
}