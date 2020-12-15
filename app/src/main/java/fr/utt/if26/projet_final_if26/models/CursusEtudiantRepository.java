package fr.utt.if26.projet_final_if26.models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.dao.CursusDao;
import fr.utt.if26.projet_final_if26.models.dao.EtudiantDao;
import fr.utt.if26.projet_final_if26.models.dao.ModuleDao;
import fr.utt.if26.projet_final_if26.models.dao.SemestreDao;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;

public class CursusEtudiantRepository {

    private final EtudiantDao mEtudiantDao;
    private final CursusDao mCursusDao;
    private final SemestreDao mSemestreDao;
    private final ModuleDao mModuleDao;


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

    public void insertEtudiant(Etudiant etudiant) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mEtudiantDao.insert(etudiant);
        });
    }

    public void deleteEtudiantById(int id) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
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

    public void deleteCursusByLabel(String label) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mCursusDao.deleteCursusByLabel(label);
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

    public void deleteSemestre(Semestre semestre) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mSemestreDao.deleteSemestre(semestre);
        });
    }

    public void duplicateCursus(Cursus cursus, List<Semestre> semestres) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {

            long result = mCursusDao.insert(cursus);
            if (result > -1) {
                semestres.forEach(semestre -> {
                    Semestre newSemestre = new Semestre(semestre.getLabel(), cursus.getLabel());
                    long id = mSemestreDao.insert(newSemestre);
                    semestre.getListeModules().forEach(module -> {
                        module.setSemestreId((int) id);
                        mModuleDao.insert(module);
                    });

                });
            }
            ;
        });

    }

    public void updateNpmlFieldItem(boolean checked, int id) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mSemestreDao.updateNpmlField(checked, id);
        });
    }

    public void updateSeFieldItem(boolean checked, int id) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {

            mSemestreDao.updateSeField(checked, id);
        });
    }

    public LiveData<Boolean> getNpmlFieldForSemestreId(int semestreId) {
        return mSemestreDao.getNpml(semestreId);
    }

    public LiveData<Boolean> getSeFieldForSemestreId(int semestreId) {
        return mSemestreDao.getSe(semestreId);
    }

    public LiveData<List<Semestre>> getAllSemestreForCursusLabel(String mCursusLabel) {
        return mSemestreDao.getSemestreByCursusLabel(mCursusLabel);
    }

    public void updateSemestre(Semestre semestre) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mSemestreDao.updateSemestre(semestre);
        });
    }

    public void insertModule(Module module) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mModuleDao.insert(module);
        });
    }

    public void deleteModule(Module module) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mModuleDao.deleteModule(module);
        });
    }

    public void updateModule(Module module) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mModuleDao.updateModule(module);
        });
    }

    public LiveData<List<Module>> getAllModuleForSemesterId(int id) {
        return mModuleDao.getModulesBySemesterId(id);
    }

    public LiveData<Integer> getCreditsForCategorie(Integer semesterId, String categorie) {
        return mModuleDao.getCreditsForCategorie(semesterId, categorie);
    }

    public LiveData<List<Module>> getDistinctModules(int semestreId) {
        return mModuleDao.getDistinctModules(semestreId);
    }


}








