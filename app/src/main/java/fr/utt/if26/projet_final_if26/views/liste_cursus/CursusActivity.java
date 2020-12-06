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

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModelFactory;

public class CursusActivity extends AppCompatActivity {

    private CursusViewModel viewModel;
    private ActivityCursusBinding binding;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();


        viewModel.getmCursus().observe(this, cursus -> initAdapter(recyclerView, cursus, viewModel));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        FloatingActionButton fab = binding.fabCursus;

        fab.setOnClickListener(view -> {

            AddCursusDialogFragment addCursusDialogFragment = new AddCursusDialogFragment();
            addCursusDialogFragment.setViewModel(viewModel);
            addCursusDialogFragment.show(getSupportFragmentManager(), "ajout_cursus");


        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_liste_cursus);
        getSupportActionBar().setTitle("Liste des cursus");
        int studentId = getIntent().getIntExtra("student_id", -1);
        CursusViewModelFactory factory = new CursusViewModelFactory(getApplication(), studentId);
        viewModel = new ViewModelProvider(this, factory).get(CursusViewModel.class);
        binding.setViewModel(viewModel);
        recyclerView = binding.cursusRecyclerView;
    }

    public void initAdapter(RecyclerView recyclerView, List<Cursus> cursus, CursusViewModel viewModel) {
        if (cursus.size() == 0) {
            binding.messageTvCursus.setText("Aucun cursus");
        } else {
            binding.messageTvCursus.setText("");

        }
        AdapterRecyclerCursus adapter = new AdapterRecyclerCursus(cursus, viewModel);
        recyclerView.setAdapter(adapter);
    }

    public void onSelectCursus(int etudiantId) {
        if (etudiantId >= 0) {
            Intent intent = new Intent(getApplicationContext(), CursusActivity.class);
            intent.putExtra("student_id", etudiantId);
            startActivity(intent);
        }
    }

    public void displayToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}