package academy.mukandrew.bank.data.models.statement

data class StatementItemResponse(
    val title: String,
    val desc: String,
    val date: String,
    val value: Float
)