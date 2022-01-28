package cat.copernic.donate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cat.copernic.donate.databinding.ActivityDonacionesRecomendadasBinding
import cat.copernic.donate.viewmodel.DonacionesRecomendadasViewModel

class ReDonacionesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDonacionesRecomendadasBinding

    private val donacionesRecomendadasViewModel : DonacionesRecomendadasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDonacionesRecomendadasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        donacionesRecomendadasViewModel.donacionesRecomendadasModel.observe(this, Observer {
            binding.ReDonacionestxt.text = it.ReDonacionestxt
        })

        donacionesRecomendadasViewModel.getText()
    }
}