package cat.copernic.donate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cat.copernic.donate.model.aboutUsModel
import cat.copernic.donate.model.aboutUsProvider

class AboutUsViewModel : ViewModel(){

    val aboutUsModel = MutableLiveData<aboutUsModel>()

    fun getText(){
        val currentText : aboutUsModel = aboutUsProvider.getAboutUs()

        aboutUsModel.postValue(currentText)
    }
}