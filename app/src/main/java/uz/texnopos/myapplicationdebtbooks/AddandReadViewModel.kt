package uz.texnopos.myapplicationdebtbooks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddandReadViewModel(private var Helper:AddandReadHelper, var contacts : ContactTransaction,var history: HistoryTransaction) : ViewModel(){
var transaction:MutableLiveData<String> = MutableLiveData()
    var contactsLive : MutableLiveData<Resource<List<CollectionUser>>> = MutableLiveData()
     var transactionLive : MutableLiveData<Resource<List<TransactionHistory>>> = MutableLiveData()
fun addandReadviewModelPlus(contact : String,amount:Double,coment:String,date:Long){
    transaction.value = "Loading"
Helper.add(contact ,amount,coment,date,
    onSuccess = {
        transaction.value = "Success"
    },
    onFailure = {
        transaction.value = it
    })
}
    fun addandReadviewModelMinus(contact : String,amount:Double,coment:String,date:Long){
        transaction.value = "Loading"
        Helper.read(contact ,amount,coment,date,
            onSuccess = {
                transaction.value = "Success"

            },
            onFailure = {
                transaction.value = it
            })
    }
    fun getContact(){
        contactsLive.value = Resource.loading()
        contacts.getALLContacts(
            {
                contactsLive.value = Resource.success(it)
            }, {
                contactsLive.value = Resource.error(it)
            }
        )
    }
    fun getTransaction(name:String){
        transactionLive.value = Resource.loading()
        history.getAllTransactions(name,{
        transactionLive.value = Resource.success(it)
        },{
        transactionLive.value = Resource.error(it)

        })


    }
}