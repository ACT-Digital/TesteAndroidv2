package academy.mukandrew.bank.data.models.auth

import academy.mukandrew.bank.data.services.local.entities.UserEntity

data class UserAccountResponse(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Float
) {
    fun toEntity(loginBody: LoginBody): UserEntity {
        return UserEntity(
            userId,
            name,
            bankAccount,
            agency,
            balance,
            loginBody.user,
            loginBody.password
        )
    }
}