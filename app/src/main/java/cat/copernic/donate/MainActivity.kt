
package cat.copernic.donate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import cat.copernic.donate.databinding.ActivityMainBinding
import cat.copernic.donate.databinding.FragmentTldonacionesBinding

private lateinit var drawerLayout: DrawerLayout

/*class MainActivity : Fragment() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTldonacionesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tldonaciones,
            container,
            false
        )
        super.onCreate(savedInstanceState)

        //Menu
        //val bindingActMain = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //drawerLayout = bindingActMain.drawerLayout
        //val navController = this.findNavController(R.id.myNavHostFragment)
        /*NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(bindingActMain.navView, navController)*/
        return binding.root
    }

  /*  override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }*/
}*/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavHostFragment)

        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
