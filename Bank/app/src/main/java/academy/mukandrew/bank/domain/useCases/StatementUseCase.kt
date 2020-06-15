package academy.mukandrew.bank.domain.useCases

import academy.mukandrew.bank.data.models.BaseResponse
import academy.mukandrew.bank.data.repositories.StatementRepository
import academy.mukandrew.bank.domain.models.Statement

class StatementUseCase(private val repository: StatementRepository) {
    suspend fun getStatementList(userId: String): BaseResponse<List<Statement>> {
        return when (val response = repository.getStatementList(userId)) {
            is BaseResponse.Success -> {
                BaseResponse.Success(response.data.statementList.map { Statement.createFrom(it) })
            }
            is BaseResponse.Error -> response
        }
    }
}