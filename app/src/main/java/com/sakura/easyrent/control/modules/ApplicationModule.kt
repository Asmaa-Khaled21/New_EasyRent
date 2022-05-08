@file:Suppress("unused")

package com.sakura.easyrent.control.modules

import com.sakura.easyrent.control.interfaces.API
import com.sakura.easyrent.control.managers.APIManager
import com.sakura.easyrent.control.repositories.APIRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAPI(): API = APIManager.instance()

    @Provides
    @Singleton
    fun provideAPIRepository(api: API): APIRepository = APIRepository(api)
}