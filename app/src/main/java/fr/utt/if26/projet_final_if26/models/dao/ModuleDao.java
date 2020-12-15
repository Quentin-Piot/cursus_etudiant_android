package fr.utt.if26.projet_final_if26.models.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.entities.Module;

@Dao
public interface ModuleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Module module);

    @Delete
    void deleteModule(Module... modules);

    @Update
    void updateModule(Module... modules);

    @Query("select * from module_table")
    LiveData<List<Module>> getAllModules();

    @Query("select * from module_table where semestre_id = :semesterId")
    LiveData<List<Module>> getModulesBySemesterId(int semesterId);


    @Query("SELECT COUNT(*) from module_table WHERE semestre_id = :semesterId AND categorie = :categorie")
    LiveData<Integer> getCreditsForCategorie(int semesterId, String categorie);

    @Query("SELECT * from module_table WHERE semestre_id != :semestreId GROUP BY sigle ")
    LiveData<List<Module>> getDistinctModules(int semestreId);
}