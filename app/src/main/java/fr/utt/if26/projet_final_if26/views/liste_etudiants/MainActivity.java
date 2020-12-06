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
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityListeEtudiantsBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.views.liste_cursus.CursusActivity;

public class MainActivity extends AppCompatActivity {

    private EtudiantViewModel viewModel;
    private ActivityListeEtudiantsBinding binding;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Liste des étudiants");

        initBinding();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getmEtudiants().observe(this, etudiants -> initAdapter(recyclerView, etudiants, viewModel));
        viewModel.getSelectedEtudiant().observe(this, this::onSelectEtudiant);

        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddEtudiantActivity.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_down_in, R.transition.slide_down_out);

        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_liste_etudiants);
        viewModel = new ViewModelProvider(this).get(EtudiantViewModel.class);
        binding.setViewModel(viewModel);
        recyclerView = binding.etudiantRecyclerView;
    }

    public void initAdapter(RecyclerView recyclerView, List<Etudiant> etudiants, EtudiantViewModel viewModel) {
        if (etudiants.size() == 0) {
            binding.etudiantMessageTv.setText(R.string.aucun_etudiant);
        } else {
            binding.etudiantMessageTv.setText("");

        }
        AdapterRecyclerEtudiant adapter = new AdapterRecyclerEtudiant(etudiants, viewModel);
        recyclerView.setAdapter(adapter);

    }

    public void onSelectEtudiant(Etudiant etudiant) {
        if (etudiant.getId() >= 0) {
            Intent intent = new Intent(getApplicationContext(), CursusActivity.class);
            intent.putExtra("student_id", etudiant.getId());
            intent.putExtra("student_nom", etudiant.getNom());

            startActivity(intent);
        }
    }
    public void onRecieveVMEvent(VMEventsEnum event) {
        switch (event) {
            case close_add_etudiant:
                Toast.makeText(getApplicationContext(),"Le cursus a été bien ajouté", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(R.transition.slide_up_in,R.transition.slide_up_out);
                break;
            case success_operation:
                Toast.makeText(getApplicationContext(),"Opération réussie", Toast.LENGTH_SHORT).show();
                break;
            case empty_fields:
                Toast.makeText(getApplicationContext(),"Veuillez compléter l'ensemble des champs", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}