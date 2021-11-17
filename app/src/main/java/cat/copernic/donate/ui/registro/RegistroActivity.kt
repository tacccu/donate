package cat.copernic.donate.ui.registro

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityRegistroBinding
import cat.copernic.donate.ui.posts.MainActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_registro)

        val spinner = findViewById<Spinner>(R.id.spinnerSelecCuenta)

        val tipoLista = resources.getStringArray(R.array.tipoCuenta)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipoLista)
        
        spinner.adapter = adaptador

        binding.botonRegistro.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}