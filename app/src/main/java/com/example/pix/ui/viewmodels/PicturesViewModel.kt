package com.example.pix.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.domain.entity.Picture
import com.example.pix.domain.usecase.GetPicturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getPicturesUseCase: GetPicturesUseCase
) : ViewModel() {

    private val baseQuery = "cats"
    private val query = MutableStateFlow(baseQuery)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val result = query
        .debounce(300)
        .distinctUntilChanged()
        .mapLatest { query ->
            getPicturesUseCase(query).fold(
                onSuccess = { list ->
                    SearchResult.Success(list)
                },
                onFailure = { throwable ->
                    Log.e("PicturesViewModel", "Error", throwable)
                    SearchResult.Error
                }
            )
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, SearchResult.Success(emptyList()))


    fun onQueryChanged(query: String) {
        this.query.value = query
    }
}

sealed class SearchResult {
    data class Success(val pictures: List<Picture>) : SearchResult()
    data object Error : SearchResult()
}