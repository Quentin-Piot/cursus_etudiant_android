package fr.utt.if26.projet_final_if26.views.etudiant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityEtudiantBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.viewmodels.factories.EtudiantViewModelFactory;
import fr.utt.if26.projet_final_if26.views.cursus.CursusActivity;

public class EtudiantActivity extends AppCompatActivity {

    private EtudiantViewModel viewModel;
    private ActivityEtudiantBinding binding;
    private RecyclerView recyclerView;

    private String studentName;
    private String studentFirstName;
    private String studentProgramme;

    private int studentId;

    private AdapterRecyclerListeCursus adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentName = getIntent().getStringExtra("student_name");
        studentFirstName = getIntent().getStringExtra("student_first_name");
        studentProgramme = getIntent().getStringExtra("student_programme");

        studentId = getIntent().getIntExtra("student_id", -1);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Page de l\'étudiant");

        initBinding();


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);
        viewModel.getSelectedCursus().observe(this, this::onSelectCursus);
        viewModel.getmCursus().observe(this, this::onListUpdate);
    }

    private void initBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_etudiant);
        EtudiantViewModelFactory factory = new EtudiantViewModelFactory(getApplication(), studentId);
        viewModel = new ViewModelProvider(this, factory).get(EtudiantViewModel.class);
        binding.setViewModel(viewModel);
        binding.setEtudiantActivity(this);
        recyclerView = binding.cursusRecyclerView;

        binding.studentNameTv.setText(studentName);
        binding.studentFirstnameTv.setText(studentFirstName);
        binding.studentProgrammeTv.setText(studentProgramme);

        adapter = new AdapterRecyclerListeCursus(new ArrayList<>(), viewModel, this);
        recyclerView.setAdapter(adapter);
    }

    public void onListUpdate(List<Cursus> cursus) {
        if (cursus.size() == 0) {
            binding.messageTvCursus.setText(R.string.aucun_cursus);
        } else {
            binding.messageTvCursus.setText("");
        }
        binding.studentCursusNumberTv.setText(Integer.toString(cursus.size()));
        adapter.setCursusList(cursus);
        adapter.notifyDataSetChanged();
    }

    public void onClickAddCursus() {
        AddCursusDialogFragment addCursusDialogFragment = new AddCursusDialogFragment(viewModel);
        addCursusDialogFragment.show(getSupportFragmentManager(), "ajout_cursus");

    }


    public void onClickDuplicateCursus(Cursus selectedCursus) {
        DuplicateCursusDialogFragment duplicateCursusDialogFragment = new DuplicateCursusDialogFragment(viewModel, selectedCursus);
        duplicateCursusDialogFragment.show(getSupportFragmentManager(), "duplication_cursus");

    }

    public void onSelectCursus(Cursus cursus) {
        if (!cursus.getLabel().isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), CursusActivity.class);
            intent.putExtra("cursus_label", cursus.getLabel());
            intent.putExtra("etudiant_programme", studentProgramme);
            startActivity(intent);
        }
    }

    public void onRecieveVMEvent(VMEventsEnum event) {
        switch (event) {
            case success_operation:
                Toast.makeText(getApplicationContext(), "Opération réussie", Toast.LENGTH_SHORT).show();
                break;
            case empty_fields:
                Toast.makeText(getApplicationContext(), "Veuillez compléter l'ensemble des champs", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}