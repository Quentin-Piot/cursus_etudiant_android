package fr.utt.if26.projet_final_if26.views.etudiant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityEtudiantBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModelFactory;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantActivityViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.views.Cursus.CursusActivity;

public class EtudiantActivity extends AppCompatActivity {

    private EtudiantActivityViewModel viewModel;
    private ActivityEtudiantBinding binding;
    private RecyclerView recyclerView;

    private String studentName;
    private String studentFirstName;
    private String studentProgramme;

    private int studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentName = getIntent().getStringExtra("student_name");
        studentFirstName = getIntent().getStringExtra("student_first_name");
        studentProgramme = getIntent().getStringExtra("student_programme");

        studentId = getIntent().getIntExtra("student_id", -1);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Page de l\'étudiant");

        initBinding();


        viewModel.getmCursus().observe(this, cursus -> {
            initAdapter(recyclerView, cursus, viewModel);
            if (cursus != null)
                binding.studentCursusNumberTv.setText(Integer.toString(cursus.size()));
        });
        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);
        viewModel.getSelectedCursus().observe(this, this::onSelectCursus);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void initBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_etudiant);
        CursusViewModelFactory factory = new CursusViewModelFactory(getApplication(), studentId);
        viewModel = new ViewModelProvider(this, factory).get(EtudiantActivityViewModel.class);
        binding.setViewModel(viewModel);
        binding.setEtudiantActivity(this);
        recyclerView = binding.cursusRecyclerView;

        binding.studentNameTv.setText(studentName);
        binding.studentFirstnameTv.setText(studentFirstName);
        binding.studentProgrammeTv.setText(studentProgramme);

    }

    public void initAdapter(RecyclerView recyclerView, List<Cursus> cursus, EtudiantActivityViewModel viewModel) {
        if (cursus.size() == 0) {
            binding.messageTvCursus.setText(R.string.aucun_cursus);
        } else {
            binding.messageTvCursus.setText("");

        }
        AdapterRecyclerListeCursus adapter = new AdapterRecyclerListeCursus(cursus, viewModel, this);
        recyclerView.setAdapter(adapter);
    }

    public void onEditCursus(Cursus cursus) {
        EditCursusDialogFragment editCursusDialogFragment = new EditCursusDialogFragment(viewModel, cursus);
        editCursusDialogFragment.show(getSupportFragmentManager(), "edit_cursus");
    }

    public void onClickAddCursus() {
        AddCursusDialogFragment addCursusDialogFragment = new AddCursusDialogFragment();
        addCursusDialogFragment.setViewModel(viewModel);
        addCursusDialogFragment.show(getSupportFragmentManager(), "ajout_cursus");

    }

    public void onSelectCursus(Cursus cursus) {
        if (cursus.getId() >= 0) {
            Intent intent = new Intent(getApplicationContext(), CursusActivity.class);
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