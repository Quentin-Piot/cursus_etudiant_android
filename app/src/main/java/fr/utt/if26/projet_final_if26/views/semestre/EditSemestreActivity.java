package fr.utt.if26.projet_final_if26.views.semestre;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityEditSemestreBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.viewmodels.SemestreViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.viewmodels.factories.SemestreViewModelFactory;

public class EditSemestreActivity extends AppCompatActivity {


    private SemestreViewModel viewModel;
    private ActivityEditSemestreBinding binding;
    private RecyclerView modulesRecyclerView;
    private RecyclerView historiqueModulesRecyclerView;
    private int mSemestreId;
    private String mSemestreLabel;
    private String mCursusLabel;

    private List<Module> modulesInSemestre = new ArrayList<>();

    private AdapterRecyclerListeHistoriqueModules historiqueAdapter;
    private AdapterRecyclerListeModules adapterModules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSemestreId = getIntent().getIntExtra("semestre_id", -1);
        mSemestreLabel = getIntent().getStringExtra("semestre_label");
        mCursusLabel = getIntent().getStringExtra("cursus_label");


        initBinding();
        modulesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        historiqueModulesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getmModules().observe(this, this::onChanged);
        viewModel.getNpml().observe(this, result -> binding.switchNpml.setChecked(result));
        viewModel.getSe().observe(this, result -> binding.switchSe.setChecked(result));

        viewModel.getDistinctModules(mSemestreId).observe(this, this::onHistoriqueChanged);


        MediatorLiveData liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(viewModel.getmModules(), value -> liveDataMerger.setValue(value));
        liveDataMerger.addSource(viewModel.getDistinctModules(mSemestreId), value -> liveDataMerger.setValue(value));


        Objects.requireNonNull(getSupportActionBar()).setTitle("Page du cursus");


    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_semestre);
        SemestreViewModelFactory factory = new SemestreViewModelFactory(getApplication(), mSemestreId, mCursusLabel);
        viewModel = new ViewModelProvider(this, factory).get(SemestreViewModel.class);
        binding.setViewModel(viewModel);
        binding.setActivity(this);
        binding.cursusSemestreLabelTv.setText(mSemestreLabel);
        modulesRecyclerView = binding.semesterModulesRecyclerView;
        historiqueModulesRecyclerView = binding.historiqueRecyclerView;
        historiqueAdapter = new AdapterRecyclerListeHistoriqueModules(new ArrayList<>(), viewModel);
        historiqueModulesRecyclerView.setAdapter(historiqueAdapter);
        adapterModules = new AdapterRecyclerListeModules(new ArrayList<>(), viewModel);
        modulesRecyclerView.setAdapter(adapterModules);
    }


    public void onEditCursus(Cursus cursus) {

    }

    public void onClickAddCursus() {

        AddModuleDialogFragment addModuleDialogFragment = new AddModuleDialogFragment(viewModel);
        addModuleDialogFragment.show(getSupportFragmentManager(), "ajout_module");

    }

    public void onSelectCursus(int etudiantId) {

    }

    public void onRecieveVMEvent(VMEventsEnum event) {
    }

    public void onSemesterClick() {

    }

    private void onChanged(List<Module> modules) {

        if (modules.size() > 0)
            binding.cursusModulesNumberTv.setText(Integer.toString(modules.size()));
        modulesInSemestre = modules;

        adapterModules.setModules(modules);
        adapterModules.notifyDataSetChanged();
        onHistoriqueChanged(historiqueAdapter.getModules());
    }


    private void onHistoriqueChanged(List<Module> modules) {


        historiqueAdapter.setModules(suppressDuplicateModules(modules, modulesInSemestre));
        historiqueAdapter.notifyDataSetChanged();

    }

    private List<Module> suppressDuplicateModules(List<Module> list1, List<Module> list2) {

        return list1.stream()
                .filter(s -> list2.stream().noneMatch(f -> f.getSigle().equals(s.getSigle())))
                .collect(Collectors.toList());
    }
}