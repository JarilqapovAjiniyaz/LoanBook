package uz.texnopos.myapplicationdebtbooks

import com.google.firebase.firestore.FirebaseFirestore

class ContactTransaction(private val db: FirebaseFirestore) {

    fun getALLContacts(onSuccess: (result : List<CollectionUser>)-> Unit, onFailure : (msg: String)-> Unit) {
        db.collection(Object.CONTACT).get()
            .addOnSuccessListener {
                var result: MutableList<CollectionUser> = mutableListOf()
                it.documents.forEach { doc ->
                    result.add(doc.toObject(CollectionUser::class.java)!!)

                }
                onSuccess.invoke(result)


            }
            .addOnFailureListener {
            onFailure.invoke(it.message.toString())
            }
    }
}