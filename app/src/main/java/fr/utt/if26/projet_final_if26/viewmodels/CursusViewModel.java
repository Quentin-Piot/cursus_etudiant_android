package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

public class CursusViewModel extends AndroidViewModel {

    private CursusEtudiantRepository mRepository;
    private int mEtudiantId;

    public MutableLiveData<String> cursusName = new MutableLiveData<>();

    private final MutableLiveData<VMEventsEnum> _vmEvent= new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;

    private final MutableLiveData<String> _messageToView = new MutableLiveData<>();
    private final LiveData<String> messageToView = _messageToView;
    private int selectedCursusId = -1;




    public CursusViewModel(@NonNull Application application, int mEtudiantId) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        this.mEtudiantId = mEtudiantId;
    }

    public void onClickAddEtudiant() {

        if (cursusName.getValue() != null && mEtudiantId > -1) {
            mRepository.insertCursus(new Cursus(cursusName.getValue(), mEtudiantId));
            _messageToView.setValue("Cursus ajouté");
            _vmEvent.setValue(VMEventsEnum.close_add_etudiant);
        } else {
            _messageToView.setValue("Veuillez remplir tous les champs");
        }
    }

    public void onClickUpdateCursus(Cursus selectedCursus){



        if(cursusName.getValue() != null) {
            selectedCursus.setNom(cursusName.getValue());
            mRepository.updateCursus(selectedCursus);
        }


    }

    public void onSelectedCursusId(int id) {

    }

    public void onClickDelCursus(Cursus cursus) {
        if (cursus.getId() > -1) {
            mRepository.deleteCursusById(cursus.getId());
            _messageToView.setValue("Cursus supprimé");
        }
    }

    public void updateCursus(Cursus cursus) {
        mRepository.updateCursus(cursus);
    }

    public LiveData<List<Cursus>> getmCursus() {
        return mRepository.getAllCursusFromEtudiantId(mEtudiantId);
    }


    public LiveData<String> getMessageToView() {
        return messageToView;
    }

    public void setSelectedCursusId(int selectedCursusId) {
        this.selectedCursusId = selectedCursusId;
    }

    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }
}
