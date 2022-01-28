package cat.copernic.donate.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.R
import cat.copernic.donate.ui.adapters.adapter
import cat.copernic.donate.ui.model.donacion
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.donate.databinding.FragmentDonacionesBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase

class FragmentDonaciones : Fragment() {


    private lateinit var postRecyclerView: RecyclerView
    private var postArrayList: ArrayList<donacion> = arrayListOf()
    private var postAdapter: adapter = adapter()
    private var db = FirebaseFirestore.getInstance()

    private val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val navigationView : NavigationView = requireActivity().findViewById(R.id.navView)
        val headerView : View = navigationView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.textView4)
        val navUserEmail : TextView = headerView.findViewById(R.id.textView9)

        //Obtenemos los datos de usuario y email para mostrarlo los textview en el header del menú
        db.collection("usuarios").document(uid.toString()).get().addOnSuccessListener {
            var nom  = it.data?.get("usuario").toString()
            navUsername.text = nom
        }
        navUserEmail.text = FirebaseAuth.getInstance().currentUser?.email


        (activity as MainActivity).supportActionBar?.title = "Donaciones"

        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentDonacionesBinding>(
            inflater, R.layout.fragment_donaciones, container, false)

        //Para optimizar la creación y hacer que todas las donaciones sean del mismo tamaño
        binding.rvDonaciones.setHasFixedSize(true)
        //El RecyclerView se mostrará en forma de lista
        binding.rvDonaciones.layoutManager = LinearLayoutManager(requireContext())


        //Buscamos en la colección de Donaciones la información que desplegaremos en cada CardView
        db.collection("Donaciones").get().addOnSuccessListener {
                documents -> postArrayList.clear()

            for(document in documents) {
                postArrayList.add(

                    donacion(
                        document.get("titulo").toString(),
                        document.get("descripcion").toString(),
                        document.get("fecha").toString(),
                        document.get("email").toString(),
                        document.get("dia").toString()

                    )
                )
            }
            //Generamos el adaptador
            context?.let {postAdapter.donacionesRecyclerAdapter(postArrayList, it)}
            //Y asignamos el adaptador al RecyclerView
            binding.rvDonaciones.adapter = postAdapter
        }

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_donaciones, menu)

        val creaPost : MenuItem = menu.findItem(R.id.creaPost)

        db.collection("usuarios").document(uid).get().addOnSuccessListener {
            var spinner  = it.data?.get("spinner").toString()

            if(spinner.equals("Donatari")){
                creaPost.setVisible(false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}






