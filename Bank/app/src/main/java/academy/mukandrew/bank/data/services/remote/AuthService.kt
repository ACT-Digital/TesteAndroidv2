package academy.mukandrew.bank.data.services.remote

import academy.mukandrew.bank.commons.application.Api
import academy.mukandrew.bank.data.models.auth.LoginBody
import academy.mukandrew.bank.data.models.auth.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(Api.login)
    suspend fun postLogin(
        @Body data: LoginBody
    ): Response<LoginResponse>
}