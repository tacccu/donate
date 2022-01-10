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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [create_report.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateReport : Fragment() {
        private var __binding: FragmentCreateReportBinding? = null
        private val binding get() = __binding!!
        private var db = FirebaseFirestore.getInstance()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            __binding = FragmentCreateReportBinding.inflate(inflater, container, false)

            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = binding.spinnerSelecCuentaRazonReport

        val user = Firebase.auth.currentUser
        val uid = user?.uid
        val nomUsuario = db.collection("usuarios").document(uid.toString())

        spinner.adapter = context?.let {
            ArrayAdapter(
                it,
                R.layout.spinner_dropdown_item,
                resources.getStringArray(R.array.tipoReport)
            )
        }
        binding.fabreport.setOnClickListener(){
            if(binding.intrDescripReport.text.isNotEmpty()){
                db.collection("Reportes").document().set(hashMapOf("usuario" to nomUsuario.toString() ,"tipoReporte" to binding.spinnerSelecCuentaRazonReport.selectedItem.toString() ,"descripcion" to binding.intrDescripReport.text.toString()))
            }
        }
    }
}