package fr.utt.if26.projet_final_if26.models;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.dao.CursusDao;
import fr.utt.if26.projet_final_if26.models.dao.EtudiantDao;
import fr.utt.if26.projet_final_if26.models.dao.ModuleDao;
import fr.utt.if26.projet_final_if26.models.dao.SemestreDao;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;

public class CursusEtudiantRepository {

    private EtudiantDao mEtudiantDao;
    private CursusDao mCursusDao;
    private SemestreDao mSemestreDao;
    private ModuleDao mModuleDao;

    private LiveData<List<Etudiant>> mAllEtudiants;



    private CursusEtudiantDatabase db;

    public CursusEtudiantRepository(Application application) {
        db = CursusEtudiantDatabase.getDatabase(application);
        mEtudiantDao = db.etudiantDao();
        mCursusDao = db.cursusDao();
        mSemestreDao = db.semestreDao();
        mModuleDao = db.moduleDao();

    }

    public LiveData<List<Etudiant>> getAllEtudiants() {
        return mEtudiantDao.getAllEtudiants();
    }

    public void insertEtudiant(Etudiant etudiant) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mEtudiantDao.insert(etudiant);
        });
    }

    public void deleteEtudiantById(int id) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mCursusDao.deleteCursusOfEtudiant(id);
            mEtudiantDao.deleteEtudiantById(id);
        });
    }

    public LiveData<List<Cursus>> getAllCursus() {
        return mCursusDao.getAllCursus();
    }

    public void insertCursus(Cursus cursus) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mCursusDao.insert(cursus);
        });
    }

    public void deleteCursusById(int id) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mCursusDao.deleteCursusById(id);
        });
    }

    public LiveData<List<Cursus>> getAllCursusForEtudiantId(int id) {
        return mCursusDao.getCursusById(id);
    }

    public void updateCursus(Cursus cursus) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            int infos = mCursusDao.updateCursus(cursus);
        });
    }

    public void insertSemestre(Semestre semestre) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mSemestreDao.insert(semestre);
        });
    }

    public void deleteSemestreById(int id) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mSemestreDao.deleteSemestreById(id);
        });
    }

    public LiveData<List<Semestre>> getAllSemestreForCursusId(int id) {
        return mSemestreDao.getSemestreByCursusId(id);
    }

    public void updateSemestre(Semestre semestre) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mSemestreDao.updateSemestre(semestre);
        });
    }



}








