package cat.copernic.donate.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.FragmentActivity
import cat.copernic.donate.R


class Perfil : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val t = inflater.inflate(R.layout.fragment_perfil, container, false)
        val spinner = t.findViewById<Spinner>(R.id.spinnerSelecCuentaPerfil)
        spinner.adapter = context?.let {
            ArrayAdapter(
                it,
                R.layout.spinner_dropdown_item,
                resources.getStringArray(R.array.tipoCuenta)
            )
        }

        // Inflate the layout for this fragment
        return t
    }
}