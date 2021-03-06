package com.example.alpacamusclemaintenance.data.feed

import com.example.alpacamusclemaintenance.domain.feed.Feed
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApi {

    // @see https://qiita.com/api/v2/docs#get-apiv2items
    @GET("api/v2/items")
    suspend fun getFeeds(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): List<Feed>

    companion object {
        const val HTTPS_API_QIITA_URL = "https://qiita.com"
    }
}
