package fr.utt.if26.projet_final_if26.views.etudiant;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.DialogAddCursusBinding;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class AddCursusDialogFragment extends DialogFragment {

    private EtudiantViewModel viewModel;

    public AddCursusDialogFragment(EtudiantViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        DialogAddCursusBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_cursus, null, false);
        binding.setViewModel(viewModel);
        viewModel.cursusLabel.setValue("");
        builder.setView(binding.getRoot())
                .setPositiveButton(R.string.ajouter, (dialog, id) -> viewModel.onClickAddEtudiant())
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
