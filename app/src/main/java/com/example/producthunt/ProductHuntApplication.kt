package com.example.producthunt

import android.app.Application
import com.example.producthunt.data.db.ProductHuntDataBase
import com.example.producthunt.data.network.*
import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.data.repository.PostRepositoryImpl
import com.example.producthunt.ui.posts.PostListViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContainer
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ProductHuntApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ProductHuntApplication))

        bind() from singleton { ProductHuntDataBase(instance())}
        bind() from singleton { instance<ProductHuntDataBase>().postDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiProductHuntService(instance()) }
        bind<PostNetworkDataSource>() with singleton { PostNetworkDataSourceImpl(instance()) }
        bind<PostRepository>() with singleton { PostRepositoryImpl(instance(), instance()) }
        bind() from provider { PostListViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}