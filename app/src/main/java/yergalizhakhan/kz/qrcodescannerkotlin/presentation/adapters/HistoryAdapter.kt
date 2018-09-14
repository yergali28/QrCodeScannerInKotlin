package yergalizhakhan.kz.qrcodescannerkotlin.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_history.view.*
import yergalizhakhan.kz.qrcodescannerkotlin.R
import yergalizhakhan.kz.qrcodescannerkotlin.domain.History
import yergalizhakhan.kz.qrcodescannerkotlin.utils.ActionEnums

class HistoryAdapter(private val histories: MutableList<History>,
                          private val onClick: (History, String) -> Unit)
    : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun addHistories(newHistories: MutableList<History>) {
        histories.addAll(newHistories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_history, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    override fun getItemCount(): Int = histories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(histories[position])
    }

    class ViewHolder(val containerView: View, private val onClick: (History, String) -> Unit) : RecyclerView.ViewHolder(containerView) {

        fun bindData(history: History) {
            with(history) {
                var rate: Int = position + 1
                containerView.tvContext.text = "$rate. " + history.context
                containerView.tvDate.text = history.date
                containerView.ivShare.setOnClickListener { onClick(this, ActionEnums().ACTION_SHARE) }
                containerView.ivCopy.setOnClickListener { onClick(this, ActionEnums().ACTION_COPY) }
                containerView.ivSeacrh.setOnClickListener { onClick(this, ActionEnums().ACTION_SEARCH) }
            }
        }
    }

}