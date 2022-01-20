package cat.copernic.donate.ui.model

data class donacion(val title:String, val desc:String, val time:String) {
    var cardTitle: String? = null
    var cardDesc: String? = null
    var cardTime: String? = null
    //var cardImage: String? = null

    init {
        this.cardTitle = title
        this.cardDesc = desc
        this.cardTime = time
    }
}

/*data class donacion(
    var title : String ?= null,
    var desc : String ?= null
)*/