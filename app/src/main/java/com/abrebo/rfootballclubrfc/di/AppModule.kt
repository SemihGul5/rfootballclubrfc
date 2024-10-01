package com.abrebo.rfootballclubrfc.di

import android.content.Context
import androidx.room.Room
import com.abrebo.rfootballclubrfc.data.datasource.Datasource
import com.abrebo.rfootballclubrfc.data.repo.Repository
import com.abrebo.rfootballclubrfc.retrofit.ApiUtils
import com.abrebo.rfootballclubrfc.retrofit.TeamDao
import com.abrebo.rfootballclubrfc.room.Db
import com.abrebo.rfootballclubrfc.room.DbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDatasource(teamDao: TeamDao,dbDao: DbDao):Datasource{
        return Datasource(teamDao,dbDao)
    }
    @Provides
    @Singleton
    fun provideDbDao(@ApplicationContext context: Context):DbDao{
        val db=Room.databaseBuilder(context,Db::class.java,"teams.sqlite")
            .createFromAsset("teams.sqlite").build()
        return db.getDbDao()
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