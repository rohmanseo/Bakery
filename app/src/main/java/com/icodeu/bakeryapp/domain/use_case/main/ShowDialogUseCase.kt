package com.icodeu.bakeryapp.domain.use_case.main

import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ShowDialogUseCase {

    operator fun invoke(isLoading: Boolean): Flow<Resource<Boolean>> = flow<Resource<Boolean>> {
        try {
            if (isLoading) {
                emit(Resource.Loading())
            } else {
                emit(Resource.Success(true))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message.toString()))
        }
    }

}