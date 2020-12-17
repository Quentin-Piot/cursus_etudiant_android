package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "semestre_table",
        foreignKeys = {@ForeignKey(entity = Cursus.class,
                parentColumns = "label",
                childColumns = "cursus_label",
                onDelete = ForeignKey.CASCADE)}
)
public class Semestre {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String label;
    @ColumnInfo(name = "cursus_label")
    private String cursusLabel;
    @ColumnInfo(name = "semestre_etranger", defaultValue = "false")
    private boolean semestreEtranger = false;
    @ColumnInfo(name = "npml", defaultValue = "false")
    private boolean npml;


    @Ignore
    private List<Module> listeModules = new ArrayList<>();


    public Semestre(@NonNull String label, @NonNull String cursusLabel, boolean semestreEtranger, boolean npml) {
        this.label = label;
        this.cursusLabel = cursusLabel;
        this.semestreEtranger = semestreEtranger;
        this.npml = npml;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getCursusLabel() {
        return cursusLabel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Module> getListeModules() {
        return listeModules;
    }

    public void setListeModules(List<Module> listeModules) {
        this.listeModules = listeModules;
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

    public void setCursusLabel(String cursusLabel) {
        this.cursusLabel = cursusLabel;
    }
}
