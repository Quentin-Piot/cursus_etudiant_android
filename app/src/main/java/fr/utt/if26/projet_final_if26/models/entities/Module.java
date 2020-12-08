package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "module_table")
public class Module {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String sigle;
    private String parcours;
    private String categorie;
    private int credits;
    @ColumnInfo(name = "semestre_id")
    private int semestreId;

    public Module(@NonNull String sigle, @NonNull String parcours, @NonNull String categorie, @NonNull int credits, int semestreId) {
        this.sigle = sigle;
        this.parcours = parcours;
        this.categorie = categorie;
        this.credits = credits;
        this.semestreId = semestreId;

    }

    public String getSigle() {
        return sigle;
    }

    public String getParcours() {
        return parcours;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getCredits() {
        return credits;
    }

    public int getId() {
        return id;
    }

    public int getSemestreId() {
        return semestreId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setParcours(String parcours) {
        this.parcours = parcours;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setSemestreId(int semestreId) {
        this.semestreId = semestreId;
    }
}
