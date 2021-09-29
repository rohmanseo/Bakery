package com.icodeu.bakeryapp.domain.use_case.user

import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.repository.UserRepository
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RegisterUseCase(private val userRepository: UserRepository) {

    operator fun invoke(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val register = userRepository.register(name, email, password, passwordConfirmation)
            emit(Resource.Success(register!!))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Connection failed"))
        }catch (e:Exception){
            e.printStackTrace()
            emit(Resource.Error(e.message.toString()))
        }
    }

}