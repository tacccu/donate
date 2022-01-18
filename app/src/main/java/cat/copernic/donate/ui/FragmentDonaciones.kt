package cat.copernic.donate.ui

import android.os.Bundle
import android.util.Log
import android.view.*
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase

class FragmentDonaciones : Fragment() {


    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_donaciones, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_donaciones, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }*/


    //----------------------------------------------------------------------------------------------


    private lateinit var postRecyclerView: RecyclerView
    private var postArrayList: ArrayList<donacion> = arrayListOf()
    private var postAdapter: adapter = adapter()
    private var db = FirebaseFirestore.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
                        document.get("fecha").toString()
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.loginActivity -> {
                FirebaseAuth.getInstance().signOut()

                NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                        || super.onOptionsItemSelected(item)
            }

            else -> NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                    || super.onOptionsItemSelected(item)
        }


    }

    /*private fun eventChangeListener() {



        db = FirebaseFirestore.getInstance()
        db.collection("Donaciones").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firestore Error", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        postArrayList.add(dc.document.toObject(donacion::class.java))

                    }
                }
                postAdapter.notifyDataSetChanged()
            }
        })


    }*/
}






