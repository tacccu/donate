package cat.copernic.donate.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.R
import cat.copernic.donate.databinding.CardBinding
import cat.copernic.donate.ui.FragmentDonacionesDirections
import cat.copernic.donate.ui.model.donacion
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL


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
        contxt = parent.context

        val layoutInflater = LayoutInflater.from(parent.context).inflate(
            R.layout.card,
            parent, false
        )

        return MyViewHolder(layoutInflater)

    }

    //método encargado de pasar los objetos al ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currDon = donaciones[position]

        with(holder){
            with(donaciones[position]) {

                holder.title.text = currDon.cardTitle
                holder.desc.text = currDon.cardDesc

            }

            holder.itemView.setOnClickListener{

                val bundle = Bundle()

                bundle.putSerializable("tituloDonacion", donaciones[position].cardTitle)
                bundle.putSerializable("descripcionDonacion", donaciones[position].cardDesc)
                bundle.putSerializable("emailDonacion", donaciones[position].cardEmail)
                bundle.putSerializable("dateDonacion", donaciones[position].cardTime)
                bundle.putSerializable("fechaDonacion", donaciones[position].cardFecha)

                holder.itemView.findNavController().navigate(
                    R.id.action_FragmentDonaciones_to_post, bundle
                )
            }

        }



        FirebaseStorage.getInstance()
            .reference.child("images/${currDon.cardEmail}/${currDon.title}")
            .listAll().addOnSuccessListener { d ->
                if (d.items.size > 0) {
                    d.items[0].downloadUrl.addOnSuccessListener { u ->
                        holder.imagen.background = getDrawable(u)
                    }
                } else {
                    holder.imagen.background = contxt.getDrawable(R.drawable.donacion)
                }
            }


        val currentPost = donaciones[position]
        holder.bind(currentPost)
    }

    fun getDrawable(u : Uri): Drawable = runBlocking(Dispatchers.IO){
        Glide.with(contxt)
            .load(u).optionalCenterCrop()
            .into(SIZE_ORIGINAL, SIZE_ORIGINAL).get()
    }

    override fun getItemCount(): Int {
        return donaciones.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: donacion) {

        }
        val title : TextView = itemView.findViewById(R.id.titleCtv)
        val desc : TextView = itemView.findViewById(R.id.descCtv)
        val imagen : ImageView = itemView.findViewById(R.id.imagenCard)
    }
}
