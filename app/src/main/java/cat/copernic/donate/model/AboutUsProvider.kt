package cat.copernic.donate.model

class aboutUsProvider {
    companion object{
        fun getAboutUs(): aboutUsModel {
            return aboutus[0]
        }

        private val aboutus = listOf<aboutUsModel>(
                aboutUsModel( "DonAte", "El objetivo principal del proyecto es llevar la solidaridad a un punto más \n cercano en la vida cotidiana, pretendemos ayudar a la gente que sufre necesidades alimenticias con una propuesta que acerca la caridad, una solución rápida y sencilla para realizar donaciones.\n" +
                        "    \n\nOfrecemos una solución ante una situación precaria de algunas personas como es no poder adquirir una variedad de alimentos básicos y la dificultad que les supone hacerlo.\n" +
                        "    \n\nPor otro lado queremos poner remedio a los alimentos que serían desechados porque el ciudadano no vea un aprovechamiento de los mismos.", "version: 1.0.0")
        )
    }


}