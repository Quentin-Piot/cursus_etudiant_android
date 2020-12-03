package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "semestre_table")
public class Semestre {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private int cursusId;

    public Semestre(@NonNull String nom, @NonNull int cursusId) {
        this.nom = nom;
        this.cursusId = cursusId;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getCursusID() {
        return cursusId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCursusId() {
        return cursusId;
    }

    public void setCursusId(int cursusId) {
        this.cursusId = cursusId;
    }
}
