package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CursusViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private int mEtudiantId;


    public CursusViewModelFactory(Application application, int etudiantId) {
        mApplication = application;
        mEtudiantId = etudiantId;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new EtudiantActivityViewModel(mApplication, mEtudiantId);
    }
}
