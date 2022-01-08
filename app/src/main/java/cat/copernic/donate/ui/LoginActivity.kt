package cat.copernic.donate.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityLoginBinding
import cat.copernic.donate.ui.MainActivity
import cat.copernic.donate.ui.ProviderType
import cat.copernic.donate.ui.RegistroActivity
import cat.copernic.donate.viewmodel.LoginActivityViewModel
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        //setTheme(R.style.Theme_DonAtelogin)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        super.onCreate(savedInstanceState)

        // Get the viewModel
        var viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

        //setup
        setup()

    }

    private fun setup(){

        binding.buttonRegister.setOnClickListener {
            intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }

        binding.buttonLogIn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)

            if(binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty() ){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful) {
                            showMainL(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        binding.olvidadoContra.setOnClickListener {

        }
    }

    private fun showMainL(email: String, provider: ProviderType){
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Ha habido un error iniciando sesion")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}