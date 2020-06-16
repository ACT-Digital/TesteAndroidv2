package academy.mukandrew.bank.domain.models

import academy.mukandrew.bank.commons.extensions.formatBrCurrency
import academy.mukandrew.bank.commons.extensions.formatBrDate
import academy.mukandrew.bank.data.models.statement.StatementItemResponse

data class Statement(
    val title: String,
    val description: String,
    val date: String,
    val value: Float
) {
    fun getDateFormatted(): String {
        return date.formatBrDate("yyyy-MM-dd", "dd/MM/yyyy")
    }

    fun getValueFormatted(): String {
        return value.formatBrCurrency()
    }

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