package academy.mukandrew.bank.data.repositories

import academy.mukandrew.bank.data.dataSources.auth.AuthLocalDataSource
import academy.mukandrew.bank.data.dataSources.auth.AuthRemoteDataSource
import academy.mukandrew.bank.data.models.BaseResponse
import academy.mukandrew.bank.data.models.auth.LoginBody
import academy.mukandrew.bank.data.models.auth.LoginResponse
import academy.mukandrew.bank.data.services.local.entities.UserEntity

class AuthRepository(
    private val localDataSource: AuthLocalDataSource,
    private val remoteDataSource: AuthRemoteDataSource
) {
    suspend fun postLogin(loginBody: LoginBody): BaseResponse<LoginResponse> {
        val response = remoteDataSource.postLogin(loginBody)
        return response.body()?.userAccount?.toEntity(loginBody)?.let { entity ->
            if (response.isSuccessful) {
                localDataSource.saveUser(entity)
            }
            BaseResponse.createFrom(response)
        } ?: BaseResponse.genericError()
    }

    suspend fun getCurrentUser(): BaseResponse<UserEntity> {
        return BaseResponse.createFrom(localDataSource.getCurrentUser())
    }

    suspend fun postLogout(): BaseResponse<Boolean> {
        return BaseResponse.createFrom(localDataSource.deleteUsers())
    }
}