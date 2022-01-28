package cat.copernic.donate.model


class donacionesRecomendadasProvider {
    companion object{
        fun getDonacionesRecomendadas(): donacionesRecomendadasModel {
            return donacionesRecomendadas[0]
        }

       /* private val DonacionesRecomendadas = listOf<DonacionesRecomendadasModel>(
            DonacionesRecomendadasModel(R.string.reDonaciones.toString())
        )*/
        private val donacionesRecomendadas = listOf<donacionesRecomendadasModel>(
            donacionesRecomendadasModel( "Recomanació d\'aliments per a donar:\n" +
                    "        \\nFruites i vegetals sencers, en bon estat\n" +
                    "        \\nL\'envàs ha d\'estar íntegre i no estar deteriorat\n" +
                    "        \\nQue no depassi la data de caducitat\n" +
                    "        \\nL\'aliment ha d\'estar correctament identificat i etiquetat\n" +
                    "        \\nAliments que no haurien de ser donats:\n" +
                    "        \\nCarn i derivats no envasats\n" +
                    "        \\nMenjars preparats no envasades")
        )
    }


}