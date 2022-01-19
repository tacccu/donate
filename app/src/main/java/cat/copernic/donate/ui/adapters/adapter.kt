package cat.copernic.donate.ui.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.R
import cat.copernic.donate.databinding.CardBinding
import cat.copernic.donate.ui.FragmentDonacionesDirections
import cat.copernic.donate.ui.model.donacion


class adapter : RecyclerView.Adapter<adapter.MyViewHolder>() {

    var donaciones: ArrayList<donacion> = ArrayList()
    lateinit var contxt: Context

    //constructor de la clase al que le pasamos la fuente de los datos y el contexto sobre el que será mostrado
    fun donacionesRecyclerAdapter(donacionesList: ArrayList<donacion>, contxt: Context) {
        this.donaciones = donacionesList
        this.contxt = contxt
    }

    //el encargado de devolver el ViewHolder configurado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return MyViewHolder(
            CardBinding.inflate(layoutInflater, parent, false)
        )

    }

    //método encargado de pasar los objetos al ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder){
            with(donaciones[position]) {
                binding.titleCtv.text = this.cardTitle
                binding.descCtv.text = this.cardDesc

            }

            holder.itemView.setOnClickListener{

                val bundle = Bundle()

                bundle.putSerializable("tituloDonacion", donaciones[position].cardTitle)
                bundle.putSerializable("descripcionDonacion", donaciones[position].cardDesc)

                holder.itemView.findNavController().navigate(
                    R.id.action_FragmentDonaciones_to_post, bundle
                )
            }

        }

        /*holder.itemView.setOnClickListener{

            val bundle = Bundle()

            bundle.putSerializable("title2", donaciones[position].cardTitle)

            holder.itemView.findNavController().navigate(
                R.id.action_FragmentDonaciones_to_post, bundle
            )
        }*/


        val currentPost = donaciones[position]
        holder.bind(currentPost)
    }

    override fun getItemCount(): Int {
        return donaciones.size
    }

    class MyViewHolder(val binding : CardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: donacion) {

        }
    }
}

/*class adapter : RecyclerView.Adapter<adapter.ViewHolder>() {
    var donaciones: MutableList<donacion> = ArrayList()
    lateinit var context: Context


    fun donacionesRecyclerAdapter(donacionesList: MutableList<donacion>, contxt: Context) {
        this.donaciones = donacionesList
        this.context = contxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            FragmentDonacionesBinding.inflate(
                layoutInflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(donaciones.get(position)) {
                binding.titleCtv.text = this.title
            }
        }
    }

    class ViewHolder(val binding: FragmentDonacionesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: donacion) {

        }
    }
}*/
