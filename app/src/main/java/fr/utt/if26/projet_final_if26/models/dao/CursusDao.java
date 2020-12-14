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
    public int updateCursus(Cursus... cursus);

    @Query("select * from cursus_table")
    LiveData<List<Cursus>> getAllCursus();

    @Query("select * from cursus_table where etudiant_id = :id")
    LiveData<List<Cursus>> getCursusById(int id);

    @Query("DELETE FROM cursus_table WHERE label = :label")
    void deleteCursusByLabel(String label);

    @Query("DELETE FROM cursus_table WHERE etudiant_id = :etudiantId")
    void deleteCursusOfEtudiant(int etudiantId);


}