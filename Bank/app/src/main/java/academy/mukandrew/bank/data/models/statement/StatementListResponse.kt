package academy.mukandrew.bank.data.models.statement

import academy.mukandrew.bank.data.interfaces.ResponseInterface
import academy.mukandrew.bank.data.models.ErrorResponse

data class StatementListResponse(
    val statementList: List<StatementItemResponse>,
    override val error: ErrorResponse?
) : ResponseInterface