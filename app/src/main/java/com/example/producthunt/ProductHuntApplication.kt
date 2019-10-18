package com.example.producthunt

import android.app.Application
import com.example.producthunt.data.db.ProductHuntDataBase
import com.example.producthunt.data.network.*
import com.example.producthunt.data.preferences.SharedPreferences
import com.example.producthunt.data.repository.PostRepository
import com.example.producthunt.data.repository.PostRepositoryImpl
import com.example.producthunt.ui.posts.detail.comments.DetailCommentViewModelFactory
import com.example.producthunt.ui.posts.detail.info.DetailPostViewModelFactory
import com.example.producthunt.ui.posts.list.PostListViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class ProductHuntApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ProductHuntApplication))

        bind() from singleton { ProductHuntDataBase(instance())}
        bind() from singleton { instance<ProductHuntDataBase>().postDao() }
        bind() from singleton { instance<ProductHuntDataBase>().commentPostDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiProductHuntService(instance()) }
        bind<PostNetworkDataSource>() with singleton { PostNetworkDataSourceImpl(instance()) }
        bind() from singleton { SharedPreferences(instance()) }
        bind<PostRepository>() with singleton { PostRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind() from provider { PostListViewModelFactory(instance()) }
        bind() from factory { productId: Long -> DetailPostViewModelFactory(productId, instance()) }
        bind() from factory { productId: Long -> DetailCommentViewModelFactory(productId,instance())}
     //   bind() from factory {DetailCommentViewModelFactory(instance(), instance())}
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}