package cat.copernic.donate.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.R
import cat.copernic.donate.ui.adapters.adapter
import cat.copernic.donate.ui.model.donacion
import com.google.firebase.firestore.FirebaseFirestore
import cat.copernic.donate.R.layout.fragment_donaciones
import cat.copernic.donate.databinding.ActivityMainBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

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
    private lateinit var dv: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ActivityMainBinding.inflate(layoutInflater)

        postRecyclerView = binding.drawerLayout
    }
}