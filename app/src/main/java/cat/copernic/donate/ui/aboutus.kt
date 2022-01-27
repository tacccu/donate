package cat.copernic.donate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cat.copernic.donate.databinding.ActivityAboutusBinding
import cat.copernic.donate.viewmodel.AboutUsViewModel

class aboutus : AppCompatActivity() {

    private lateinit var binding : ActivityAboutusBinding

    private val AboutUsViewModel : AboutUsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AboutUsViewModel.aboutUsModel.observe(this, Observer {
            binding.TitleTV.text = it.title
            binding.aboutUsTv.text = it.text
            binding.version.text = it.version
        })

        AboutUsViewModel.getText()
    }
}