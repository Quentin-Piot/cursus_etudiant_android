package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "cursus_table",
        foreignKeys = @ForeignKey(entity = Etudiant.class,
                parentColumns = "id",
                childColumns = "etudiant_id",
                onDelete = ForeignKey.CASCADE)
)
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