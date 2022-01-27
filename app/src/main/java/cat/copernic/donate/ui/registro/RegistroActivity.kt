package cat.copernic.donate.ui.registro

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.copernic.donate.ui.MainActivity
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityRegistroBinding
import cat.copernic.donate.ui.ProviderType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registro)

        val spinner = findViewById<Spinner>(R.id.spinnerSelecCuenta)

        val tipoLista = resources.getStringArray(R.array.tipoCuenta)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipoLista)

        spinner.adapter = adaptador


        //setup
        setupR()
    }



    private fun setupR() {

        binding.botonRegistro.setOnClickListener {


            if(binding.intrEmailRegist.text.isNotEmpty()
                && binding.editTextUser.text.isNotEmpty()
                && binding.editTextTelef.text.isNotEmpty()
                && binding.editTextContra.text.isNotEmpty()
                && binding.editTextContraAgain.text.isNotEmpty()){
                    if (binding.editTextContra.text.toString() == binding.editTextContraAgain.text.toString()){
                        FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(binding.intrEmailRegist.text.toString(), binding.editTextContra.text.toString())
                            .addOnCompleteListener{
                                if(it.isSuccessful) {
                                    showMainR(it.result?.user?.email?: "", ProviderType.BASIC)
                                } else {
                                    showAlert()
                                }
                            }


                    } else {
                        showAlert2()
                    }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Ha habido un error registrando")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert2() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Las contraseñas no coinciden")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showMainR(email: String, provider: ProviderType){
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("usuarios").document(uid).set(
            hashMapOf(
                "numTelef" to binding.editTextTelef.text.toString(),
                "usuario" to binding.editTextUser.text.toString(),
                "email" to binding.intrEmailRegist.text.toString(),
                "spinner" to binding.spinnerSelecCuenta.selectedItem.toString()

            )
        )
        startActivity(intent)
    }

}