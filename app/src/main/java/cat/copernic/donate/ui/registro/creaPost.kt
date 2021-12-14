package cat.copernic.donate.ui.registro

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import cat.copernic.donate.R
import cat.copernic.donate.databinding.FragmentCreaPostBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [creaPost.newInstance] factory method to
 * create an instance of this fragment.
 */
class creaPost : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    lateinit var imagen : ImageView
    lateinit var storageRef: StorageReference

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
    ): View {

        val binding: FragmentCreaPostBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_crea_post, container, false
        )


        binding.floatingActionButton.setOnClickListener{view : View ->
            if(binding.tituloEditText.text.isNotEmpty()
                && binding.descripcionEditText.text.isNotEmpty()
                && binding.timeEditText.text.isNotEmpty()){

                    db.collection("Donaciones").add(hashMapOf(
                        "titulo" to binding.tituloEditText.text.toString(),
                        "descripcion" to binding.descripcionEditText.text.toString(),
                        "fecha" to binding.timeEditText.text.toString()

                    ))
                view.findNavController().navigate(creaPostDirections.actionCreaPostToFragmentDonaciones())
            } else {
                showAlert()
            }

        }

        binding.addImage.setOnClickListener{ view: View ->
            ponerImagen(view)
        }
        return binding.root
    }


    fun ponerImagen(view: View) {
        val pathReference = storageRef.child("images/donate.jpg")
        val bitmap = (imagen.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = pathReference.putBytes(data)
        uploadTask.addOnFailureListener{
            Snackbar.make(view, R.string.error, Snackbar.LENGTH_LONG).show()
        }.addOnSuccessListener {
            Snackbar.make(view, R.string.success, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Todos los campos son obligatorios!!!")
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment creaPost.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            creaPost().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}