package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "semestre_table")
public class Semestre {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String label;
    @ColumnInfo(name = "cursus_id")
    private int cursusId;
    @ColumnInfo(name = "nombre_modules", defaultValue = "0")
    private int nombreModules;

    public Semestre(@NonNull String label, @NonNull int cursusId) {
        this.label = label;
        this.cursusId = cursusId;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getCursusID() {
        return cursusId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCursusId() {
        return cursusId;
    }

    public void setCursusId(int cursusId) {
        this.cursusId = cursusId;
    }

    public int getNombreModules() {
        return nombreModules;
    }

    public void setNombreModules(int nombreModules) {
        this.nombreModules = nombreModules;
    }
}
