package cat.copernic.donate.ui.login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityLoginBinding
import cat.copernic.donate.ui.registro.RegistroActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    //private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //Thread.sleep(2000) --> Comando para ralentizar la app y ver mejor el SplashScreen
        //setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        //val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_login)

        //val navController = this.findNavController(R.id.myNavHostFragment)
        //drawerLayout = binding.drawerLayout

        binding.buttonRegister.setOnClickListener {
            intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        //binding.RegisterButton.setOnClickListener {
        //    Log.i("LoginActivity", "He clicado boton registro")
        //    intent = Intent(this, RegistroActivity::class.java)
        //    startActivity(intent)
        //}

        //Menu start here
        //NavigationUI.setupWithNavController(binding.navView, navController)

    }

    //override fun onSupportNavigateUp(): Boolean {
        //val navController = this.findNavController(R.id.myNavHostFragment)
        //return NavigationUI.navigateUp(navController, drawerLayout)
    //}
}