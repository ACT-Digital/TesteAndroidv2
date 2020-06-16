package academy.mukandrew.bank.domain.useCases

import academy.mukandrew.bank.commons.extensions.isValidCPF
import academy.mukandrew.bank.commons.extensions.isValidEmail
import academy.mukandrew.bank.data.repositories.AuthRepository
import academy.mukandrew.bank.data.models.BaseResponse
import academy.mukandrew.bank.data.models.auth.LoginBody
import academy.mukandrew.bank.domain.models.UserInfo
import java.util.regex.Pattern

class AuthUseCase(private val repository: AuthRepository) {

    fun validateLogin(user: String): Boolean {
        return user.run {
            isNotEmpty() && isNotBlank() &&
                    (user.isValidEmail() || user.isValidCPF())
        }
    }

    fun validatePassword(password: String): Boolean {
        val pattern = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=]).*$")
        return password.run {
            isNotBlank() && isNotEmpty() && pattern.matches(this)
        }
    }

    suspend fun postLogin(user: String, password: String): BaseResponse<UserInfo> {
        return when (val response = repository.postLogin(LoginBody(user, password))) {
            is BaseResponse.Success -> {
                BaseResponse.Success(UserInfo.createFromResponse(response.data))
            }
            is BaseResponse.Error -> response
        }
    }

    suspend fun getCurrentUser(): BaseResponse<UserInfo> {
        return when (val response = repository.getCurrentUser()) {
            is BaseResponse.Success -> {
                BaseResponse.Success(UserInfo.createFromEntity(response.data))
            }
            is BaseResponse.Error -> response
        }
    }

    suspend fun postLogout(): BaseResponse<Boolean> {
        return repository.postLogout()
    }
}