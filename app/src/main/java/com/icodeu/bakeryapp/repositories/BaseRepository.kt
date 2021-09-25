package com.icodeu.bakeryapp.repositories

abstract class BaseRepository<DataSource> {
    protected var localDataSource: DataSource? = null
    protected var remoteDataSource: DataSource? = null

    fun init(localDataSource: DataSource, remoteDataSource: DataSource){
        this.localDataSource = localDataSource
        this.remoteDataSource = remoteDataSource
    }
}