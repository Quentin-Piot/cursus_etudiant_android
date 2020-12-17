package fr.utt.if26.projet_final_if26.models.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.entities.Cursus;

@Dao
public interface CursusDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Cursus cursus);

    @Delete
    void deleteCursus(Cursus... cursus);

    @Update
    int updateCursus(Cursus... cursus);

    @Query("select * from cursus_table where etudiant_id = :id")
    LiveData<List<Cursus>> getCursusById(int id);


}