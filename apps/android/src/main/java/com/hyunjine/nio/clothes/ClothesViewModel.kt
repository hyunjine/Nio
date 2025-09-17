package com.hyunjine.nio.clothes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyunjine.nio.clothes.model.ClothesItemModel
import com.hyunjine.nio.data.clothes.ClothesLocalDataSource
import com.hyunjine.nio.util.common_android.wlog
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val repository: ClothesRepository,
    @param:ApplicationContext
    private val context: Context
): ViewModel() {
    val link: StateFlow<String>
        field: MutableStateFlow<String> = MutableStateFlow("")

    val description: StateFlow<String>
        field: MutableStateFlow<String> = MutableStateFlow("")

    val clothes: StateFlow<List<ClothesItemModel>> = repository.getClothes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun updateLink(value: String) {
        link.update { value }
    }

    fun updateDescription(value: String) {
        description.update { value }
    }

    fun addClothes(
        thumbnail: String = "",
    ) {
        viewModelScope.launch {
            repository.addClothes(
                ClothesItemModel(
                    link = link.value,
                    thumbnail = thumbnail,
                    description = description.value
                )
            )
        }
    }

    fun removeClothes(id: Long) {
        viewModelScope.launch {
            repository.removeClothes(id)
        }
    }
}