package fr.utt.if26.projet_final_if26.models.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.entities.Semestre;

@Dao
public interface SemestreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Semestre semestre);

    @Delete
    public void deleteSemestre(Semestre... semestres);

    @Update
    public void updateSemestre(Semestre... semestres);

    @Query("select * from semestre_table")
    LiveData<List<Semestre>> getAllSemestres();

    @Query("select * from semestre_table where cursus_label = :mCursusLabel")
    LiveData<List<Semestre>> getSemestreByCursusLabel(String mCursusLabel);

    @Query("DELETE FROM semestre_table WHERE id = :id")
    void deleteSemestreById(int id);



}