package uz.texnopos.myapplicationdebtbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.myapplicationdebtbooks.databinding.ItemBinding
import uz.texnopos.myapplicationdebtbooks.ui.MainFragment

class AdapterUser(val mainFragment: MainFragment) :
    RecyclerView.Adapter<AdapterUser.TransactionViewModel>() {

    var models: List<CollectionUser> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class TransactionViewModel(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(transactionUser: CollectionUser) {
            binding.apply {
                tvBalance.text = transactionUser.balance.toString()
                tvId.text = transactionUser.id
                tvName.text = transactionUser.name
            }
//            itemView.tvId.setTextColor(itemView.context.getColor(R.color.green))
//            itemView.tvId.setTextColor(itemView.context.getColor(R.color.red))
////            itemView.tvName.setTextColor(itemView.context.getColor(R.color.purple_700))
//        itemView.moreVert.setOnClickListener {
//      mainFragment.onOptionButtonClick(itemView)
//        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewModel {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return TransactionViewModel(ItemBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: TransactionViewModel, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }
}