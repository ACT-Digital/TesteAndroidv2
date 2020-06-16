package academy.mukandrew.bank.data.repositories

import academy.mukandrew.bank.data.dataSources.statement.StatementRemoteDataSource
import academy.mukandrew.bank.data.models.BaseResponse
import academy.mukandrew.bank.data.models.statement.StatementListResponse

class StatementRepository(private val dataSource: StatementRemoteDataSource) {
    suspend fun getStatementList(userId: String): BaseResponse<StatementListResponse> {
        return BaseResponse.createFrom(dataSource.getStatementList(userId))
    }
}