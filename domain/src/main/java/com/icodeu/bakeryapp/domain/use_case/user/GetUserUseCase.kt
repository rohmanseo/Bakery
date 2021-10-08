package com.icodeu.bakeryapp.domain.use_case.user

import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class GetUserUseCase(val userRepository: UserRepository) {

    operator fun invoke(): Flow<Resource<User?>> = flow<Resource<User?>> {
        try {
            emit(Resource.Loading())
            val user = userRepository.getLoggedInUser()
            emitAll(user)
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