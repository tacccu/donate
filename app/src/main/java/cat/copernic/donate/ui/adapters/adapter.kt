package cat.copernic.donate.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import cat.copernic.donate.ui.DonacionesList
import cat.copernic.donate.ui.model.donacion

class adapter: RecyclerView.Adapter<adapter.ViewHolder>() {
    var donaciones : MutableList<donacion> = ArrayList()
    lateinit var context: Context

    fun donacionesRecycle(donacionesList: MutableList<donacion>, contxt: Context){
        this.donaciones = donacionesList
        this.context = contxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecyclerView.ViewHolder(
            DonacionesListBinding.inflate(
                layoutInflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: adapter.ViewHolder, position: Int) {

        with(holder){
            with(donaciones.get(position)){
                binding.
            }
        }
    }
}