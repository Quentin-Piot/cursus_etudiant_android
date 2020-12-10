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

public class CursusViewModel extends AndroidViewModel {

    private final CursusEtudiantRepository mRepository;
    private final String mCursusLabel;

    private final MutableLiveData<Semestre> _selectedSemestre = new MutableLiveData<>();
    private final LiveData<Semestre> selectedSemestre = _selectedSemestre;


    public MutableLiveData<String> semestreLabel = new MutableLiveData<>();

    private final MutableLiveData<VMEventsEnum> _vmEvent = new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;


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

    public void onClickDelSemestre(Semestre semestre) {
        if (semestre.getId() > -1) {
            mRepository.deleteSemestreById(semestre.getId());
            _vmEvent.setValue(VMEventsEnum.success_operation);
        }
    }

    public LiveData<List<Semestre>> getmSemestres() {
        return mRepository.getAllSemestreForCursusLabel(mCursusLabel);
    }

    public LiveData<List<Module>> getmModules() {
        return mRepository.getAllModuleForCursusLabel(mCursusLabel);
    }
    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }

    public LiveData<Semestre> getSelectedSemestre() {
        return selectedSemestre;
    }
}
