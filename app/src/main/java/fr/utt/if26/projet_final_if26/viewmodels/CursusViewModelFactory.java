package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CursusViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String mCursusLabel;


    public CursusViewModelFactory(Application application, String mCursusLabel) {
        mApplication = application;
        this.mCursusLabel = mCursusLabel;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CursusViewModel(mApplication, mCursusLabel);
    }
}
