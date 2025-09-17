package com.hyunjine.nio.clothes

import android.content.Context
import android.webkit.WebSettings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyunjine.nio.clothes.model.ClothesItemModel
import com.hyunjine.nio.util.common_android.wlog
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
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

    fun addClothes() {
        viewModelScope.launch {
            val thumbnail = fetchThumbnail(link.value)
            repository.addClothes(
                ClothesItemModel(
                    link = link.value,
                    thumbnail = thumbnail,
                    description = description.value
                )
            )
        }
    }

    suspend fun fetchThumbnail(url: String): String? {
        val client = HttpClient(CIO)
        return try {
            val userAgent = WebSettings.getDefaultUserAgent(context)
            val html = client.get(url) {
                headers { append(HttpHeaders.UserAgent, userAgent) }
            }.bodyAsText()
            val doc = Jsoup.parse(html)
            val ogImage = doc.select("meta[property=og:image]").attr("content")
            ogImage.ifEmpty { null }
        } catch (_: Exception) {
            null
        } finally {
            client.close()
        }
    }

    fun removeClothes(id: Long) {
        viewModelScope.launch {
            repository.removeClothes(id)
        }
    }
}