package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
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

    @PrimaryKey
    @NonNull
    private String label;
    @ColumnInfo(name = "etudiant_id")
    private int etudiantId;
    @ColumnInfo(name = "semestre_etranger", defaultValue = "false")
    private boolean semestreEtranger = false;
    @ColumnInfo(name = "npml", defaultValue = "false")
    private boolean npml;

    public Cursus(@NonNull String label, @NonNull int etudiantId) {
        this.label = label;
        this.etudiantId = etudiantId;
        this.npml = false;
        this.semestreEtranger = true;
    }


    public void setLabel(String label) {
        this.label = label;
    }

    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId;
    }


    public String getLabel() {
        return label;
    }

    public int getEtudiantId() {
        return etudiantId;
    }

    public boolean isSemestreEtranger() {
        return semestreEtranger;
    }

    public void setSemestreEtranger(boolean semestreEtranger) {
        this.semestreEtranger = semestreEtranger;
    }

    public boolean isNpml() {
        return npml;
    }

    public void setNpml(boolean npml) {
        this.npml = npml;
    }
}