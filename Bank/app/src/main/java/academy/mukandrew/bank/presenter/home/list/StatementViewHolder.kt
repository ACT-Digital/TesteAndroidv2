package academy.mukandrew.bank.presenter.home.list

import academy.mukandrew.bank.R
import academy.mukandrew.bank.domain.models.Statement
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_statement.view.*

class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): StatementViewHolder {
            return StatementViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
            )
        }
    }

    fun bind(statement: Statement) = with(itemView) {
        itemTitleTextView.text = statement.title
        itemDescriptionTextView.text = statement.description
        itemDateTextView.text = statement.date
        itemValueTextView.text = statement.value.toString()
    }
}