package fr.utt.if26.projet_final_if26.views.etudiant;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.DialogDuplicateCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class DuplicateCursusDialogFragment extends DialogFragment {

    private EtudiantViewModel viewModel;
    private Cursus selectedCursus;

    private List<Semestre> semestreList;

    public DuplicateCursusDialogFragment(EtudiantViewModel viewModel, Cursus cursus) {
        super();
        this.viewModel = viewModel;
        this.selectedCursus = cursus;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        DialogDuplicateCursusBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_duplicate_cursus, null, false);
        binding.setViewModel(viewModel);

        viewModel.getmSemestres(selectedCursus.getLabel()).observe(this, semestres -> {
            semestreList = semestres;
            semestres.forEach(semestre -> {
                viewModel.getmModulesForSemesterId(semestre.getId()).observe(this, semestre::setListeModules);
            });
        });
        viewModel.cursusLabel.setValue("");
        builder.setView(binding.getRoot())
                .setPositiveButton(R.string.modifier, (dialog, id) -> {
                    viewModel.onClickDuplicateCursus(viewModel.cursusLabel.getValue(), semestreList);
                })
                .setNegativeButton(R.string.annuler, (dialog, id) -> {
                });
        return builder.create();
    }

    public EtudiantViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(EtudiantViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
