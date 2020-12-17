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
    long insert(Semestre semestre);

    @Delete
    void deleteSemestre(Semestre... semestres);

    @Update
    void updateSemestre(Semestre... semestres);

    @Query("select * from semestre_table where cursus_label = :mCursusLabel")
    LiveData<List<Semestre>> getSemestreByCursusLabel(String mCursusLabel);

    @Query("UPDATE semestre_table SET npml = :checked WHERE id = :id")
    void updateNpmlField(boolean checked, int id);

    @Query("UPDATE semestre_table SET semestre_etranger = :checked WHERE id = :id")
    void updateSeField(boolean checked, int id);

    @Query("select npml from semestre_table where id = :semestreId")
    LiveData<Boolean> getNpml(int semestreId);

    @Query("select semestre_etranger from semestre_table where id = :semestreId")
    LiveData<Boolean> getSe(int semestreId);

    @Query("SELECT * from semestre_table GROUP BY label ")
    LiveData<List<Semestre>> getDistinctSemestres();
}