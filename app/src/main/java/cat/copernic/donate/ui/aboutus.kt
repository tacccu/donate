package cat.copernic.donate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityAboutusBinding
import cat.copernic.donate.viewmodel.aboutUsViewModel

class aboutus : AppCompatActivity() {

    private lateinit var binding : ActivityAboutusBinding

    private val aboutUsViewModel : aboutUsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        aboutUsViewModel.aboutUsModel.observe(this, Observer {
            binding.TitleTV.text = it.title
            binding.aboutUsTV.text = it.text
        })

        aboutUsViewModel.getText()
    }
}