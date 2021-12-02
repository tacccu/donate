package cat.copernic.donate.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import cat.copernic.donate.R
import cat.copernic.donate.databinding.FragmentPerfilBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class Perfil : Fragment() {
    private var __binding: FragmentPerfilBinding? = null
    private val binding get() = __binding!!
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        __binding = FragmentPerfilBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Firebase.auth.currentUser
        val uid = user?.getEmail()

        binding.sendUpdateButton.setOnClickListener() {
            if (user != null ) {
                val userUpdNumTelf = db.collection("usuarios").document(uid.toString())

                if(binding.editTextTextPersonName5.text.toString().isNotEmpty()){
                    userUpdNumTelf.update("usuario", binding.editTextTextPersonName5.text.toString())
                }
                /*if(binding.editTextTextEmailAddress.text.toString().isNotEmpty()){
                    user!!.updateEmail(binding.editTextTextEmailAddress.text.toString())
                }*/
                if(binding.editTextPhone.text.toString().isNotEmpty()){
                    userUpdNumTelf.update("numTelef", binding.editTextPhone.text.toString())
                }
                /*if(binding.editTextPhone.text.toString().isNotEmpty()){
                    userUpdNumTelf.update("numTelef", binding.spinnerSelecCuentaPerfil.get)
                }*/
            }
        }

        val spinner = binding.spinnerSelecCuentaPerfil
        spinner.adapter = context?.let {
            ArrayAdapter(
                it,
                R.layout.spinner_dropdown_item,
                resources.getStringArray(R.array.tipoCuenta)
            )
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        __binding = null
    }
}