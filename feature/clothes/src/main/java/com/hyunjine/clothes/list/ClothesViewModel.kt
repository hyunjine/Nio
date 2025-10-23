package com.hyunjine.clothes.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyunjine.clothes.ClothesRepository
import com.hyunjine.clothes.list.model.ClothesItemModel
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
): ViewModel() {
    val link: StateFlow<String>
        field: MutableStateFlow<String> = MutableStateFlow("")

    val description: StateFlow<String>
        field: MutableStateFlow<String> = MutableStateFlow("")

    val clothes: StateFlow<List<ClothesItemModel>> = flow {
        emit(repository.getClothes())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun updateLink(value: String) {
        link.update { value }
    }

    fun updateDescription(value: String) {
        description.update { value }
    }

    fun addClothes() {
        viewModelScope.launch {
            val thumbnail = repository.fetchThumbnail(link.value)
            repository.addClothes(
                link = link.value,
                thumbnail = thumbnail,
                description = description.value
            )
        }
    }

    fun removeClothes(id: Long) {
        viewModelScope.launch {
            repository.removeClothes(id)
        }
    }
}