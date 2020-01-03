package com.ldl.wanandroid.di.module

import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ldl.wanandroid.BuildConfig
import com.ldl.wanandroid.app.Constants
import com.ldl.wanandroid.core.api.Apis
import com.ldl.wanandroid.di.qualifier.WanAndroidUrl
import dagger.Module
import dagger.Provides
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
@Module
class HttpModule {

    @Singleton
    @Provides
    fun provideApis(@WanAndroidUrl retrofit: Retrofit): Apis = retrofit.create(Apis::class.java)

    @Singleton
    @Provides
    @WanAndroidUrl
    fun provideRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit =
        createRetrofit(builder, client, Apis.HOST)

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Singleton
    @Provides
    fun provideClient(builder: OkHttpClient.Builder): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
            builder.addInterceptor(StethoInterceptor())
        }
        val cacheFile = File(Constants.PATH_CACHE)
        val cache = Cache(cacheFile, 1024 * 1024 * 50)
        val cacheInterceptor: Interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()
                if (!NetworkUtils.isConnected()) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (NetworkUtils.isConnected()) {
                    val maxAge = 0
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .removeHeader("Pragma")
                        .build()
                } else {
                    // 无网络时，设置超时为4周
                    val maxStale = 60 * 60 * 24 * 28
                    response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .removeHeader("Pragma")
                        .build()
                }
                return response
            }
        }
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor)
        builder.addInterceptor(cacheInterceptor)
        builder.cache(cache)
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        //错误重连
        builder.retryOnConnectionFailure(true)
        //cookie认证
        builder.cookieJar(
            PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(Utils.getApp())
            )
        )
        return RetrofitUrlManager.getInstance().with(builder).build()
    }

    private fun createRetrofit(
        builder: Retrofit.Builder,
        client: OkHttpClient,
        url: String
    ): Retrofit = builder
        .baseUrl(url)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}