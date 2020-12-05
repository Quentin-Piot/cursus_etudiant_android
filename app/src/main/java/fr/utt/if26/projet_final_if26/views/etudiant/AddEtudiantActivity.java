package fr.utt.if26.projet_final_if26.views.etudiant;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityAddEtudiantBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class AddEtudiantActivity extends AppCompatActivity {
    private EtudiantViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();


        viewModel.getAddSuccess().observe(this, this::onAddSuccess);

    }

    private void initBinding() {
        ActivityAddEtudiantBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_etudiant);
        viewModel = new ViewModelProvider(this).get(EtudiantViewModel.class);
        InputFilter[] allCaps = new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)};
        binding.nameStudentEt.setFilters(allCaps);


        binding.setViewModel(viewModel);

        viewModel.programme.setValue("Mars");

    }

    public void onAddSuccess(String text) {
        if (text.equals("SUCCESS")) {
            Toast.makeText(getApplicationContext(), "Étudiant ajouté", Toast.LENGTH_SHORT).show();
            finish();
        } else if (text.equals("EMPTY")) {
            Toast.makeText(getApplicationContext(), "Veuillez compléter tous les champs", Toast.LENGTH_SHORT).show();

        }
    }
}