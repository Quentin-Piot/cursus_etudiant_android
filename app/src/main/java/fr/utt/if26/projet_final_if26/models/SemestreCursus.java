package fr.utt.if26.projet_final_if26.models;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;

public class SemestreCursus {

    private Semestre semestre;
    private List<Module> modules = new ArrayList<>();

    public SemestreCursus(Semestre semestre) {
        this.semestre = semestre;
    }
    public SemestreCursus(Semestre semestre, List<Module> modules) {
        this.semestre = semestre;
        this.modules = modules;
    }
    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
