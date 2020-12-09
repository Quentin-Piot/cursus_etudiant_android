package fr.utt.if26.projet_final_if26.views.cursus;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModelFactory;
import fr.utt.if26.projet_final_if26.viewmodels.VMEventsEnum;

public class EditCursusActivity extends AppCompatActivity {


    private CursusViewModel viewModel;
    private ActivityCursusBinding binding;
    private RecyclerView recyclerView;

    private String mCursusLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCursusLabel = getIntent().getStringExtra("cursus_label");
        initBinding();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        viewModel.getmSemestres().observe(this, this::onChanged);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Page du cursus");


    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cursus);
        CursusViewModelFactory factory = new CursusViewModelFactory(getApplication(), mCursusLabel);
        viewModel = new ViewModelProvider(this, factory).get(CursusViewModel.class);
        binding.cursusNameTv.setText(mCursusLabel);
        binding.setViewModel(viewModel);
        binding.setCursusActivity(this);
        recyclerView = binding.semestreRecyclerView;

    }

    public void initAdapter(RecyclerView recyclerView, List<Semestre> semestres, CursusViewModel viewModel) {
        if (semestres.size() == 0) {
            binding.messageTvSemestre.setText(R.string.aucun_semestre);
        } else {
            binding.messageTvSemestre.setText("");

        }
        AdapterRecyclerListeSemestres adapter = new AdapterRecyclerListeSemestres(semestres, viewModel, this);
        recyclerView.setAdapter(adapter);
      /*  recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                System.out.println("touchs");
                int visibility = ((LinearLayout) view.findViewById(R.id.layout_collapsed)).getVisibility();
                if (visibility == View.GONE) {
                    ((LinearLayout) view.findViewById(R.id.layout_collapsed)).setVisibility(View.VISIBLE);

                } else {
                    ((LinearLayout) view.findViewById(R.id.layout_collapsed)).setVisibility(View.GONE);

                }
            }



            @Override
            public void onLongClick(View view, int position) {

            }
        })); */
    }

    public void onEditCursus(Cursus cursus) {

    }

    public void onClickAddCursus() {
        AddSemestreDialogFragment addSemestreDialogFragment = new AddSemestreDialogFragment(viewModel);
        addSemestreDialogFragment.show(getSupportFragmentManager(), "ajout_semestre");

    }

    public void onSelectCursus(int etudiantId) {

    }

    public void onRecieveVMEvent(VMEventsEnum event) {
    }

    public void onSemesterClick() {

    }

    private void onChanged(List<Semestre> semestres) {
        binding.cursusSemestresNumberTv.setText(Integer.toString(semestres.size()));
        this.initAdapter(recyclerView, semestres, viewModel);
        if (semestres.size() >= 7) {
            binding.fabCursus.setVisibility(View.INVISIBLE);
        }
        binding.semestreRecyclerView.scheduleLayoutAnimation();
    }
}