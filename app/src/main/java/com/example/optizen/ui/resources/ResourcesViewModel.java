package com.example.optizen.ui.resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResourcesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ResourcesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is resources fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
