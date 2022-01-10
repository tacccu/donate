package cat.copernic.donate.model

data class ticket(val titTicket: String, val descTicket) {
    var cardTitTicket: String? = null
    var cardDescTicket: String? = null

    init{
        this.cardTitTicket = titTicket
        this.cardDescTicket = descTicket
    }
}