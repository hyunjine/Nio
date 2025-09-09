package com.hyunjine.nio.clothes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyunjine.nio.clothes.model.ClothesItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ClothesViewModel: ViewModel() {
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