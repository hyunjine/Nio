package com.hyunjine.nio.clothes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyunjine.nio.clothes.model.ClothesItemModel
import com.hyunjine.nio.data.clothes.ClothesLocalDataSource
import com.hyunjine.nio.util.common_android.wlog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val clothesLocalDataSource: ClothesLocalDataSource
): ViewModel() {
    init {
        viewModelScope.launch {
            wlog(clothesLocalDataSource.getClothes())
        }
    }
    val city: StateFlow<String>
        field: MutableStateFlow<String> = MutableStateFlow("")

    val clothes: StateFlow<List<ClothesItemModel>> = flow {
        emit(
            List(
                10
            ) {
                val link = "https://picsum.photos/400/200"
                ClothesItemModel(link = link, thumbnail = link, description = "테스트입니다.")
            }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}