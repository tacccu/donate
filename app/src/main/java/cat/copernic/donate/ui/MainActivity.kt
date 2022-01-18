
package cat.copernic.donate.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import cat.copernic.donate.R
import cat.copernic.donate.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


private lateinit var drawerLayout: DrawerLayout

enum class ProviderType {
    BASIC
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)

        binding = setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController : NavController = navHostFragment.navController

        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        binding.navView.menu.getItem(5).setOnMenuItemClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            FirebaseAuth.getInstance().signOut()
            startActivity(intent)
            this.finish()
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


}
