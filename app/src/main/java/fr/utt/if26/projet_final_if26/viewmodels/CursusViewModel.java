package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.NombreCreditsCategorie;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;

public class CursusViewModel extends AndroidViewModel {

    private final CursusEtudiantRepository mRepository;
    private final String mCursusLabel;

    private final MutableLiveData<Semestre> _selectedSemestre = new MutableLiveData<>();
    private final LiveData<Semestre> selectedSemestre = _selectedSemestre;


    public MutableLiveData<String> semestreLabel = new MutableLiveData<>();

    private final MutableLiveData<VMEventsEnum> _vmEvent = new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;

    public MutableLiveData<NombreCreditsCategorie> _moduleCatCredit = new MutableLiveData<>();
    private final LiveData<NombreCreditsCategorie> moduleCatCredit = _moduleCatCredit;

    public CursusViewModel(@NonNull Application application, String mCursusLabel) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        this.mCursusLabel = mCursusLabel;
    }

    public void onClickAddSemestre() {

        if (semestreLabel.getValue() != null && !semestreLabel.getValue().isEmpty()) {
            mRepository.insertSemestre(new Semestre(semestreLabel.getValue(), mCursusLabel));
            _vmEvent.setValue(VMEventsEnum.success_operation);
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }
    }

    public void onClickEditSemestre(Semestre selectedSemestre) {
    }

    public void setSelectedSemestre(Semestre semestre) {

        this._selectedSemestre.setValue(semestre);
    }

    public void onUpdateModulesCredits(List<Semestre> semestres) {

        List<Module> allModules = new ArrayList<>();
        semestres.stream().map(Semestre::getListeModules).collect(Collectors.toList()).forEach(allModules::addAll);

        Map<String, Integer> mapArray = allModules.stream()
                .collect(Collectors.groupingBy(Module::getCategorie, Collectors.summingInt(Module::getCredits)));

        NombreCreditsCategorie mcc = new NombreCreditsCategorie(mapArray.getOrDefault("CS", 0), mapArray.getOrDefault("TM", 0), mapArray.getOrDefault("ST", 0), mapArray.getOrDefault("EC", 0), mapArray.getOrDefault("ME", 0), mapArray.getOrDefault("HT", 0), 0, 0);
        this._moduleCatCredit.setValue(mcc);
    }


    public LiveData<List<Semestre>> getmSemestres() {
        return mRepository.getAllSemestreForCursusLabel(mCursusLabel);
    }

    public LiveData<List<Module>> getmModulesForSemesterId(int id) {
        return mRepository.getAllModuleForSemesterId(id);
    }

    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }

    public LiveData<Semestre> getSelectedSemestre() {
        return selectedSemestre;
    }

    public LiveData<Integer> getCreditsForCategorie(Integer semestreId, String categorie) {

        return mRepository.getCreditsForCategorie(semestreId, categorie);
    }


    public LiveData<NombreCreditsCategorie> getNombreCreditsCategorie() {
        return moduleCatCredit;
    }

}
