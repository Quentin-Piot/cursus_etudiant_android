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

public class SemestreViewModel extends AndroidViewModel {

    private final CursusEtudiantRepository mRepository;
    private final int mSemestreId;

    public MutableLiveData<String> moduleSigle;
    public MutableLiveData<String> moduleCategorie;
    public MutableLiveData<String> moduleParcours;
    public MutableLiveData<Integer> moduleCredits;


    private final MutableLiveData<Module> _selectedModule = new MutableLiveData<>();
    private final LiveData<Module> selectedModule = _selectedModule;


    public MutableLiveData<String> cursusLabel = new MutableLiveData<>();

    private final MutableLiveData<VMEventsEnum> _vmEvent = new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;


    public SemestreViewModel(@NonNull Application application, int semestreId) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        this.mSemestreId = semestreId;
    }

    public void onClickAddModule() {
/*
        if (cursusLabel.getValue() != null && !cursusLabel.getValue().isEmpty() && mEtudiantId > -1) {
            mRepository.insertCursus(new Cursus(cursusLabel.getValue(), mEtudiantId));
            _vmEvent.setValue(VMEventsEnum.success_operation);
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }

 */
    }

    public void onClickUpdateModule(Module module) {
/*

        if (cursusLabel.getValue() != null && !cursusLabel.getValue().isEmpty()) {
            selectedModule.setLabel(cursusLabel.getValue());
            mRepository.updateCursus(selectedModule);
            _vmEvent.setValue(VMEventsEnum.success_operation);
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }

 */


    }


    public void onClickDelModule(Module module) {
        if (module.getId()>-1) {
            mRepository.deleteModuleById(module.getId());
            _vmEvent.setValue(VMEventsEnum.success_operation);
        }
    }

    public void setSelectedModule(Module module) {

        this._selectedModule.setValue(module);
    }


    public LiveData<List<Module>> getmModules() {
        return mRepository.getAllModuleForSemesterId(mSemestreId);
    }

    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }

    public LiveData<Module> getselectedModule() {
        return selectedModule;
    }
}
