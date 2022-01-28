package cat.copernic.donate.ui.model

data class ticket(val titTicket: String, val descTicket: String, val emailTicket: String, val horaTicket: String, val reportedUser: String, val donacionReportada: String) {
    var cardTitTicket: String? = null
    var cardDescTicket: String? = null
    var cardUserTicket: String? = null
    var cardHoraTicket: String? = null
    var cardReportedUser: String? = null
    var cardDonacionReportada: String? = null

    init{
        this.cardTitTicket = titTicket
        this.cardDescTicket = descTicket
        this.cardUserTicket = emailTicket
        this.cardHoraTicket = horaTicket
        this.cardReportedUser = reportedUser
        this.cardDonacionReportada = donacionReportada
    }
}