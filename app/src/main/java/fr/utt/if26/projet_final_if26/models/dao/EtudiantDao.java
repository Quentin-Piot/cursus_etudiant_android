package fr.utt.if26.projet_final_if26.models.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

@Dao
public interface EtudiantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Etudiant etudiant);

    @Delete
    void deleteEtudiant(Etudiant... etudiants);

    @Query("select * from etudiant_table")
    LiveData<List<Etudiant>> getAllEtudiants();

    @Query("DELETE FROM etudiant_table")
    void deleteAllEtudiants();

}