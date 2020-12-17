package fr.utt.if26.projet_final_if26.viewmodels.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class EtudiantViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final int mEtudiantId;


    public EtudiantViewModelFactory(Application application, int etudiantId) {
        mApplication = application;
        mEtudiantId = etudiantId;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EtudiantViewModel(mApplication, mEtudiantId);
    }
}
