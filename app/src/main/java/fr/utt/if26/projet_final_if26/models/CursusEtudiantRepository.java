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

    public void deleteEtudiant(Etudiant etudiant) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mEtudiantDao.deleteEtudiant(etudiant);
        });
    }

    public void insertCursus(Cursus cursus) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mCursusDao.insert(cursus);
        });
    }

    public void deleteCursus(Cursus cursus) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {
            mCursusDao.deleteCursus(cursus);
        });
    }

    public LiveData<List<Cursus>> getAllCursusForEtudiantId(int id) {
        return mCursusDao.getCursusById(id);
    }

    public void duplicateCursus(Cursus cursus, List<Semestre> semestres) {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {

            long result = mCursusDao.insert(cursus);
            if (result > -1) {
                semestres.forEach(semestre -> {
                    Semestre newSemestre = new Semestre(semestre.getLabel(), cursus.getLabel(), false, false);
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

    public LiveData<List<Semestre>> getDistinctSemestres() {
        return mSemestreDao.getDistinctSemestres();
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


    public void populateDatabaseRoom() {
        CursusEtudiantDatabase.databaseWriteExecutor.execute(() -> {

            //clean database
            mEtudiantDao.deleteAllEtudiants();

            //création de l'étudiant

            int etudiantId = (int) mEtudiantDao.insert(new Etudiant("DUPONT", "Antoine", "ISI"));

            //création des deux cursus

            int cursus1 = (int) mCursusDao.insert(new Cursus("cursus_1", etudiantId));
            int cursus2 = (int) mCursusDao.insert(new Cursus("cursus_2", etudiantId));

            int cursus1Semestre1 = (int) mSemestreDao.insert(new Semestre("A16", "cursus_1", false, false));
            int cursus1Semestre2 = (int) mSemestreDao.insert(new Semestre("P17", "cursus_1", false, false));
            int cursus1Semestre3 = (int) mSemestreDao.insert(new Semestre("A17", "cursus_1", false, false));
            int cursus1Semestre4 = (int) mSemestreDao.insert(new Semestre("P18", "cursus_1", false, false));
            int cursus1Semestre5 = (int) mSemestreDao.insert(new Semestre("A18", "cursus_1", false, false));
            int cursus1Semestre6 = (int) mSemestreDao.insert(new Semestre("P19", "cursus_1", false, false));

            int cursus2Semestre1 = (int) mSemestreDao.insert(new Semestre("A16", "cursus_2", false, true));
            int cursus2Semestre2 = (int) mSemestreDao.insert(new Semestre("P17", "cursus_2", false, false));
            int cursus2Semestre3 = (int) mSemestreDao.insert(new Semestre("A17", "cursus_2", true, false));
            int cursus2Semestre4 = (int) mSemestreDao.insert(new Semestre("P18", "cursus_2", false, false));
            int cursus2Semestre5 = (int) mSemestreDao.insert(new Semestre("A18", "cursus_2", false, false));
            int cursus2Semestre6 = (int) mSemestreDao.insert(new Semestre("P19", "cursus_2", false, false));

            mModuleDao.insert(new Module("GL02", "ISI", "CS", 6, cursus2Semestre1));
            mModuleDao.insert(new Module("NF16", "ISI", "CS", 6, cursus2Semestre1));
            mModuleDao.insert(new Module("HT01", "TB", "HT", 4, cursus2Semestre1));
            mModuleDao.insert(new Module("EC01", "TB", "EC", 4, cursus2Semestre1));
            mModuleDao.insert(new Module("IF09", "ISI", "TM", 6, cursus2Semestre1));
            mModuleDao.insert(new Module("IF14", "ISI", "TM", 6, cursus2Semestre1));

            mModuleDao.insert(new Module("NF20", "ISI", "CS", 6, cursus2Semestre2));
            mModuleDao.insert(new Module("LO02", "ISI", "TM", 6, cursus2Semestre2));
            mModuleDao.insert(new Module("NF21", "ISI", "TM", 6, cursus2Semestre2));
            mModuleDao.insert(new Module("HT02", "TB", "HT", 4, cursus2Semestre2));
            mModuleDao.insert(new Module("EC02", "TB", "EC", 4, cursus2Semestre2));


            mModuleDao.insert(new Module("GL03", "ISI", "CS", 6, cursus2Semestre3));
            mModuleDao.insert(new Module("NF17", "ISI", "CS", 6, cursus2Semestre3));
            mModuleDao.insert(new Module("IF10", "ISI", "TM", 6, cursus2Semestre3));
            mModuleDao.insert(new Module("IF15", "ISI", "TM", 6, cursus2Semestre3));
            mModuleDao.insert(new Module("EC03", "TB", "EC", 4, cursus2Semestre3));
            mModuleDao.insert(new Module("ME03", "TB", "ME", 4, cursus2Semestre3));

            mModuleDao.insert(new Module("ST09", "TB", "ST", 30, cursus2Semestre4));


            mModuleDao.insert(new Module("NF21", "ISI", "CS", 6, cursus2Semestre5));
            mModuleDao.insert(new Module("LO03", "ISI", "TM", 6, cursus2Semestre5));
            mModuleDao.insert(new Module("NF23", "ISI", "TM", 6, cursus2Semestre5));
            mModuleDao.insert(new Module("NF22", "ISI", "TM", 6, cursus2Semestre5));
            mModuleDao.insert(new Module("HT04", "TB", "HT", 4, cursus2Semestre5));
            mModuleDao.insert(new Module("ME04", "TB", "ME", 4, cursus2Semestre5));

            mModuleDao.insert(new Module("ST10", "TB", "ST", 30, cursus2Semestre6));


            mModuleDao.insert(new Module("GL02", "ISI", "CS", 6, cursus1Semestre1));
            mModuleDao.insert(new Module("HT01", "TB", "HT", 4, cursus1Semestre1));
            mModuleDao.insert(new Module("IF09", "ISI", "TM", 6, cursus1Semestre1));
            mModuleDao.insert(new Module("IF14", "ISI", "TM", 6, cursus1Semestre1));

            mModuleDao.insert(new Module("NF20", "ISI", "CS", 6, cursus1Semestre2));
            mModuleDao.insert(new Module("NF21", "ISI", "TM", 6, cursus1Semestre2));
            mModuleDao.insert(new Module("EC02", "TB", "EC", 4, cursus1Semestre2));


            mModuleDao.insert(new Module("GL03", "ISI", "CS", 6, cursus1Semestre3));
            mModuleDao.insert(new Module("NF17", "ISI", "CS", 6, cursus1Semestre3));
            mModuleDao.insert(new Module("IF15", "ISI", "TM", 6, cursus1Semestre3));
            mModuleDao.insert(new Module("ME03", "TB", "ME", 4, cursus1Semestre3));

            mModuleDao.insert(new Module("ST09", "TB", "ST", 30, cursus1Semestre4));


            mModuleDao.insert(new Module("NF21", "ISI", "CS", 6, cursus1Semestre5));
            mModuleDao.insert(new Module("LO03", "ISI", "TM", 6, cursus1Semestre5));
            mModuleDao.insert(new Module("NF22", "ISI", "TM", 6, cursus1Semestre5));
            mModuleDao.insert(new Module("ME04", "TB", "ME", 4, cursus1Semestre5));


        });


    }

}








