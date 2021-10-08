package com.icodeu.bakeryapp.domain.use_case.user

import com.icodeu.bakeryapp.domain.repository.UserRepository
import com.icodeu.bakeryapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class IsLoggedInUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<Resource<Boolean>> = flow<Resource<Boolean>> {
        try {
            emit(Resource.Loading())
            val isLoggedIn = userRepository.isLoggedIn()
            emitAll(isLoggedIn)
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Connection failed"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message.toString()))
        }
    }
}