package academy.mukandrew.bank.presentation.home.list

import academy.mukandrew.bank.domain.models.Statement
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class StatementAdapter() : ListAdapter<Statement, StatementViewHolder>(StatementDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        return StatementViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}