package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

public class EtudiantViewModel extends AndroidViewModel {

    public final LiveData<List<Etudiant>> mEtudiants;


    private CursusEtudiantRepository mRepository;
    public EtudiantViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        mEtudiants = mRepository.getAllEtudiants();
    }
}
