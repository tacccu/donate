package cat.copernic.donate.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import cat.copernic.donate.R
import cat.copernic.donate.ui.model.donacion

class adapter(private val donaciones : ArrayList<donacion>) : RecyclerView.Adapter<adapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){

        val currentPost = donaciones[position]

        holder.title.text = currentPost.title
        holder.desc.text = currentPost.desc
    }

    override fun getItemCount(): Int {
        return donaciones.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.titleCtv)
        val desc : TextView = itemView.findViewById(R.id.descCtv)
    }
}