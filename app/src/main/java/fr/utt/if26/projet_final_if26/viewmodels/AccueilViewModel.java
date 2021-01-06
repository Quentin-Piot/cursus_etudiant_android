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
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

public class AccueilViewModel extends AndroidViewModel {

    private final LiveData<List<Etudiant>> mEtudiants;
    private final MutableLiveData<Etudiant> _selectedEtudiant = new MutableLiveData<>();
    private final LiveData<Etudiant> selectedEtudiant = _selectedEtudiant;
    private final MutableLiveData<VMEventsEnum> _vmEvent = new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;
    private final CursusEtudiantRepository mRepository;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> firstName = new MutableLiveData<>();
    public MutableLiveData<String> programme = new MutableLiveData<>();


    public AccueilViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        mEtudiants = mRepository.getAllEtudiants();
    }

    public void onClickAddEtudiant() {

        if (name.getValue() != null && firstName.getValue() != null && programme.getValue() != null && !name.getValue().isEmpty() && !firstName.getValue().isEmpty()) {
            _vmEvent.setValue(VMEventsEnum.success_add);
            mRepository.insertEtudiant(new Etudiant(name.getValue(), firstName.getValue(), programme.getValue()));
        }
    }

    public void onClickDelEtudiant(Etudiant etudiant) {
        if (etudiant.getId() > -1) {
            mRepository.deleteEtudiant(etudiant);
        }
    }

    public void onClickPopulateBaseRoom() {

        mRepository.populateDatabaseRoom();

    }


    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        programme.setValue(parent.getSelectedItem().toString());
        ((TextView) parent.getChildAt(0)).setTextSize(16);

    }


    public void insert(Etudiant etudiant) {
        mRepository.insertEtudiant(etudiant);
    }


    public LiveData<Etudiant> getSelectedEtudiant() {
        return selectedEtudiant;
    }

    public void setSelectedEtudiant(Etudiant selectedEtudiant) {
        this._selectedEtudiant.setValue(selectedEtudiant);
    }


    public LiveData<List<Etudiant>> getmEtudiants() {
        return mEtudiants;
    }

    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }


}
