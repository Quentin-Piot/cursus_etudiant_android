package fr.utt.if26.projet_final_if26.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.utt.if26.projet_final_if26.models.CursusEtudiantRepository;
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

    public MutableLiveData<ModuleCatCredit> _moduleCatCredit = new MutableLiveData<>();
    private final LiveData<ModuleCatCredit> moduleCatCredit = _moduleCatCredit;

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

    public void onUpdateModulesCredits(List<Semestre> semestres) {

        List<Module> allModules = new ArrayList<>();
        semestres.stream().map(Semestre::getListeModules).collect(Collectors.toList()).forEach(allModules::addAll);

        Map<String, Integer> mapArray = allModules.stream()
                .collect(Collectors.groupingBy(Module::getCategorie, Collectors.summingInt(Module::getCredits)));

        ModuleCatCredit mcc = new ModuleCatCredit(mapArray.getOrDefault("CS", 0), mapArray.getOrDefault("TM", 0), mapArray.getOrDefault("ST", 0), mapArray.getOrDefault("EC", 0), mapArray.getOrDefault("ME", 0), mapArray.getOrDefault("HT", 0), 0, 0, mapArray.size());
        this._moduleCatCredit.setValue(mcc);
    }


    public LiveData<List<Semestre>> getmSemestres() {
        return mRepository.getAllSemestreForCursusLabel(mCursusLabel);
    }

    public LiveData<List<Module>> getmModulesForSemesterId(int id) {
        return mRepository.getAllModuleForSemesterId(id);
    }

    public LiveData<VMEventsEnum> getVmEvent() {
        return vmEvent;
    }

    public LiveData<Semestre> getSelectedSemestre() {
        return selectedSemestre;
    }

    public LiveData<Integer> getCreditsForCategorie(Integer semestreId, String categorie) {

        return mRepository.getCreditsForCategorie(semestreId, categorie);
    }


    public LiveData<ModuleCatCredit> getModuleCatCredit() {
        return moduleCatCredit;
    }

    public static class ModuleCatCredit {
        private int cs = 0;
        private int tm = 0;
        private int st = 0;
        private int ec = 0;
        private int me = 0;
        private int ht = 0;
        private int ct = 0;
        private int hp = 0;

        private List<Integer> maxProgress = Arrays.asList(66, 48, 66, 20, 8, 8);
        private int total;

        public ModuleCatCredit() {

        }

        public ModuleCatCredit(int cs, int tm, int st, int ec, int me, int ht, int ct, int hp, int total) {
            this.cs = cs;
            this.tm = tm;
            this.st = st;
            this.ec = ec;
            this.me = me;
            this.ht = ht;
            this.ct = ct;
            this.hp = hp;
            this.total = total;

            if (cs > maxProgress.get(0)) maxProgress.set(0, cs);
            if (tm > maxProgress.get(1)) maxProgress.set(1, tm);
            if (st > maxProgress.get(2)) maxProgress.set(2, st);
            if (ec > maxProgress.get(3)) maxProgress.set(3, ec);
            if (me > maxProgress.get(4)) maxProgress.set(4, me);
            if (ht > maxProgress.get(5)) maxProgress.set(5, ht);

        }


        public int getCs() {
            return cs;
        }

        public int getTm() {
            return tm;
        }

        public int getSt() {
            return st;
        }

        public int getEc() {
            return ec;
        }

        public int getMe() {
            return me;
        }

        public int getHt() {
            return ht;
        }

        public int getCt() {
            return ct;
        }

        public int getHp() {
            return hp;
        }

        public int getTotal() {
            return total;
        }

        public void setCs(int cs) {
            this.cs = cs;
        }

        public void setTm(int tm) {
            this.tm = tm;
        }

        public void setSt(int st) {
            this.st = st;
        }

        public void setEc(int ec) {
            this.ec = ec;
        }

        public void setMe(int me) {
            this.me = me;
        }

        public void setHt(int ht) {
            this.ht = ht;
        }

        public void setCt(int ct) {
            this.ct = ct;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public void setMaxProgress(List<Integer> maxProgress) {
            this.maxProgress = maxProgress;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Integer> getMaxProgress() {
            return maxProgress;
        }
    }
}
