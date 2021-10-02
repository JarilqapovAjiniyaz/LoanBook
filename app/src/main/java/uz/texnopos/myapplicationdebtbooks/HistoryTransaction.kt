package uz.texnopos.myapplicationdebtbooks

import com.google.firebase.firestore.FirebaseFirestore

class HistoryTransaction(val db:FirebaseFirestore) {
fun getAllTransactions(name:String,onSuccess:(result:List<TransactionHistory>)->Unit,onFailure:(msg:String)->Unit){
checkUser(name,onSuccess, onFailure)
}
private fun checkUser(name:String,onSuccess:(result:List<TransactionHistory>)->Unit,onFailure:(msg:String)->Unit){
    var id:String = ""
db.collection(Object.CONTACT).whereEqualTo("name",name).get()

    .addOnSuccessListener {documents->
    id = documents.documents[0].data!!.getValue("id").toString()
    getTransaction(id,onSuccess,onFailure)
    }

}
private fun getTransaction(id:String,onSuccess:(result:List<TransactionHistory>)->Unit,onFailure:(msg:String)->Unit){
 var result:MutableList<TransactionHistory> = mutableListOf()
db.collection(Object.CONTACT).document(id).collection(Object.TRANSACTION).get()
    .addOnSuccessListener {
        it.documents.forEach {doc->
        result.add(doc.toObject(TransactionHistory::class.java)!!)
        }
        onSuccess.invoke(result)
    }
    .addOnFailureListener {
    onFailure.invoke(it.localizedMessage)
    }

}
}