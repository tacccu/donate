package cat.copernic.donate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import cat.copernic.donate.R
import cat.copernic.donate.databinding.FragmentCreateReportBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*

var emailDon : String? = null
var tituloDon : String? = null

class CreateReport : Fragment() {
        private var __binding: FragmentCreateReportBinding? = null
        private val binding get() = __binding!!
        private var db = FirebaseFirestore.getInstance()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            (activity as MainActivity).supportActionBar?.title = "Crea tu reporte"

            __binding = FragmentCreateReportBinding.inflate(inflater, container, false)

            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        emailDon = arguments?.getSerializable("emailDonacion") as String?
        tituloDon = arguments?.getSerializable("tituloDonacion") as String?

        val spinner = binding.spinnerSelecCuentaRazonReport

        val user = Firebase.auth.currentUser
        val nomUsuario = user?.email.toString()

        val currentTime = Calendar.getInstance().getTime()

        //val uid = user?.uid
        //val nomUsuario = db.collection("usuarios").document(uid.toString()).get("usuario")

        spinner.adapter = context?.let {
            ArrayAdapter(
                it,
                R.layout.spinner_dropdown_item,
                resources.getStringArray(R.array.tipoReport)
            )
        }
        binding.fabreport.setOnClickListener(){
            if(binding.intrDescripReport.text.isNotEmpty()){
                db.collection("Reportes").document().set(hashMapOf("fechaHora" to currentTime.toString(),
                    "usuario" to nomUsuario,
                    "tipoReporte" to binding.spinnerSelecCuentaRazonReport.selectedItem.toString() ,
                    "descripcion" to binding.intrDescripReport.text.toString(),
                    "usuarioReportado" to emailDonacion,
                    "donacionReportada" to tituloDon))
            }
        }
    }
}