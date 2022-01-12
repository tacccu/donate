package cat.copernic.donate.ui.registro

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import cat.copernic.donate.R
import cat.copernic.donate.databinding.FragmentCreaPostBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.io.File

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
    lateinit var imagen: ImageView
    lateinit var storageRef: StorageReference
    lateinit var binding: FragmentCreaPostBinding
    private var latestTempUri: Uri? = null
    var iden: String? = null
    private val array : ArrayList<Uri?> = arrayListOf()

    private val channelId = "channelId"
    private val channelName = "channelName"
    private val notificationId = 1




    val tomarImgResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSucces ->
            if (isSucces) {
                latestTempUri?.let { uri ->
                    binding.imageView6.setImageURI(uri)
                }
            }
        }

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

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_crea_post, container, false
        )
        createNotificationChannel()

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Titulo")
            .setContentText("Content")
            .setSmallIcon(R.drawable.logoicono)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        //Botón para crear las donaciones
        binding.floatingActionButton.setOnClickListener { view: View ->
            //control de variables para que no se creen documentos vacíos
            if (binding.tituloEditText.text.isNotEmpty()
                && binding.descripcionEditText.text.isNotEmpty()
                && binding.timeEditText.text.isNotEmpty()
            ) {
               //creo una donación en la ruta de Donacioens
               val document = db.collection("Donaciones")
                    //añadimos la información de los campos de texto
                    document.add(
                    hashMapOf(
                        "titulo" to binding.tituloEditText.text.toString(),
                        "descripcion" to binding.descripcionEditText.text.toString(),
                        "fecha" to binding.timeEditText.text.toString()

                    )
                )
                //variable con la id del documento para nombrar la imagen
                iden = document.id

                ponerImagen(view)

                view.findNavController()
                    .navigate(creaPostDirections.actionCreaPostToFragmentDonaciones())

                notificationManager.notify(notificationId, notification)


            } else {
                showAlert()
            }

        }
        //botón para que salte la selección
        binding.addImage.setOnClickListener { view: View ->
            seleccion()
        }



        return binding.root
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                enableLights(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }

    //función para seleccionar entre abrir la galería o abrir la cámara
    fun seleccion() {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage("Abrir cámara o galería")
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "Cámara"
        ) {dialog, wich -> abrirCamara()}
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "Galería"
        ) {dialog, wich -> abrirGaleria()}
        alertDialog.show()
    }

    //función para subir la imagen a Firebase
    fun ponerImagen(view: View) {
        //para el nombre de la imagen le pasaremos el identificador de la donación, de esta forma ninguna imagen se llamará igual
        storageRef = FirebaseStorage.getInstance().reference
        val pathReference = storageRef.child("images/${this.iden}")
        val bitmap = (binding.imageView6.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = pathReference.putBytes(data)
        uploadTask.addOnFailureListener {
            Snackbar.make(view, R.string.error, Snackbar.LENGTH_LONG).show()
        }.addOnSuccessListener {
            Snackbar.make(view, R.string.success, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Todos los campos son obligatorios!!!")
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private val startActivityGal = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data

            if(data != null) {
                Log.e("nom", "$data")
                array.add(data)
                binding.imageView6.setImageURI(array[0])
            }



        }
    }

    //función para abrir la galería
    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityGal.launch(intent)
    }
    //función para abrir la cámara
    private fun abrirCamara() {
        lifecycleScope.launchWhenStarted {
            getTempFile().let { uri ->
                latestTempUri = uri

                tomarImgResult.launch(uri)
            }
        }
    }


    private fun getTempFile(): Uri? {
        val tempFile = File.createTempFile("tmp_image_file", ".png", activity?.cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }
        return activity?.let {
            FileProvider.getUriForFile(
                it.applicationContext,
                "cat.copernic.donate.provider",
                tempFile
            )
        }
    }



    }


    private object n {
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


