package uz.texnopos.myapplicationdebtbooks

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Transaction
import com.google.firebase.firestore.model.DocumentKey
import org.koin.core.component.getScopeId
import org.koin.core.component.getScopeName
import uz.texnopos.myapplicationdebtbooks.di.dataModule
import java.util.*
import java.util.function.DoubleBinaryOperator
import kotlin.collections.HashMap

class AddandReadHelper(private val db: FirebaseFirestore) {
    fun add(
        name: String,
        amount: Double,
        coment: String,
        date: Long,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        check(name, amount, coment, date, onSuccess, onFailure)
    }

    fun read(
        name: String,
        amount: Double,
        coment: String,
        date: Long,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        check(name, -1 * amount, coment, date, onSuccess, onFailure)
    }

    private fun check(
        name: String,
        amount: Double,
        coment: String,
        date: Long,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {

        db.collection(Object.CONTACT).whereEqualTo("name", name).get()
            .addOnSuccessListener {
                if (it.documents.isEmpty()) {
                    create(name, amount, coment, date, onSuccess, onFailure)
                } else {
                    var documentId = it.documents[0].data!!.getValue("id").toString()
                    var balance = it.documents[0].data!!.getValue("balance").toString().toDouble()
                    update(documentId, balance, name, amount, coment, date, onSuccess, onFailure)
                }
            }
            .addOnFailureListener {

            }
    }

    private fun create(
        name: String,
        amount: Double,
        coment: String,
        date: Long,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        var id = UUID.randomUUID().toString()
        var d = hashMapOf<String, Any>()
        d["name"] = name
        d["id"] = id
        d["balance"] = amount
        db.collection(Object.CONTACT).document(d["id"].toString()).set(d)
            .addOnSuccessListener {
                addTransaction(d["id"].toString(), name, amount, coment, date, onSuccess, onFailure)
            }
            .addOnFailureListener {
                onFailure.invoke(it.message)
            }

    }

    private fun addTransaction(
        id: String,
        name: String,
        amount: Double,
        coment: String,
        date: Long,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        var t = hashMapOf<String, Any>()
        t["name"] = name
        t["id"] = UUID.randomUUID().toString()
        t["balance"] = amount
        t["date"] = date
        t["coment"] = coment
        db.collection(Object.CONTACT).document(id).collection(Object.TRANSACTION)
            .document(t["id"].toString()).set(t)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.message)
            }
    }

    private fun update(
        documentId: String,
        balance: Double,
        name: String,
        amount: Double,
        coment: String,
        date: Long,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {

        db.collection(Object.CONTACT).document(documentId).update("balance", balance + amount)
            .addOnSuccessListener {
                addTransaction(documentId, name, amount, coment, date, onSuccess, onFailure)
            }
            .addOnFailureListener {
                onFailure.invoke(it.message)
            }
    }

}
