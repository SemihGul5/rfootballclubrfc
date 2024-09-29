package com.abrebo.rfootballclubrfc.di

import com.abrebo.rfootballclubrfc.data.datasource.Datasource
import com.abrebo.rfootballclubrfc.data.repo.Repository
import com.abrebo.rfootballclubrfc.retrofit.ApiUtils
import com.abrebo.rfootballclubrfc.retrofit.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDatasource(teamDao: TeamDao):Datasource{
        return Datasource(teamDao)
    }

    @Provides
    @Singleton
    fun provideTeamDao():TeamDao{
        return ApiUtils.getTeamDao()
    }

    @Provides
    @Singleton
    fun provideRepository(datasource: Datasource):Repository{
        return Repository(datasource)
    }
}