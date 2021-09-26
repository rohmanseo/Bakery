package com.icodeu.bakeryapp.network

import com.icodeu.bakeryapp.network.services.UserService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenRefreshAuthenticator(private val userService: UserService) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {

        val newToken = userService.refreshToken().execute().body()?.token

        return response.request()
            .newBuilder()
            .header("Authentication", "Bearer $newToken")
            .build()
    }

}