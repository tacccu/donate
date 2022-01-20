package cat.copernic.donate.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityRecontraBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ReContraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecontraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recontra)

        var emailAddress = binding.emailReContra.text.toString()
        if(emailAdress != null || !emailAddress.isEmpty()){
            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                    }
                }
        }
    }
}