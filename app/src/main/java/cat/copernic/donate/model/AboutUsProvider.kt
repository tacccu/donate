package cat.copernic.donate.model

import cat.copernic.donate.R

class aboutUsProvider {
    companion object{
        fun getAboutUs(): aboutUsModel {
            return aboutus[0]
        }

        private val aboutus = listOf<aboutUsModel>(
                aboutUsModel( R.string.app_name.toString(), R.string.AboutUs.toString(), "version: 1.1.0")
        )
    }


}