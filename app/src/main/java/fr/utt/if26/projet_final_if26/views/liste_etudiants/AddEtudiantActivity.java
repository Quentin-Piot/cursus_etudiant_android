package fr.utt.if26.projet_final_if26.views.liste_etudiants;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityAddEtudiantBinding;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class AddEtudiantActivity extends AppCompatActivity {
    private EtudiantViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();

        viewModel.getMessageToView().observe(this, this::displayToast);

    }

    private void initBinding() {
        ActivityAddEtudiantBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_etudiant);
        getSupportActionBar().setTitle("Ajout d'un étudiant");
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

    public void displayToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}