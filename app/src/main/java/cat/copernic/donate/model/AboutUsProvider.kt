package cat.copernic.donate.model

import cat.copernic.donate.R

class aboutUsProvider {
    companion object{
        fun getAboutUs(): aboutUsModel {
            return aboutus[0]
        }

        private val aboutus = listOf<aboutUsModel>(
                aboutUsModel( "DonAte", "\n L\'objectiu principal del projecte és portar la solidaritat a un punt més pròxim en la vida quotidiana, pretenem ajudar a la gent que sofreix necessitats alimentoses amb una proposta que acosta la caritat, una solució ràpida i senzilla per a realitzar donacions."
                    + "\n\nOfrecemos una solució davant una situació precària d\'algunes persones com és no poder adquirir una varietat d\'aliments bàsics i la dificultat que els suposa fer-ho."
        + "\n\nPor un altre costat volem posar remei als aliments que serien rebutjats perquè el ciutadà no vegi un aprofitament d\'aquests.", "version: 1.4.0")
        )
    }


}