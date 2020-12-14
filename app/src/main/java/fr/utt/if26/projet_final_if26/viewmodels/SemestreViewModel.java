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
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }


    }

    public void onClickAddHistoriqueModule(Module module) {

        module.setSemestreId(mSemestreId);
        mRepository.insertModule(module);

    }

    public void onSelectProgramme(AdapterView<?> parent, View view, int pos, long id) {
        moduleProgramme.setValue(parent.getSelectedItem().toString());
        ((TextView) parent.getChildAt(0)).setTextSize(16);

    }

    public void onSelectCategorie(AdapterView<?> parent, View view, int pos, long id) {
        moduleCategorie.setValue(parent.getSelectedItem().toString());
        ((TextView) parent.getChildAt(0)).setTextSize(16);

    }

    public void onSwitchNpml(boolean checked) {
        this.mRepository.updateNpmlFieldItem(checked, mSemestreId);
    }

    public void onSwitchSe(boolean checked) {
        this.mRepository.updateSeFieldItem(checked, mSemestreId);
    }

    public LiveData<List<Module>> getDistinctModules(int mSemestreId) {
        return mRepository.getDistinctModules(mSemestreId);
    }


    public void onClickDelModule(Module module) {
        mRepository.deleteModule(module);

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

    public LiveData<Boolean> getNpml() {
        return mRepository.getNpmlFieldForSemestreId(mSemestreId);
    }

    public LiveData<Boolean> getSe() {
        return mRepository.getSeFieldForSemestreId(mSemestreId);
    }
}
