package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;

public class SemestreViewModel extends AndroidViewModel {

    private CursusEtudiantRepository mRepository;
    private int mEtudiantId;

    public MutableLiveData<String> cursusName = new MutableLiveData<>();

    private final MutableLiveData<VMEventsEnum> _vmEvent = new MutableLiveData<>();
    private final LiveData<VMEventsEnum> vmEvent = _vmEvent;


    public SemestreViewModel(@NonNull Application application, int mEtudiantId) {
        super(application);
        mRepository = new CursusEtudiantRepository(application);
        this.mEtudiantId = mEtudiantId;
    }

    public void onClickAddEtudiant() {

        if (cursusName.getValue() != null && mEtudiantId > -1) {
            mRepository.insertCursus(new Cursus(cursusName.getValue(), mEtudiantId));
            _vmEvent.setValue(VMEventsEnum.close_add_etudiant);
        } else {
            _vmEvent.setValue(VMEventsEnum.empty_fields);
        }
    }

    public void onClickUpdateCursus(Cursus selectedCursus) {


        if (cursusName.getValue() != null) {
            selectedCursus.setLabel(cursusName.getValue());
            mRepository.updateCursus(selectedCursus);
        }


    }

    public void onSelectedCursusId(int id) {

    }

    public void onClickDelCursus(Cursus cursus) {
        if (cursus.getId() > -1) {
            mRepository.deleteCursusById(cursus.getId());
            _vmEvent.setValue(VMEventsEnum.success_operation);
        }
    }

    public void updateCursus(Cursus cursus) {
        mRepository.updateCursus(cursus);
    }

    public LiveData<List<Cursus>> getmCursus() {
        return mRepository.getAllCursusForEtudiantId(mEtudiantId);
    }


    public void setSelectedCursusId(int selectedCursusId) {
    }

    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }
}
