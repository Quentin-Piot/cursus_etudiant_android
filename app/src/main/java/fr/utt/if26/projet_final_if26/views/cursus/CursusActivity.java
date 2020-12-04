package fr.utt.if26.projet_final_if26.views.cursus;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class CursusActivity extends AppCompatActivity {

    private ActivityCursusBinding binding;
    private CursusViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int etudiantId = getIntent().getIntExtra("student_id", -1);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cursus);
        viewModel = new ViewModelProvider(this).get(CursusViewModel.class);
        binding.setViewModel(viewModel);
        RecyclerView recyclerView = binding.cursusRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel.getmCursus(etudiantId).observe(this, cursus -> initAdapter(recyclerView, cursus, viewModel));


        Toast.makeText(this, "l" + etudiantId, Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    public void initAdapter(RecyclerView recyclerView, List<Cursus> cursus, CursusViewModel viewModel) {
        if (cursus.size() == 0) {
            binding.cursusMessageTv.setText("Aucun cursus");
        } else {
            binding.cursusMessageTv.setText("");

        }
        AdapterRecyclerCursus adapter = new AdapterRecyclerCursus(cursus, viewModel);
        recyclerView.setAdapter(adapter);

    }
}