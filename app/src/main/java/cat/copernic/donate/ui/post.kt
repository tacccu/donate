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

var tituloDonacion : String? = null
var descripcionDonacion : String? = null
var emailDonacion : String? = null
var dateDonacion : String? = null
var fechaDonacion : String? = null


class post : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

lateinit var binding : FragmentPostBinding


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

        (activity as MainActivity).supportActionBar?.title = arguments?.getSerializable("tituloDonacion") as String?

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(layoutInflater)

        tituloDonacion = arguments?.getSerializable("tituloDonacion") as String?
        descripcionDonacion = arguments?.getSerializable("descripcionDonacion") as String?
        emailDonacion = arguments?.getSerializable("emailDonacion") as String?
        dateDonacion = arguments?.getSerializable("dateDonacion") as String?
        fechaDonacion = arguments?.getSerializable("fechaDonacion") as String?


        binding.title2.text = tituloDonacion
        binding.descripcion2.text = descripcionDonacion
        binding.textView7.text = dateDonacion
        binding.fecha.text = fechaDonacion


        val fab : FloatingActionButton = binding.floatingActionButton


        //Mandar email
        if(FirebaseAuth.getInstance().currentUser?.email != emailDonacion){
            fab.setVisibility(true)
            fab.setOnClickListener( View.OnClickListener() {
                share()
            })
        }

        setImage()


        return binding.root
    }

    private fun setImage(){
        FirebaseStorage.getInstance()
            .reference.child("images/${emailDonacion}/${tituloDonacion}")
            .listAll().addOnSuccessListener {
               for(i in it.items) {
                   i.downloadUrl.addOnSuccessListener { itUri ->
                       Glide.with(this).load(itUri).centerInside()
                           .fitCenter().into(binding.imageView3)
                   }
               }
            }


    }

    private fun share() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        val mail = arrayOf(emailDonacion)

        shareIntent.setData(Uri.parse("send to:"))
        shareIntent.setType("text/plain")

        shareIntent.putExtra(Intent.EXTRA_EMAIL, mail)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, tituloDonacion)
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share))

        startActivity(Intent.createChooser(shareIntent, "send"))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_post, menu)

        val deletePost : MenuItem = menu.findItem(R.id.delete_post)
        val createReport : MenuItem = menu.findItem(R.id.create_report)

        if(FirebaseAuth.getInstance().currentUser?.email == emailDonacion.toString()){
            deletePost.setVisible(true)
            createReport.setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()

        bundle.putSerializable("emailDonacion", emailDonacion)
        bundle.putSerializable("tituloDonacion", tituloDonacion)

        when(item.itemId){
            R.id.create_report -> findNavController().navigate(
                R.id.action_post_to_create_report, bundle
            )
        }

        return super.onOptionsItemSelected(item)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment post.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            post().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}