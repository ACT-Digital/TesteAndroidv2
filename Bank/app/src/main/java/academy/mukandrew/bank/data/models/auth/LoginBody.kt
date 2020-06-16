package academy.mukandrew.bank.data.models.auth

data class LoginBody(
    val user: String,
    val password: String
)