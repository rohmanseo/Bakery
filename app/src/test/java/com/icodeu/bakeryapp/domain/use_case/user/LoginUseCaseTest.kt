package com.icodeu.bakeryapp.domain.use_case.user

import com.icodeu.bakeryapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class LoginUseCaseTest {

    private lateinit var userRepository: com.icodeu.bakeryapp.domain.repository.UserRepository
    private lateinit var loginUseCase: com.icodeu.bakeryapp.domain.use_case.user.LoginUseCase
    private val validEmailAndPassword = arrayOf("rohman@gmail.com", "password")
    private val inValidEmailAndPassword = arrayOf("rohman@gmail.com", "invalidpsswd")

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
        loginUseCase = com.icodeu.bakeryapp.domain.use_case.user.LoginUseCase(userRepository)
    }

    @Test
    fun `Login using valid email and password return success`() = runBlocking {
        val loginResult = loginUseCase(validEmailAndPassword[0], validEmailAndPassword[1])
        loginResult.drop(1).collect {
            assertEquals(FakeUserRepository.validUser, it.data)
        }
    }

    @Test
    fun `Login using invalid credential return error`() = runBlocking {
        try {
            val loginResult = loginUseCase(inValidEmailAndPassword[0], inValidEmailAndPassword[1])
            loginResult.drop(1)
                .collect {
                    assertNotEquals(FakeUserRepository.validUser, it.data)
                }
        } catch (e: Exception) {
            assertEquals( "invalid user",e.message)
        }
    }


}