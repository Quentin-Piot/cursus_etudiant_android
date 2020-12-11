package fr.utt.if26.projet_final_if26.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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

    public Semestre(@NonNull String label, @NonNull String cursusLabel) {
        this.label = label;
        this.cursusLabel = cursusLabel;
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

}
