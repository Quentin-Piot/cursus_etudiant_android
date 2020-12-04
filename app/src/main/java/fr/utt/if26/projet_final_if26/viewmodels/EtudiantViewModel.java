package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

public class EtudiantViewModel extends AndroidViewModel {

    private final LiveData<List<Etudiant>> mEtudiants;

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> firstName = new MutableLiveData<>();

    private MutableLiveData<String> _messageToView = new MutableLiveData<>();
    private LiveData<String> messageToView = _messageToView;

    private MutableLiveData<String> _addSuccess = new MutableLiveData<>();
    private LiveData<String> addSuccess = _addSuccess;

    private MutableLiveData<Integer> _selectedEtudiantId = new MutableLiveData<>();
    private LiveData<Integer> selectedEtudiantId = _selectedEtudiantId;



    private CursusEtudiantRepository mRepository;
    public EtudiantViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        mEtudiants = mRepository.getAllEtudiants();
    }

    public void handleOnClickAddEtudiant(){

        if(name.getValue() != null && firstName.getValue() != null){
            mRepository.insertEtudiant(new Etudiant(name.getValue(),firstName.getValue()));
            _addSuccess.setValue("SUCCESS");
        } else {
            _addSuccess.setValue("EMPTY");
        }
    }

    public void handleOnClickDelEtudiant(Etudiant etudiant){
        if(etudiant.getId() > -1){
            mRepository.deleteEtudiantById(etudiant.getId());
            _messageToView.setValue("Étudiant supprimé");
        }
        }


    public void insert(Etudiant etudiant) {
        mRepository.insertEtudiant(etudiant);
    }

    public void deleteById(int id) {
        mRepository.deleteEtudiantById(id);
    }


    public LiveData<Integer> getSelectedEtudiantId() {
        return selectedEtudiantId;
    }

    public void setSelectedEtudiantId(int selectedEtudiantId) {
        this._selectedEtudiantId.setValue(selectedEtudiantId);;
    }

    public LiveData<String> getMessageToView() {
        return messageToView;
    }

    public LiveData<String> getAddSuccess() {
        return addSuccess;
    }

    public LiveData<List<Etudiant>> getmEtudiants() {
        return mEtudiants;
    }
}
