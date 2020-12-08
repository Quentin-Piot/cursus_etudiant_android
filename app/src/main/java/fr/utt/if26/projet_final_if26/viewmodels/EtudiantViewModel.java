package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

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
            _vmEvent.setValue(VMEventsEnum.success_operation);
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }
    }

    public void onClickUpdateCursus(Cursus selectedCursus) {


        if (cursusLabel.getValue() != null && !cursusLabel.getValue().isEmpty()) {
            selectedCursus.setLabel(cursusLabel.getValue());
            mRepository.updateCursus(selectedCursus);
            _vmEvent.setValue(VMEventsEnum.success_operation);
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }


    }

    public void setSelectedEtudiant(Cursus cursus) {

        this._selectedCursus.setValue(cursus);
    }

    public void onClickDelCursus(Cursus cursus) {
        if (cursus.getId() > -1) {
            mRepository.deleteCursusById(cursus.getId());
            _vmEvent.setValue(VMEventsEnum.success_operation);
        }
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
