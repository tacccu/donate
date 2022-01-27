package cat.copernic.donate.ui.model

data class donacion(val title:String, val desc:String, val time:String, val email:String, val dia:String) {
    var cardTitle: String? = null
    var cardDesc: String? = null
    var cardTime: String? = null
    var cardEmail: String? = null
    var cardFecha: String? = null

    init {
        this.cardTitle = title
        this.cardDesc = desc
        this.cardTime = time
        this.cardEmail = email
        this.cardFecha = dia
    }
}