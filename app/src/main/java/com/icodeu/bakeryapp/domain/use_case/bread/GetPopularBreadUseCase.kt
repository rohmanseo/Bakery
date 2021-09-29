package com.icodeu.bakeryapp.domain.use_case.bread

import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.repository.BreadRepository
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetPopularBreadUseCase(private val breadRepository: BreadRepository) {
    operator fun invoke(): Flow<Resource<List<Bread>>> = flow {
        try {
            emit(Resource.Loading())
            val bread = breadRepository.getPopular()
            emit(Resource.Success(bread))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Connection failed"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message.toString()))
        }
    }
}