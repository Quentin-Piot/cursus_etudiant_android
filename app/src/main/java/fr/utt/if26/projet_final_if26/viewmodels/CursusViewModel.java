package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

public class CursusViewModel extends AndroidViewModel {

    private CursusEtudiantRepository mRepository;

    public CursusViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
    }

    public void onSelectedCursusId(int id) {

    }

    public void handleOnClickDelCursus(Cursus cursus) {

    }

    public LiveData<List<Cursus>> getmCursus(int id) {
        return mRepository.getAllCursusFromEtudiantId(id);
    }
}
