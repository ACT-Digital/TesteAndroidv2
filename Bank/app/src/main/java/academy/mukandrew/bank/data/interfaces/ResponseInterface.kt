package academy.mukandrew.bank.data.interfaces

import academy.mukandrew.bank.data.models.ErrorResponse

interface ResponseInterface {
    val error: ErrorResponse?
}