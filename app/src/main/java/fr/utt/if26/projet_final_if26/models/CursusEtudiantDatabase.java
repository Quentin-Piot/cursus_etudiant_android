package fr.utt.if26.projet_final_if26.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.utt.if26.projet_final_if26.models.dao.CursusDao;
import fr.utt.if26.projet_final_if26.models.dao.EtudiantDao;
import fr.utt.if26.projet_final_if26.models.dao.ModuleDao;
import fr.utt.if26.projet_final_if26.models.dao.SemestreDao;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;

@Database(entities = {Etudiant.class, Cursus.class, Semestre.class, Module.class}, version = 1, exportSchema = false)
public abstract class CursusEtudiantDatabase extends RoomDatabase {

    public abstract EtudiantDao etudiantDao();
    public abstract CursusDao cursusDao();
    public abstract SemestreDao semestreDao();
    public abstract ModuleDao moduleDao();

    private static volatile CursusEtudiantDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CursusEtudiantDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CursusEtudiantDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CursusEtudiantDatabase.class, "cursus_etudiant_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
