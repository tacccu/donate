package cat.copernic.donate.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.R
import cat.copernic.donate.ui.adapters.adapter
import cat.copernic.donate.ui.model.donacion
import cat.copernic.donate.R.layout.fragment_donaciones
import cat.copernic.donate.databinding.FragmentDonaciones
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.donate.databinding.ActivityLoginBinding.inflate
import com.google.firebase.firestore.*

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


    //----------------------------------------------------------------------------------------------------


    private lateinit var postRecyclerView: RecyclerView
    private lateinit var postArrayList: ArrayList<donacion>
    private lateinit var postAdapter: adapter
    private lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val binding = FragmentDonaciones.inflate(layoutInflater)

        //postRecyclerView = binding.
        postRecyclerView.layoutManager = LinearLayoutManager(context)
        postRecyclerView.setHasFixedSize(true)
        postArrayList = arrayListOf()
        //postAdapter = postAdapter(postArrayList)

        eventChangeListener()

        //return binding.root
        return null
    }

    private fun eventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("posts").addSnapshotListener(object : EventListener<QuerySnapshot> {
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
    }
}




