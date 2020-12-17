package fr.utt.if26.projet_final_if26.viewmodels.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import fr.utt.if26.projet_final_if26.viewmodels.SemestreViewModel;

public class SemestreViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final int mSemestreId;
    private final String mCursusLabel;


    public SemestreViewModelFactory(Application application, int semestreId, String cursusLabel) {
        mApplication = application;
        mSemestreId = semestreId;
        mCursusLabel = cursusLabel;

    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SemestreViewModel(mApplication, mSemestreId, mCursusLabel);
    }
}
