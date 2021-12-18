package uz.gita.interviewtask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.interviewtask.domain.repository.AppRepository
import uz.gita.interviewtask.domain.repository.impl.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun getAppRepository(): AppRepository = AppRepositoryImpl()
}