package cat.copernic.donate.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class perfilViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var dbRef: DatabaseReference
    private val dbRef = Firebase.
    val user = Firebase.auth.currentUser
    val uid = user?.uid
    val userUpdPerfil = db.collection("usuarios").document(uid.toString())

    var nomUser = ""
    var telefUser = 0
    var tipoUser = ""

    init{

    }

}