package fr.utt.if26.projet_final_if26.views.liste_cursus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityListeCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModelFactory;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;

public class CursusActivity extends AppCompatActivity {

    private CursusViewModel viewModel;
    private ActivityListeCursusBinding binding;
    private RecyclerView recyclerView;

    private String studentName;
    private int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentName = getIntent().getStringExtra("student_name");
        studentId = getIntent().getIntExtra("student_id", -1);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Liste des cursus de " + studentName);

        initBinding();


        viewModel.getmCursus().observe(this, cursus -> initAdapter(recyclerView, cursus, viewModel));
        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void initBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_liste_cursus);
        CursusViewModelFactory factory = new CursusViewModelFactory(getApplication(), studentId);
        viewModel = new ViewModelProvider(this, factory).get(CursusViewModel.class);
        binding.setViewModel(viewModel);
        binding.setListeCursusActivity(this);
        recyclerView = binding.cursusRecyclerView;
    }

    public void initAdapter(RecyclerView recyclerView, List<Cursus> cursus, CursusViewModel viewModel) {
        if (cursus.size() == 0) {
            binding.messageTvCursus.setText(R.string.aucun_cursus);
        } else {
            binding.messageTvCursus.setText("");

        }
        AdapterRecyclerCursus adapter = new AdapterRecyclerCursus(cursus, viewModel, this);
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

    public void onSelectCursus(int etudiantId) {
        if (etudiantId >= 0) {
            Intent intent = new Intent(getApplicationContext(), CursusActivity.class);
            intent.putExtra("student_id", etudiantId);
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