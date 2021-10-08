package com.icodeu.bakeryapp.domain.use_case.user

import com.icodeu.bakeryapp.domain.repository.UserRepository
import com.icodeu.bakeryapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LogoutUseCase(val userRepository: UserRepository) {

    operator fun invoke(): Flow<com.icodeu.bakeryapp.core.utils.Resource<Boolean>> = flow {
        try {
            emit(com.icodeu.bakeryapp.core.utils.Resource.Loading())
            val logout = userRepository.logout()
            emit(com.icodeu.bakeryapp.core.utils.Resource.Success(logout))
        } catch (e: HttpException) {
            emit(com.icodeu.bakeryapp.core.utils.Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(com.icodeu.bakeryapp.core.utils.Resource.Error("Connection failed"))
        }catch (e:Exception){
            e.printStackTrace()
            emit(com.icodeu.bakeryapp.core.utils.Resource.Error(e.message.toString()))
        }
    }

}