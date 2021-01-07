package fr.utt.if26.projet_final_if26.views.semestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityEditSemestreBinding;
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
        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);
        modulesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        historiqueModulesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getmModules().observe(this, this::onListUpdate);
        viewModel.getNpml().observe(this, result -> binding.switchNpml.setChecked(result));
        viewModel.getSe().observe(this, result -> binding.switchSe.setChecked(result));

        viewModel.getDistinctModules(mSemestreId).observe(this, this::onHistoriqueChanged);


        Objects.requireNonNull(getSupportActionBar()).setTitle("Page du semestre");


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

    public void onClickAddCursus() {

        AddModuleDialogFragment addModuleDialogFragment = new AddModuleDialogFragment(viewModel);
        addModuleDialogFragment.show(getSupportFragmentManager(), "ajout_module");

    }

    private void onListUpdate(List<Module> modules) {

        binding.cursusModulesNumberTv.setText(Integer.toString(modules.size()));
        modulesInSemestre = modules;

        adapterModules.setModules(modules);
        adapterModules.notifyDataSetChanged();
        onHistoriqueChanged(historiqueAdapter.getModules());

        if (modules.size() >= 8) {
            binding.addModuleBt.setVisibility(View.GONE);
        } else if (binding.addModuleBt.getVisibility() == View.GONE)
            binding.addModuleBt.setVisibility(View.VISIBLE);
    }


    private void onHistoriqueChanged(List<Module> modules) {


        historiqueAdapter.setModules(suppressDuplicateModules(modules, modulesInSemestre));
        historiqueAdapter.setNumberAddedModules(modulesInSemestre.size());
        historiqueAdapter.notifyDataSetChanged();

    }

    private List<Module> suppressDuplicateModules(List<Module> list1, List<Module> list2) {

        return list1.stream()
                .filter(s -> list2.stream().noneMatch(f -> f.getSigle().equals(s.getSigle())))
                .collect(Collectors.toList());
    }

    public void onRecieveVMEvent(VMEventsEnum event) {
        switch (event) {
            case element_already_exist:
                Toast.makeText(getApplicationContext(), "L'élement existe déjà", Toast.LENGTH_SHORT).show();
                break;
            case empty_fields:
                Toast.makeText(getApplicationContext(), "Veuillez compléter l'ensemble des champs", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}