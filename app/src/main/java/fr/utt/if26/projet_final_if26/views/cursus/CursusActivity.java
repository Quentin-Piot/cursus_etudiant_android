package fr.utt.if26.projet_final_if26.views.cursus;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ActivityCursusBinding;
import fr.utt.if26.projet_final_if26.models.NombreCreditsCategorie;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.factories.CursusViewModelFactory;
import fr.utt.if26.projet_final_if26.views.semestre.EditSemestreActivity;

public class CursusActivity extends AppCompatActivity {


    private CursusViewModel viewModel;
    private ActivityCursusBinding binding;
    private RecyclerView recyclerView;

    private String mCursusLabel;
    private String mEtudiantProgramme;

    private List<Semestre> listeSemestres = new ArrayList<>();

    private AdapterRecyclerListeSemestres adapter;

    private boolean npmlDone;
    private boolean seDone;

    private ColorFilter doneFilter;
    private ColorFilter undoneFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCursusLabel = getIntent().getStringExtra("cursus_label");
        mEtudiantProgramme = getIntent().getStringExtra("etudiant_programme");

        initBinding();
        doneFilter = new PorterDuffColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.done), PorterDuff.Mode.MULTIPLY);
        undoneFilter = new PorterDuffColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.undone), PorterDuff.Mode.MULTIPLY);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        viewModel.getmSemestres().observe(this, this::onListUpdate);
        viewModel.getNombreCreditsCategorie().observe(this, this::updateProgressCredits);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Page du cursus");


    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cursus);
        CursusViewModelFactory factory = new CursusViewModelFactory(getApplication(), mCursusLabel, mEtudiantProgramme);
        viewModel = new ViewModelProvider(this, factory).get(CursusViewModel.class);
        binding.setViewModel(viewModel);
        binding.setCursusActivity(this);
        recyclerView = binding.semestreRecyclerView;
        this.initAdapter(recyclerView, listeSemestres, viewModel);
    }

    public void initAdapter(RecyclerView recyclerView, List<Semestre> listeSemestres, CursusViewModel viewModel) {

        binding.messageTvSemestre.setText(R.string.aucun_semestre);
        adapter = new AdapterRecyclerListeSemestres(listeSemestres, viewModel, this);
        recyclerView.setAdapter(adapter);

    }


    public void onClickEditSemestre(Semestre semestre) {
        Intent intent = new Intent(getApplicationContext(), EditSemestreActivity.class);
        intent.putExtra("cursus_label", mCursusLabel);
        intent.putExtra("semestre_id", semestre.getId());
        intent.putExtra("semestre_label", semestre.getLabel());
        startActivity(intent);

    }

    public void onClickAddSemestre() {
        Intent intent = new Intent(getApplicationContext(), AddSemestreActivity.class);
        intent.putExtra("cursus_label", mCursusLabel);
        intent.putExtra("etudiant_programme", mEtudiantProgramme);

        startActivity(intent);
        overridePendingTransition(R.transition.slide_down_in, R.transition.slide_down_out);
    }

    public void onClickInfosCursus() {
        InfosCursusDialogFragment infosCursusDialogFragment = new InfosCursusDialogFragment(viewModel);
        infosCursusDialogFragment.show(getSupportFragmentManager(), "infos_cursus");
    }

    private void updateProgressCredits(NombreCreditsCategorie mcc) {

        binding.arcProgressCs.setMax(mcc.getMaxProgress().get(0));
        binding.arcProgressCs.setProgress(mcc.getCs());

        if (mcc.getCs() >= 24)
            binding.arcProgressCs.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
        else
            binding.arcProgressCs.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));

        binding.arcProgressTm.setMax(mcc.getMaxProgress().get(1));
        binding.arcProgressTm.setProgress(mcc.getTm());

        if (mcc.getTm() >= 24)
            binding.arcProgressTm.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
        else
            binding.arcProgressTm.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));

        binding.arcProgressSt.setMax(mcc.getMaxProgress().get(2));
        binding.arcProgressSt.setProgress(mcc.getSt());

        if (mcc.getSt() >= 60)
            binding.arcProgressSt.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
        else
            binding.arcProgressSt.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));

        binding.arcProgressEc.setMax(mcc.getMaxProgress().get(3));
        binding.arcProgressEc.setProgress(mcc.getEc());

        if (mcc.getEc() >= 12)
            binding.arcProgressEc.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
        else
            binding.arcProgressEc.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));

        binding.arcProgressMe.setMax(mcc.getMaxProgress().get(4));
        binding.arcProgressMe.setProgress(mcc.getMe());

        if (mcc.getMe() >= 4)
            binding.arcProgressMe.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
        else
            binding.arcProgressMe.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));

        binding.arcProgressHt.setMax(mcc.getMaxProgress().get(5));
        binding.arcProgressHt.setProgress(mcc.getHt());

        if (mcc.getHt() >= 4)
            binding.arcProgressHt.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
        else
            binding.arcProgressHt.setFinishedStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));


        binding.determinateBar.setMax(mcc.getMaxProgress().get(6));
        binding.determinateBar.setProgress(mcc.getTotal());
        binding.totalCreditsTv.setText(Integer.toString(mcc.getTotal()));

        if (mcc.getTotal() >= 180)
            binding.determinateBar.getProgressDrawable().setColorFilter(doneFilter);
        else binding.determinateBar.getProgressDrawable().setColorFilter(undoneFilter);


    }

    private void onListUpdate(List<Semestre> semestres) {
        listeSemestres = semestres;
        seDone = false;
        npmlDone = false;
        semestres.forEach(semestre -> {
            if (semestre.isNpml()) {
                npmlDone = true;
            }
            if (semestre.isSemestreEtranger()) {
                seDone = true;
            }

            viewModel.getmModulesForSemesterId(semestre.getId()).observe(this, modules -> updateModules(modules, semestres.indexOf(semestre)));

        });
        setBadges(seDone, npmlDone);
        onChanged();


    }

    private void setBadges(boolean se, boolean npml) {
        if (se) {
            binding.badgeSe.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
            binding.badgeSe.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back_done));
        } else {
            binding.badgeSe.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));
            binding.badgeSe.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back_undone));
        }

        if (npml) {
            binding.badgeNpml.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.done));
            binding.badgeNpml.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back_done));
        } else {
            binding.badgeNpml.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.undone));
            binding.badgeNpml.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back_undone));
        }
    }

    private void updateModules(List<Module> modules, int pos) {
        if (modules.size() > 0 && pos < listeSemestres.size()) {
            listeSemestres.get(pos).setListeModules(modules);
            adapter.setListeSemestres(listeSemestres);
            adapter.notifyDataSetChanged();
            viewModel.onUpdateModulesCredits(listeSemestres);
        } else {
            viewModel.onUpdateModulesCredits(listeSemestres);
        }

    }

    private void onChanged() {

        if (listeSemestres.size() == 0) {
            binding.messageTvSemestre.setText(R.string.aucun_semestre);
        } else {
            binding.messageTvSemestre.setText("");

        }

        adapter.setListeSemestres(listeSemestres);
        adapter.notifyDataSetChanged();
        if (listeSemestres.size() >= 7) {
            binding.fabCursus.setVisibility(View.INVISIBLE);
        }
        binding.semestreRecyclerView.scheduleLayoutAnimation();
    }


}