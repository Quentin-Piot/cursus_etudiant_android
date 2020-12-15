package fr.utt.if26.projet_final_if26.views.homepage;

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
import fr.utt.if26.projet_final_if26.databinding.ActivityMainBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.AccueilViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.views.etudiant.EtudiantActivity;

public class MainActivity extends AppCompatActivity {

    private AccueilViewModel viewModel;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private AdapterRecyclerListeEtudiants adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Accueil");

        initBinding();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getmEtudiants().observe(this, this::onListUpdate);
        viewModel.getSelectedEtudiant().observe(this, this::onSelectEtudiant);

        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddEtudiantActivity.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_down_in, R.transition.slide_down_out);

        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(AccueilViewModel.class);
        binding.setViewModel(viewModel);
        recyclerView = binding.etudiantRecyclerView;
        adapter = new AdapterRecyclerListeEtudiants(new ArrayList<>(), viewModel);
        recyclerView.setAdapter(adapter);
    }

    public void onListUpdate(List<Etudiant> etudiants) {
        if (etudiants.size() == 0) {
            binding.etudiantMessageTv.setText(R.string.aucun_etudiant);
        } else {
            binding.etudiantMessageTv.setText("");

        }
        adapter.setEtudiantList(etudiants);
        adapter.notifyDataSetChanged();

    }

    public void onSelectEtudiant(Etudiant etudiant) {
        if (etudiant.getId() >= 0) {
            Intent intent = new Intent(getApplicationContext(), EtudiantActivity.class);
            intent.putExtra("student_id", etudiant.getId());
            intent.putExtra("student_name", etudiant.getNom());
            intent.putExtra("student_first_name", etudiant.getPrenom());
            intent.putExtra("student_programme", etudiant.getProgramme());

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