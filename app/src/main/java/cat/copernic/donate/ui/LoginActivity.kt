package cat.copernic.donate.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityLoginBinding
import cat.copernic.donate.ui.ReContraActivity
import cat.copernic.donate.ui.registro.RegistroActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_DonAte)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        super.onCreate(savedInstanceState)

        // Get the viewModel
        //var viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

        //setup
        setup()

    }

    private fun setup(){

        title = "Login"

        binding.buttonRegister.setOnClickListener {
            intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }

        var cr : Job? = null
        binding.buttonLogIn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)

            cr = create(
                5,
                binding.buttonLogIn,
                binding.bar
            )
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
            intent = Intent(this, ReContraActivity::class.java)
            startActivity(intent)
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
        builder.setMessage("las credenciales no coinciden")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun create(time: Int, but : Button, bar : ProgressBar) = GlobalScope.launch(Dispatchers.Main) {
        bar.setVisibility(View.VISIBLE)
        bar.progress = 0

        withContext(Dispatchers.IO) {
            var i = 0

            while(i < time) {
                if (sus((time * 50).toLong())) {
                    i++
                    bar.progress = (i * 50) / time
                }
            }
        }
    }

    private suspend fun sus(time: Long): Boolean {
        delay(time)
        return true
    }

}