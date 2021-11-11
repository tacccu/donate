package cat.copernic.donate

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cat.copernic.donate.databinding.ActivityLoginBinding
import com.google.android.material.navigation.NavigationView

private lateinit var drawerLayout: DrawerLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_tldonaciones)

    }
}