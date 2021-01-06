package fr.utt.if26.projet_final_if26.views.cursus;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityAddSemestreBinding;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.viewmodels.factories.CursusViewModelFactory;

public class AddSemestreActivity extends AppCompatActivity {

    private CursusViewModel viewModel;
    private String mCursusLabel;
    private String mEtudiantProgramme;

    private AdapterRecyclerHistoriqueSemestres adapter;
    private ActivityAddSemestreBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ajout d'un semestre");
        mCursusLabel = getIntent().getStringExtra("cursus_label");
        mEtudiantProgramme = getIntent().getStringExtra(" etudiant_programme");
        initBinding();
        viewModel.getDistinctSemestres().observe(this, this::onListUpdate);
        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_semestre);
        CursusViewModelFactory factory = new CursusViewModelFactory(getApplication(), mCursusLabel, mEtudiantProgramme);
        viewModel = new ViewModelProvider(this, factory).get(CursusViewModel.class);
        binding.setViewModel(viewModel);
        binding.historiqueSemestresRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AdapterRecyclerHistoriqueSemestres(new ArrayList<>(), this);
        binding.historiqueSemestresRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.slide_up_in, R.transition.slide_up_out);
    }

    private void onListUpdate(List<Semestre> semestres) {
        adapter.setListeSemestres(semestres);
        adapter.notifyDataSetChanged();
    }

    public void onClickHistoriqueSemestre(String label) {
        binding.nameSemestreEt.setText(label);
    }

    public void onRecieveVMEvent(VMEventsEnum event) {
        switch (event) {
            case success_add:
                finish();
                overridePendingTransition(R.transition.slide_up_in, R.transition.slide_up_out);
                break;
            case empty_fields:
                Toast.makeText(getApplicationContext(), "Veuillez compléter l'ensemble des champs", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}