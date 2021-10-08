package com.icodeu.bakeryapp.domain.use_case.bread

import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.repository.BreadRepository
import com.icodeu.bakeryapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetSimilarBreadUseCase(private val breadRepository: BreadRepository) {
    operator fun invoke(): Flow<com.icodeu.bakeryapp.core.utils.Resource<List<Bread>>> = flow {
        try {
            emit(com.icodeu.bakeryapp.core.utils.Resource.Loading())
            val bread = breadRepository.getSimilar()
            emit(com.icodeu.bakeryapp.core.utils.Resource.Success(bread))
        } catch (e: HttpException) {
            emit(com.icodeu.bakeryapp.core.utils.Resource.Error(e.localizedMessage ?: "An unexpected error occurred",breadRepository.getSimilarCache()))
        } catch (e: IOException) {
            emit(com.icodeu.bakeryapp.core.utils.Resource.Error("Connection failed",breadRepository.getSimilarCache()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(com.icodeu.bakeryapp.core.utils.Resource.Error(e.message.toString(),breadRepository.getSimilarCache()))
        }
    }
}