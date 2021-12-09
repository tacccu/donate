package cat.copernic.donate.ui.model

data class donacion(val title:String, val ubi:String, val image:String) {
    var cardTitle: String? = null
    var cardUbi: String? = null
    var cardImage: String? = null

    init {
        this.cardTitle = title
        this.cardUbi = ubi
        this.cardImage = image
    }
}
