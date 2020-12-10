package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SemestreViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final int mSemestreId;


    public SemestreViewModelFactory(Application application, int semestreId) {
        mApplication = application;
        mSemestreId = semestreId;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SemestreViewModel(mApplication, mSemestreId);
    }
}
