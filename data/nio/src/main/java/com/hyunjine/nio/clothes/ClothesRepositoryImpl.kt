package com.hyunjine.nio.clothes

import android.content.Context
import android.webkit.WebSettings
import com.hyunjine.clothes.ClothesRepository
import com.hyunjine.clothes.list.model.ClothesItemModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import org.jsoup.Jsoup
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val clothesRemoteDataSource: ClothesRemoteDataSource,
    @param:ApplicationContext private val context: Context
): ClothesRepository {
    override suspend fun getClothes(): List<ClothesItemModel> {
        return clothesRemoteDataSource.getClothes().map { entity ->
            ClothesItemModel(
                id = entity.id,
                link = entity.link,
                thumbnail = entity.thumbnail,
                description = entity.description
            )
        }
    }

    override suspend fun addClothes(
        link: String,
        thumbnail: String?,
        description: String
    ) {
        clothesRemoteDataSource.addClothes(link = link, thumbnail = thumbnail, description = description)
    }

    override suspend fun removeClothes(id: Long) {
        clothesRemoteDataSource.removeClothes(id)
    }

    override suspend fun fetchThumbnail(url: String): String? {
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
}