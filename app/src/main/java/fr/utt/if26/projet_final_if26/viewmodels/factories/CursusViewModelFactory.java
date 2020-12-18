package fr.utt.if26.projet_final_if26.viewmodels.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class CursusViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final String mCursusLabel;
    private final String mEtudiantProgramme;


    public CursusViewModelFactory(Application application, String mCursusLabel, String mEtudiantProgramme) {
        mApplication = application;
        this.mCursusLabel = mCursusLabel;
        this.mEtudiantProgramme = mEtudiantProgramme;

    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CursusViewModel(mApplication, mCursusLabel, mEtudiantProgramme);
    }
}
