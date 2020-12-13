package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

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
    private final String mCursusLabel;
    public MutableLiveData<String> moduleSigle = new MutableLiveData<>();
    public MutableLiveData<String> moduleCategorie = new MutableLiveData<>();
    public MutableLiveData<String> moduleProgramme = new MutableLiveData<>();
    public MutableLiveData<String> moduleCredits = new MutableLiveData<>();


    private final MutableLiveData<Module> _selectedModule = new MutableLiveData<>();
    private final LiveData<Module> selectedModule = _selectedModule;



    private final MutableLiveData<VMEventsEnum> _vmEvent = new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;


    public SemestreViewModel(@NonNull Application application, int semestreId, String cursusLabel) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        this.mSemestreId = semestreId;
        this.mCursusLabel = cursusLabel;
    }

    public void onClickAddModule() {

        if (!moduleSigle.getValue().isEmpty() && !moduleProgramme.getValue().isEmpty() && !moduleCategorie.getValue().isEmpty()
                && !moduleCredits.getValue().isEmpty()
                && mSemestreId > -1) {
            mRepository.insertModule(new Module(moduleSigle.getValue(), moduleProgramme.getValue(), moduleCategorie.getValue(), Integer.parseInt(moduleCredits.getValue()), mSemestreId));
            _vmEvent.setValue(VMEventsEnum.success_operation);
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }


    }

    public void onSelectProgramme(AdapterView<?> parent, View view, int pos, long id) {
        moduleProgramme.setValue(parent.getSelectedItem().toString());
        ((TextView) parent.getChildAt(0)).setTextSize(16);

    }

    public void onSelectCategorie(AdapterView<?> parent, View view, int pos, long id) {
        moduleCategorie.setValue(parent.getSelectedItem().toString());
        ((TextView) parent.getChildAt(0)).setTextSize(16);

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
