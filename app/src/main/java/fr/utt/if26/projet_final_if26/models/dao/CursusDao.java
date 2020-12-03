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
    void insert(Cursus cursus);

    @Delete
    public void deleteCursus(Cursus... cursus);

    @Update
    public void updateCursus(Cursus... cursus);

    @Query("select * from cursus_table")
    LiveData<List<Cursus>> getAllCursus(int id);

    @Query("select * from cursus_table where etudiantId = :id")
    LiveData<Cursus> getCursusById(int id);

    @Query("DELETE FROM cursus_table WHERE etudiantId = :id")
    void deleteCursusById(int id);



}