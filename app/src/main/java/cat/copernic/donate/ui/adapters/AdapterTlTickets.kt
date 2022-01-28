package cat.copernic.donate.ui.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.donate.R
import cat.copernic.donate.ui.adapters.adapterTlTickets.*
import cat.copernic.donate.ui.model.ticket
import cat.copernic.donate.databinding.TicketCardviewBinding

class adapterTlTickets : RecyclerView.Adapter<MyViewHolder>() {

    var tickets : ArrayList<ticket> = ArrayList()
    lateinit var contxt: Context

    fun ticketRecycleAdapter(ticketsList: ArrayList<ticket>, contxt: Context){
        this.tickets = ticketsList
        this.contxt = contxt
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)


        return MyViewHolder(
            TicketCardviewBinding.inflate(layoutInflater, parent, false)
        )


    }
    //método que pasa los objetos al viewholder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(tickets[position]){
                binding.emailReport.text = this.cardUserTicket
                binding.horaReport.text = this.cardHoraTicket
                binding.razonReport.text = this.cardTitTicket
                binding.descripcionReport.text = this.cardDescTicket
                binding.reportedUser.text = this.cardReportedUser
                binding.donacionReportada.text = this.cardDonacionReportada
            }

            holder.itemView.setOnClickListener{

                val bundle = Bundle()

                bundle.putSerializable("titTicket", tickets[position].cardTitTicket)
                bundle.putSerializable("descTicket", tickets[position].cardDescTicket)
                bundle.putSerializable("emailTicket", tickets[position].cardUserTicket)
                bundle.putSerializable("horaTicket", tickets[position].cardHoraTicket)
                bundle.putSerializable("reportedUser", tickets[position].cardReportedUser)
                bundle.putSerializable("cardDonacionReportada", tickets[position].cardDonacionReportada)


                holder.itemView.findNavController().navigate(
                    R.id.action_tltickets_to_fragmentTicket2, bundle
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return tickets.size
    }


    class MyViewHolder(val binding: TicketCardviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: ticket) {

        }
    }
}