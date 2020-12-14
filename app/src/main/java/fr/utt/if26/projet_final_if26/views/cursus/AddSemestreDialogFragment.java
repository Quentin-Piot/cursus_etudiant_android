package fr.utt.if26.projet_final_if26.views.cursus;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.DialogAddSemestreBinding;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class AddSemestreDialogFragment extends DialogFragment {

    private CursusViewModel viewModel;

    public AddSemestreDialogFragment(CursusViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        DialogAddSemestreBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_semestre, null, false);
        binding.setViewModel(viewModel);
        viewModel.semestreLabel.setValue("");
        builder.setView(binding.getRoot())
                .setPositiveButton(R.string.ajouter, (dialog, id) -> viewModel.onClickAddSemestre())
                .setNegativeButton(R.string.annuler, (dialog, id) -> {
                });
        return builder.create();
    }
}
