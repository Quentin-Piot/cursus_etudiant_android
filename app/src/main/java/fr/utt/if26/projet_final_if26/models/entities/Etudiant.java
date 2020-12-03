package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "etudiant_table")
public class Etudiant {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom;

    public Etudiant(@NonNull String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
