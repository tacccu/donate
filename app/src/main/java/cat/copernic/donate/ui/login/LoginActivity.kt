package cat.copernic.donate.ui.login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.copernic.donate.MainActivity
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityLoginBinding
import cat.copernic.donate.ui.registro.RegistroActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        super.onCreate(savedInstanceState)


        binding.buttonRegister.setOnClickListener {
            intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogIn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}

