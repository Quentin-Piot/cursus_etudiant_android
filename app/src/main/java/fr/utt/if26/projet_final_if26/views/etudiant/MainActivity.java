package fr.utt.if26.projet_final_if26.views.etudiant;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityMainBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;
import fr.utt.if26.projet_final_if26.views.cursus.CursusActivity;

public class MainActivity extends AppCompatActivity {

    private EtudiantViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(EtudiantViewModel.class);
        binding.setViewModel(viewModel);
        RecyclerView recyclerView = binding.etudiantRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getMessageToView().observe(this, this::displayToast);
        viewModel.getmEtudiants().observe(this, etudiants -> initAdapter(recyclerView,etudiants,viewModel));
        viewModel.getSelectedEtudiantId().observe(this, this::onSelectEtudiant);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddEtudiantActivity.class);
            startActivity(intent);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), AddEtudiantActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initAdapter(RecyclerView recyclerView , List<Etudiant> etudiants, EtudiantViewModel viewModel) {
        if(etudiants.size() == 0 ) {
            binding.etudiantMessageTv.setText("Aucun étudiant");
        } else {
            binding.etudiantMessageTv.setText("");

        }
            AdapterRecyclerEtudiant adapter = new AdapterRecyclerEtudiant(etudiants, viewModel);
            recyclerView.setAdapter(adapter);

    }

    public void onSelectEtudiant(int etudiantId){
        if(etudiantId >= 0) {
            Intent intent = new Intent(getApplicationContext(), CursusActivity.class);
            intent.putExtra("student_id", etudiantId);
            startActivity(intent);
        }
    }
    public void displayToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}