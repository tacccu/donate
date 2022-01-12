package cat.copernic.donate.model

data class ticket(val titTicket: String, val descTicket: String, val emailTicket: String, val horaTicket: String) {
    var cardTitTicket: String? = null
    var cardDescTicket: String? = null
    var cardUserTicket: String? = null
    var cardHoraTicket: String? = null

    init{
        this.cardTitTicket = titTicket
        this.cardDescTicket = descTicket
        this.cardUserTicket = emailTicket
        this.cardHoraTicket = horaTicket
    }
}