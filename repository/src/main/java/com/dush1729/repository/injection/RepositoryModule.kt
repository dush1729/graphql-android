package com.dush1729.repository.injection

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo.fetcher.ApolloResponseFetchers.CACHE_FIRST
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideLruCache(): LruNormalizedCacheFactory {
        // Create a 50MB NormalizedCacheFactory
        return LruNormalizedCacheFactory(
            EvictionPolicy.builder().maxSizeBytes(50 * 1024 * 1024).build()
        )
    }

    @Singleton
    @Provides
    fun provideSqlCache(): SqlNormalizedCacheFactory {
        return SqlNormalizedCacheFactory(application, "apollo_cache.db")
    }

    @Singleton
    @Provides
    fun provideCacheResolver(): CacheKeyResolver {
        return object : CacheKeyResolver() {
            override fun fromFieldRecordSet(
                field: ResponseField,
                recordSet: Map<String, Any>
            ): CacheKey {
                return formatCacheKey(recordSet["id"] as String?)
            }

            override fun fromFieldArguments(
                field: ResponseField,
                variables: Operation.Variables
            ): CacheKey {
                return formatCacheKey(field.resolveArgument("id", variables) as String?)
            }

            private fun formatCacheKey(id: String?) = when {
                id.isNullOrEmpty() -> CacheKey.NO_KEY
                else -> CacheKey.from(id)
            }
        }
    }

    @Singleton
    @Provides
    @Named("Apollo")
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }


    // TODO 1 provide apollo client
    @Singleton
    @Provides
    fun provideApolloClient(
        @Named("Apollo") okHttpClient: OkHttpClient,
        lruCache: LruNormalizedCacheFactory,
        sqlCache: SqlNormalizedCacheFactory,
        resolver: CacheKeyResolver
    ): ApolloClient {
        val lruFirstThenSqlCache = lruCache.chain(sqlCache)
        return ApolloClient.builder()
            .serverUrl("https://graphql-pokemon2.vercel.app")
            .normalizedCache(lruFirstThenSqlCache, resolver)
            .defaultResponseFetcher(CACHE_FIRST)
            .okHttpClient(okHttpClient)
            .build()
    }
}