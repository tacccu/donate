package cat.copernic.donate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cat.copernic.donate.model.AboutUsModel
import cat.copernic.donate.model.AboutUsProvider

class AboutUsViewModel : ViewModel(){

    val aboutUsModel = MutableLiveData<AboutUsModel>()

    fun putText(){
        val currentText = AboutUsProvider.returnText()
        aboutUsModel.postValue(currentText)
    }
}