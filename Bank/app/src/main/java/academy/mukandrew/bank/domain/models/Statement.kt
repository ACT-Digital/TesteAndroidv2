package academy.mukandrew.bank.domain.models

import academy.mukandrew.bank.data.models.statement.StatementItemResponse

data class Statement(
    val title: String,
    val description: String,
    val date: String,
    val value: Float
) {
    companion object {
        fun createFrom(item: StatementItemResponse): Statement {
            return Statement(
                item.title,
                item.desc,
                item.date,
                item.value
            )
        }
    }
}