package academy.mukandrew.bank.domain.models

import academy.mukandrew.bank.data.models.auth.LoginResponse
import academy.mukandrew.bank.data.services.local.entities.UserEntity

data class UserInfo(
    val id: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Float,
    val login: String = String(),
    val password: String = String()
) {
    fun getBankNumber(): String {
        return "$bankAccount / $agency"
    }

    fun getBalanceFormatted(): String {
        return "R\$$balance"
    }

    companion object {
        fun createFromResponse(loginResponse: LoginResponse): UserInfo {
            return UserInfo(
                loginResponse.userAccount.userId,
                loginResponse.userAccount.name,
                loginResponse.userAccount.bankAccount,
                loginResponse.userAccount.agency,
                loginResponse.userAccount.balance
            )
        }

        fun createFromEntity(userEntity: UserEntity): UserInfo {
            return UserInfo(
                userEntity.userId,
                userEntity.name,
                userEntity.bankAccount,
                userEntity.agency,
                userEntity.balance,
                userEntity.login,
                userEntity.password
            )
        }
    }
}