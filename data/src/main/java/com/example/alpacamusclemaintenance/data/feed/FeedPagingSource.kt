package com.example.alpacamusclemaintenance.data.feed

import androidx.paging.PagingSource
import com.example.alpacamusclemaintenance.domain.feed.Feed
import java.io.IOException
import retrofit2.HttpException

// References:
//  - https://developer.android.com/reference/kotlin/androidx/paging/PagingSource

private const val QIITA_STARTING_PAGE_INDEX = 1

class FeedPagingSource(
    private val service: QiitaApi,
    private val query: String
) : PagingSource<Int, Feed>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Feed> {
        return try {
            val position = params.key ?: QIITA_STARTING_PAGE_INDEX
            val feeds: List<Feed> = service.getFeeds(
                query = query,
                page = position,
                itemsPerPage = params.loadSize
            )

            return LoadResult.Page(
                data = feeds,
                prevKey = if (position == QIITA_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (feeds.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
