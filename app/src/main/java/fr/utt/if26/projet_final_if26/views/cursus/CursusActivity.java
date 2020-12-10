package fr.utt.if26.projet_final_if26.views.cursus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityCursusBinding;
import fr.utt.if26.projet_final_if26.models.SemestreCursus;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.factories.CursusViewModelFactory;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.views.semestre.EditSemestreActivity;

public class CursusActivity extends AppCompatActivity {


    private CursusViewModel viewModel;
    private ActivityCursusBinding binding;
    private RecyclerView recyclerView;

    private String mCursusLabel;

    private List<Semestre> listeSemestres = new ArrayList<>();
    private List<Module> listeModules = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCursusLabel = getIntent().getStringExtra("cursus_label");
        initBinding();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        viewModel.getmSemestres().observe(this, semestres -> {listeSemestres = semestres; this.onChanged();});
        viewModel.getmModules().observe(this, modules -> {listeModules = modules; this.onChanged();});

        Objects.requireNonNull(getSupportActionBar()).setTitle("Page du cursus");


    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cursus);
        CursusViewModelFactory factory = new CursusViewModelFactory(getApplication(), mCursusLabel);
        viewModel = new ViewModelProvider(this, factory).get(CursusViewModel.class);
        binding.cursusNameTv.setText(mCursusLabel);
        binding.setViewModel(viewModel);
        binding.setCursusActivity(this);
        recyclerView = binding.semestreRecyclerView;

    }

    public void initAdapter(RecyclerView recyclerView, List<SemestreCursus> semestreCursus, CursusViewModel viewModel) {
        if (semestreCursus.size() == 0) {
            binding.messageTvSemestre.setText(R.string.aucun_semestre);
        } else {
            binding.messageTvSemestre.setText("");

        }
        AdapterRecyclerListeSemestres adapter = new AdapterRecyclerListeSemestres(semestreCursus, viewModel, this);
        recyclerView.setAdapter(adapter);
    }

    public void onClickEditSemestre(Semestre semestre) {
        Intent intent = new Intent(getApplicationContext(), EditSemestreActivity.class);
        intent.putExtra("cursus_label", mCursusLabel);
        intent.putExtra("semestre_id", semestre.getId());
        intent.putExtra("semestre_label", semestre.getLabel());
        startActivity(intent);

    }

    public void onClickAddCursus() {
        AddSemestreDialogFragment addSemestreDialogFragment = new AddSemestreDialogFragment(viewModel);
        addSemestreDialogFragment.show(getSupportFragmentManager(), "ajout_semestre");

    }

    public void onSelectCursus(int etudiantId) {

    }

    public void onRecieveVMEvent(VMEventsEnum event) {
    }

    public void onSemesterClick() {

    }

    private void onChanged() {
        List<SemestreCursus> semestreCursus = new ArrayList<>();

        listeSemestres.forEach((semestre -> {
            List<Module> modulesCursus = new ArrayList<>();
            listeModules.forEach(module -> {
                if(module.getSemestreId() == semestre.getId()){
                    modulesCursus.add(module);
                }
            });
                    semestreCursus.add(new SemestreCursus(semestre, modulesCursus));

                })

        );


        binding.cursusSemestresNumberTv.setText(Integer.toString(listeSemestres.size()));
        this.initAdapter(recyclerView, semestreCursus, viewModel);
        if (listeSemestres.size() >= 7) {
            binding.fabCursus.setVisibility(View.INVISIBLE);
        }
        binding.semestreRecyclerView.scheduleLayoutAnimation();
    }
}