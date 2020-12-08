package fr.utt.if26.projet_final_if26.views.homepage;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityAddEtudiantBinding;
import fr.utt.if26.projet_final_if26.viewmodels.MainActivityViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;

public class AddEtudiantActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ajout d'un étudiant");
        initBinding();

        viewModel.getVmEvent().observe(this, this::onRecieveVMEvent);

    }

    private void initBinding() {
        ActivityAddEtudiantBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_etudiant);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
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
            case close_add_etudiant:
                Toast.makeText(getApplicationContext(),"Le cursus a été bien ajouté", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(R.transition.slide_up_in,R.transition.slide_up_out);
                break;
            case empty_fields:
                Toast.makeText(getApplicationContext(),"Veuillez compléter l'ensemble des champs", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}