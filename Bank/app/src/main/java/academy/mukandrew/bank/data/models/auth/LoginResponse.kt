package academy.mukandrew.bank.data.models.auth

import academy.mukandrew.bank.data.models.ErrorResponse
import academy.mukandrew.bank.data.interfaces.ResponseInterface

data class LoginResponse(
    val userAccount: UserAccountResponse,
    override val error: ErrorResponse? = null
) : ResponseInterface