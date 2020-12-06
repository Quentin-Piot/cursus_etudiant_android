package fr.utt.if26.projet_final_if26.views.liste_etudiants;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityAddEtudiantBinding;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;

public class AddEtudiantActivity extends AppCompatActivity {
    private EtudiantViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ajout d'un étudiant");
        initBinding();

        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);

    }

    private void initBinding() {
        ActivityAddEtudiantBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_etudiant);
        viewModel = new ViewModelProvider(this).get(EtudiantViewModel.class);
        InputFilter[] filters = new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)};
        binding.nameStudentEt.setFilters(filters);
        binding.setViewModel(viewModel);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.transition.slide_up_in, R.transition.slide_up_out);
    }

    public void onRecieveVMEvent(VMEventsEnum event) {
        switch (event) {
            case success_operation:
                Toast.makeText(getApplicationContext(),"Opération réussie", Toast.LENGTH_SHORT).show();
                break;
            case empty_fields:
                Toast.makeText(getApplicationContext(),"Veuillez compléter l'ensemble des champs", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}