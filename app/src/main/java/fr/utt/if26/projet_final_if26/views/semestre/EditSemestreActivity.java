package fr.utt.if26.projet_final_if26.views.semestre;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityEditSemestreBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.viewmodels.SemestreViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.factories.SemestreViewModelFactory;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;

public class EditSemestreActivity extends AppCompatActivity {


    private SemestreViewModel viewModel;
    private ActivityEditSemestreBinding binding;
    private RecyclerView recyclerView;

    private int mSemestreId;
    private String mSemestreLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSemestreId = getIntent().getIntExtra("semestre_id", -1);
        mSemestreLabel = getIntent().getStringExtra("semestre_label");

        initBinding();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        viewModel.getmModules().observe(this, this::onChanged);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Page du cursus");


    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_semestre);
        SemestreViewModelFactory factory = new SemestreViewModelFactory(getApplication(), mSemestreId);
        viewModel = new ViewModelProvider(this, factory).get(SemestreViewModel.class);
        binding.setViewModel(viewModel);
        binding.setActivity(this);
        binding.cursusSemestreLabelTv.setText(mSemestreLabel);
        recyclerView = binding.semesterModulesRecyclerView;

    }

    public void initAdapter(RecyclerView recyclerView, List<Module> modules, SemestreViewModel viewModel) {
        AdapterRecyclerListeModules adapter = new AdapterRecyclerListeModules(modules, viewModel);
        recyclerView.setAdapter(adapter);
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
        binding.cursusModulesNumberTv.setText(Integer.toString(modules.size()));
        this.initAdapter(recyclerView, modules, viewModel);
        /*
        if (semestres.size() >= 7) {
            binding.fabCursus.setVisibility(View.INVISIBLE);
        }

         */
        //binding.semestreRecyclerView.scheduleLayoutAnimation();
    }
}