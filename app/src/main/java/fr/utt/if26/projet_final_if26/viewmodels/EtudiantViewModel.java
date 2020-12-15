package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;

public class EtudiantViewModel extends AndroidViewModel {

    private final CursusEtudiantRepository mRepository;
    private final int mEtudiantId;

    private final MutableLiveData<Cursus> _selectedCursus = new MutableLiveData<>();
    private final LiveData<Cursus> selectedCursus = _selectedCursus;


    public MutableLiveData<String> cursusLabel = new MutableLiveData<>();

    private final MutableLiveData<VMEventsEnum> _vmEvent = new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;


    public EtudiantViewModel(@NonNull Application application, int mEtudiantId) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        this.mEtudiantId = mEtudiantId;
    }

    public void onClickAddEtudiant() {

        if (cursusLabel.getValue() != null && !cursusLabel.getValue().isEmpty() && mEtudiantId > -1) {
            mRepository.insertCursus(new Cursus(cursusLabel.getValue(), mEtudiantId));
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }
    }

    public void onClickDuplicateCursus(String newCursusLabel, List<Semestre> semestres) {

        if (!newCursusLabel.isEmpty() && semestres != null) {
            Cursus newCursus = new Cursus(newCursusLabel, mEtudiantId);
            mRepository.duplicateCursus(newCursus, semestres);
        }


    }

    public void setSelectedEtudiant(Cursus cursus) {

        this._selectedCursus.setValue(cursus);
    }

    public void onClickDelCursus(Cursus cursus) {
        if (!cursus.getLabel().isEmpty()) {
            mRepository.deleteCursus(cursus);
        }
    }

    public LiveData<List<Semestre>> getmSemestres(String cursusLabel) {
        return mRepository.getAllSemestreForCursusLabel(cursusLabel);
    }

    public LiveData<List<Module>> getmModulesForSemesterId(int id) {
        return mRepository.getAllModuleForSemesterId(id);
    }

    public LiveData<List<Cursus>> getmCursus() {
        return mRepository.getAllCursusForEtudiantId(mEtudiantId);
    }

    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }

    public LiveData<Cursus> getSelectedCursus() {
        return selectedCursus;
    }
}
