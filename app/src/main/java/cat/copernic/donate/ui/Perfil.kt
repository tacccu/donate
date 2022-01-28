package cat.copernic.donate.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import cat.copernic.donate.R
import cat.copernic.donate.databinding.FragmentPerfilBinding
import cat.copernic.donate.viewmodel.fragmentDonacionesViewModel
import cat.copernic.donate.viewmodel.perfilViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class Perfil : Fragment() {
    private var __binding: FragmentPerfilBinding? = null
    private val binding get() = __binding!!
    private var db = FirebaseFirestore.getInstance()
    val user = Firebase.auth.currentUser
    val uid = user?.uid


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Perfil"

        var database: DatabaseReference = Firebase.database.reference


        db.collection("usuarios").document(uid.toString()).get().addOnSuccessListener {
            var nom  = it.data?.get("usuario").toString()
            binding.editTextUserPerfil.hint = nom
            binding.nomUsuari?.text = nom
        }

        db.collection("usuarios").document(uid.toString()).get().addOnSuccessListener {
            var telefon  = it.data?.get("numTelef").toString()
            binding.editTextPhone.hint = telefon

        }

        __binding = FragmentPerfilBinding.inflate(inflater, container, false)

        //binding.editTextUserPerfil.setHint(database.child("usuarios").child(uid.toString()).child("usuario").get().toString())
        binding.editTextUserPerfil.setHint(database.child("numTelef").child(uid.toString()).get().toString())


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendUpdateButton.setOnClickListener() {
            if (user != null ) {// si hay un usuario logeado podremos modificar su información de perfil
                val userUpdPerfil = db.collection("usuarios").document(uid.toString())
                val textSuccess: String = getString(R.string.success)
                val textFailure: String = getString(R.string.error)


                if(binding.editTextUserPerfil.text.toString().isNotEmpty()){//nombre usuario
                    userUpdPerfil.update("usuario", binding.editTextUserPerfil.text.toString()).addOnSuccessListener {
                        Toast.makeText(context, textSuccess, Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(context, textFailure, Toast.LENGTH_SHORT).show()
                    }
                }
                if(binding.editTextPhone.text.toString().isNotEmpty()){//teléfono
                    userUpdPerfil.update("numTelef", binding.editTextPhone.text.toString()).addOnSuccessListener {
                        Toast.makeText(context, textSuccess, Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(context, textFailure, Toast.LENGTH_SHORT).show()
                    }
                }

                view.findNavController().navigate(PerfilDirections.actionPerfilToFragmentDonaciones())
            }
        }

    }

    override fun onDestroyView(){
        super.onDestroyView()
        __binding = null
    }
}