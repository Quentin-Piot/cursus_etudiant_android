package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

@Entity(tableName = "cursus_table")
public class Cursus {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom;
    private int etudiantId;

    public Cursus(@NonNull String nom, @NonNull int etudiantId) {
        this.nom = nom;
        this.etudiantId = etudiantId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getEtudiantId() {
        return etudiantId;
    }
}