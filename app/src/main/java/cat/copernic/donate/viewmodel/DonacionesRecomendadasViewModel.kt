package cat.copernic.donate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cat.copernic.donate.model.donacionesRecomendadasModel
import cat.copernic.donate.model.donacionesRecomendadasProvider

class DonacionesRecomendadasViewModel : ViewModel(){

    val donacionesRecomendadasModel = MutableLiveData<donacionesRecomendadasModel>()

    fun getText(){
        val currentText : donacionesRecomendadasModel = donacionesRecomendadasProvider.getDonacionesRecomendadas()

        donacionesRecomendadasModel.postValue(currentText)
    }
}