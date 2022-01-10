package cat.copernic.donate.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.CardViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.databinding.ActivityLoginBinding.inflate
import cat.copernic.donate.model.ticket

class adapterTlTickets : RecyclerView.Adapter<adapterTlTickets.MyViewHolder>() {
    var tickets = ArrayList<ticket> = ArrayList()

    fun ticketRecycleAdapter(ticketsList: ArrayList<ticket>, contxt: Context){
        this.tickets = ticketsList
        this.contxt = contxt
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterTlTickets.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return MyViewHolder(
            adapterTlTickets.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(tickets[position]){

            }
        }
    }

    class MyViewHolder(val binding: CardViewBindingAdapter) : RecyclerView.viewHolder(binding.root) {
        fun bind(ticket)
    }
}