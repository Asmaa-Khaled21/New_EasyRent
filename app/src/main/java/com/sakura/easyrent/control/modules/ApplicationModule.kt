@file:Suppress("unused")

package com.sakura.easyrent.control.modules

import android.content.Context
import com.sakura.easyrent.control.interfaces.API
import com.sakura.easyrent.control.managers.APIManager
import com.sakura.easyrent.control.managers.SPManager
import com.sakura.easyrent.control.repositories.APIRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    // Method(API):
    @Provides @Singleton fun provideAPI(): API = APIManager.instance()

    // Method(APIRepository):
    @Provides @Singleton fun provideAPIRepository(api: API): APIRepository = APIRepository(api)

    // Method(SPManager):
    @Provides @Singleton fun provideSPManager(@ApplicationContext context: Context): SPManager = SPManager(context)

}