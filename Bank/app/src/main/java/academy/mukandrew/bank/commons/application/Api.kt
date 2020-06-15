package academy.mukandrew.bank.commons.application

object Api {
    const val base = "https://bank-app-test.herokuapp.com/api/"
    const val login = "login"
    const val userIdParam = "userId"
    const val statements = "statements/{$userIdParam}"
}