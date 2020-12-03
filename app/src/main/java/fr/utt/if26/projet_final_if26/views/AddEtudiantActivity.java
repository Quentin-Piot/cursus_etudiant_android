package fr.utt.if26.projet_final_if26.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityAddEtudiantBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class AddEtudiantActivity extends AppCompatActivity {
    private EtudiantViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddEtudiantBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_etudiant);
        viewModel = new ViewModelProvider(this).get(EtudiantViewModel.class);
        binding.setViewModel(viewModel);
    }
}