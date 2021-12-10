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
import com.google.firebase.auth.FirebaseAuth
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
        val uid = user?.uid


        binding.sendUpdateButton.setOnClickListener() {
            if (user != null ) {// si hay un usuario logeado podremos modificar su información de perfil
                Log.e(tag, "ENTRO ENTRO ENTRO ENTRO\nENTRO ENTRO ENTRO")
                val userUpdPerfil = db.collection("usuarios").document(uid.toString())
                
                if(binding.editTextTextPersonName5.text.toString().isNotEmpty()){//nombre usuario
                    Log.e(tag, "ENTRO ENTRO ENTRO ENTRO\nENTRO ENTRO ENTRO")
                    userUpdPerfil.update("usuario", binding.editTextTextPersonName5.text.toString())
                }
                if(binding.editTextTextEmailAddress.text.toString().isNotEmpty()){//email
                    userUpdPerfil.update("usuario", binding.editTextTextEmailAddress.text.toString())
                }
                if(binding.editTextPhone.text.toString().isNotEmpty()){//teléfono
                    userUpdPerfil.update("numTelef", binding.editTextPhone.text.toString())
                }
                if(binding.spinnerSelecCuentaPerfil.selectedItem != db.collection("usuarios").document(uid.toString()).get()){
                    userUpdPerfil.update("tipoCuent", binding.spinnerSelecCuentaPerfil.selectedItem.toString())
                    Log.e(tag, "ENTRO ENTRO ENTRO ENTRO\nTIPO CUENTA TIPO CUENTA    ")
                    //BUSCAR COMO COMPARAR QUE LOS VALORES SEAN SIMILARES POR ID
                }
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