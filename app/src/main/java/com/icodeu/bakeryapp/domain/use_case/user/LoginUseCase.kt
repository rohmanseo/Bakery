package com.icodeu.bakeryapp.domain.use_case.user

import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.repository.UserRepository
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginUseCase(private val userRepository: UserRepository) {

    operator fun invoke(email:String,password:String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val login = userRepository.login(email, password)
            emit(Resource.Success(login!!))
        }catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Connection failed"))
        }catch (e:Exception){
            e.printStackTrace()
            emit(Resource.Error(e.message.toString()))
        }
    }

}