package academy.mukandrew.bank.data.services.remote

import academy.mukandrew.bank.commons.application.Api
import academy.mukandrew.bank.data.models.statement.StatementListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementService {
    @GET(Api.statements)
    suspend fun listStatements(
        @Path(Api.userIdParam) userId: String
    ): Response<StatementListResponse>
}