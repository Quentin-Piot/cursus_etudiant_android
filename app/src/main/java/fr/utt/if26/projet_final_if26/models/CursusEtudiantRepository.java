package fr.utt.if26.projet_final_if26.models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.dao.CursusDao;
import fr.utt.if26.projet_final_if26.models.dao.EtudiantDao;
import fr.utt.if26.projet_final_if26.models.dao.ModuleDao;
import fr.utt.if26.projet_final_if26.models.dao.SemestreDao;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

public class CursusEtudiantRepository {

    private EtudiantDao mEtudiantDao;
    private CursusDao mCursusDao;
    private SemestreDao mSemestreDao;
    private ModuleDao mModuleDao;

    private LiveData<List<Etudiant>> mAllEtudiants;

    public CursusEtudiantRepository(Application application) {
        CursusEtudiantDatabase db = CursusEtudiantDatabase.getDatabase(application);
        mEtudiantDao = db.etudiantDao();
        mCursusDao = db.cursusDao();
        mSemestreDao = db.semestreDao();
        mModuleDao = db.moduleDao();

    }

    public LiveData<List<Etudiant>> getAllEtudiants() {
        return mEtudiantDao.getAllEtudiants();
    }


}



