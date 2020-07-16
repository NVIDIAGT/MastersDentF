package com.example.mastersdentf.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Horario de atención 09:00 AM - 10:00 PM Lunes - Sábado");
    }

    public LiveData<String> getText() {
        return mText;
    }
}