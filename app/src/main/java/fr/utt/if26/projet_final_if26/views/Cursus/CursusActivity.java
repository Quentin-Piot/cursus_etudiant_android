package fr.utt.if26.projet_final_if26.views.Cursus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityEtudiantBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModelFactory;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantActivityViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;
import fr.utt.if26.projet_final_if26.views.etudiant.AdapterRecyclerListeCursus;
import fr.utt.if26.projet_final_if26.views.etudiant.AddCursusDialogFragment;
import fr.utt.if26.projet_final_if26.views.etudiant.EditCursusDialogFragment;

public class CursusActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Page du cursus");



    }

    private void initBinding() {

        setContentView( R.layout.activity_cursus);

    }

    public void initAdapter(RecyclerView recyclerView, List<Cursus> cursus, EtudiantActivityViewModel viewModel) {

    }

    public void onEditCursus(Cursus cursus) {

    }

    public void onClickAddCursus() {


    }

    public void onSelectCursus(int etudiantId) {

    }

    public void onRecieveVMEvent(VMEventsEnum event) {
    }

}