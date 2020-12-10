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
    public void deleteModule(Module... modules);

    @Update
    public void updateModule(Module... modules);

    @Query("select * from module_table")
    LiveData<List<Module>> getAllModules();

    @Query("select * from module_table where semestre_id = :semesterId")
    LiveData<List<Module>> getModulesBySemesterId(int semesterId);

    @Query("DELETE FROM module_table WHERE id = :id")
    void deleteModuleById(int id);



}