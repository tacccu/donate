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
import com.google.firebase.ktx.Firebase


class Perfil : Fragment() {
    private var __binding: FragmentPerfilBinding? = null
    private val binding get() = __binding!!
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

        binding.sendUpdateButton.setOnClickListener() {
            if (user != null && binding.editTextPhone.text.isNotEmpty()) {
                val profileUpdates = userProfileChangeRequest {
                    var numTelef = binding.editTextPhone
                }

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(tag, "User profile updated.")
                        }
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