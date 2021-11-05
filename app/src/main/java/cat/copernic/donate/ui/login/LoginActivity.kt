package cat.copernic.donate.ui.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityLoginBinding
import cat.copernic.donate.ui.registro.RegistroActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        binding.botonRegistro.setOnClickListener {
            Log.i("LoginActivity", "He clicado boton registro")
            intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }



    }


}