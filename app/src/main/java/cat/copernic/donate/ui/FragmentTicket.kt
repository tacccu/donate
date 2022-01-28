package cat.copernic.donate.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import cat.copernic.donate.R
import cat.copernic.donate.databinding.FragmentPostBinding
import cat.copernic.donate.databinding.FragmentTicketBinding
import com.github.dhaval2404.colorpicker.util.setVisibility
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [post.newInstance] factory method to
 * create an instance of this fragment.
 */

var TitTicket: String? = null
var DescTicket: String? = null
var UserTicket: String? = null
var HoraTicket: String? = null
var ReportedUser: String? = null
var donacionReportada: String? = null


class FragmentTicket : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding : FragmentTicketBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = arguments?.getSerializable("tituloTicket") as String?

        // Inflate the layout for this fragment
        binding = FragmentTicketBinding.inflate(layoutInflater)

        TitTicket = arguments?.getSerializable("titTicket") as String?
        DescTicket = arguments?.getSerializable("descTicket") as String?
        UserTicket = arguments?.getSerializable("emailTicket") as String?
        donacionReportada = arguments?.getSerializable("cardDonacionReportada") as String?
        HoraTicket = arguments?.getSerializable("horaTicket") as String?
        ReportedUser = arguments?.getSerializable("reportedUser") as String?

        binding.descripcion.text = DescTicket
        binding.donacionReportada.text = donacionReportada
        binding.fecha.text = HoraTicket
        binding.tipoReporte.text = TitTicket
        binding.usuario.text = UserTicket
        binding.usuarioreportado.text = ReportedUser


        return binding.root
    }


}