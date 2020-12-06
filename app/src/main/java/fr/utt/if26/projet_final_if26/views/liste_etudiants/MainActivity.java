package fr.utt.if26.projet_final_if26.views.liste_etudiants;

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
import fr.utt.if26.projet_final_if26.databinding.ActivityListeEtudiantsBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;
import fr.utt.if26.projet_final_if26.views.liste_cursus.CursusActivity;

public class MainActivity extends AppCompatActivity {

    private EtudiantViewModel viewModel;
    private ActivityListeEtudiantsBinding binding;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getMessageToView().observe(this, this::displayToast);
        viewModel.getmEtudiants().observe(this, etudiants -> initAdapter(recyclerView, etudiants, viewModel));
        viewModel.getSelectedEtudiantId().observe(this, this::onSelectEtudiant);


        FloatingActionButton fab = binding.fab;


        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddEtudiantActivity.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_down_in, R.transition.slide_down_out);

        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_liste_etudiants);
        getSupportActionBar().setTitle("Liste des étudiants");
        viewModel = new ViewModelProvider(this).get(EtudiantViewModel.class);
        binding.setViewModel(viewModel);
        recyclerView = binding.etudiantRecyclerView;
    }

    public void initAdapter(RecyclerView recyclerView, List<Etudiant> etudiants, EtudiantViewModel viewModel) {
        if (etudiants.size() == 0) {
            binding.etudiantMessageTv.setText("Aucun étudiant");
        } else {
            binding.etudiantMessageTv.setText("");

        }
        AdapterRecyclerEtudiant adapter = new AdapterRecyclerEtudiant(etudiants, viewModel);
        recyclerView.setAdapter(adapter);

    }

    public void onSelectEtudiant(int etudiantId) {
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