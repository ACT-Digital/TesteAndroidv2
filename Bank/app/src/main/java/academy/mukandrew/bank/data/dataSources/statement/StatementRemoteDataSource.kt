package academy.mukandrew.bank.data.dataSources.statement

import academy.mukandrew.bank.data.models.statement.StatementListResponse
import academy.mukandrew.bank.data.services.remote.StatementService
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import retrofit2.Retrofit

class StatementRemoteDataSource(retrofit: Retrofit) {
    private val service = retrofit.create(StatementService::class.java)

    suspend fun getStatementList(userId: String): Response<StatementListResponse> {
        return try {
            service.listStatements(userId)
        } catch (e: Exception) {
            Response.error(e.hashCode(), e.message.toString().toResponseBody())
        }
    }
}