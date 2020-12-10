package fr.utt.if26.projet_final_if26.views.semestre;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.DialogAddCursusBinding;
import fr.utt.if26.projet_final_if26.databinding.DialogAddModuleBinding;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;
import fr.utt.if26.projet_final_if26.viewmodels.SemestreViewModel;

public class AddModuleDialogFragment extends DialogFragment {

    private SemestreViewModel viewModel;

    public AddModuleDialogFragment(SemestreViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        DialogAddModuleBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_module, null, false);
        binding.setViewModel(viewModel);
        viewModel.moduleSigle.setValue("");
        viewModel.moduleCategorie.setValue("");
        viewModel.moduleCredits.setValue("");

        builder.setView(binding.getRoot())
                .setPositiveButton(R.string.ajouter, (dialog, id) -> viewModel.onClickAddModule())
                .setNegativeButton(R.string.annuler, (dialog, id) -> {
                });
        return builder.create();
    }

}
