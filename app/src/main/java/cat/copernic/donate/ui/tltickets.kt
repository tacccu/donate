package cat.copernic.donate.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.donate.R
import cat.copernic.donate.adapters.adapterTlTickets
import cat.copernic.donate.databinding.FragmentTlticketsBinding
import cat.copernic.donate.model.ticket
import com.google.firebase.firestore.FirebaseFirestore


class tltickets : Fragment() {
    private var postArrayList: ArrayList<ticket> = arrayListOf()
    private var postAdapter: adapterTlTickets = adapterTlTickets()
    private var db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)



        //val recyclerView = binding.RecyclerViewTlReport
        val binding = DataBindingUtil.inflate<FragmentTlticketsBinding>(
            inflater, R.layout.fragment_tltickets, container, false)

        //Los objetos de la lista deben tener el mismo tamaño
        binding.RecyclerViewTlReport.setHasFixedSize(true)

        //RecyclerView se mostrará en forma de lista
        binding.RecyclerViewTlReport.layoutManager = LinearLayoutManager(requireContext())

        //Buscamos en la colección de Donaciones la información que desplegaremos en cada CardView
        db.collection("Reportes").get().addOnSuccessListener {
                documents -> postArrayList.clear()

            for(document in documents) {
                postArrayList.add(

                    ticket(
                        document.get("tipoReporte").toString(),
                        document.get("descripcion").toString(),
                        document.get("fechaHora").toString(),
                        document.get("usuario").toString()
                    )
                )
            }
            //Generamos el adaptador
            context?.let {postAdapter.ticketRecycleAdapter(postArrayList, it)}
            //Y asignamos el adaptador al RecyclerView
            binding.RecyclerViewTlReport.adapter = postAdapter
        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_donaciones, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}