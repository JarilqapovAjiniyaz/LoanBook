package uz.texnopos.myapplicationdebtbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.myapplicationdebtbooks.databinding.TransactionRecyclerviewItemBinding

class UserTransactionAdapter: RecyclerView.Adapter<UserTransactionAdapter.UserTransactionViewHolder> (){
    var models:List<TransactionHistory> = listOf()
    set(value) {
    field = value
    notifyDataSetChanged()
    }
    inner class UserTransactionViewHolder(var binding : TransactionRecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
    fun populateModel(transactionHistory: TransactionHistory){
        binding.apply {
            name.text = transactionHistory.name
            balance.text = transactionHistory.balance.toString()
            coment.text = transactionHistory.coment
            calendar.text = transactionHistory.date.toString()
        }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserTransactionViewHolder {
    val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.transaction_recyclerview_item,parent,false)
    var binding = TransactionRecyclerviewItemBinding.bind(itemView)
        return UserTransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserTransactionViewHolder, position: Int) {
    holder.populateModel(models[position])
    }

    override fun getItemCount(): Int {
    return models.size
    }
}