package fr.utt.if26.projet_final_if26.views.liste_cursus;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.DialogAddCursusBinding;
import fr.utt.if26.projet_final_if26.databinding.DialogEditCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class EditCursusDialogFragment extends DialogFragment {

    private CursusViewModel viewModel;
    private Cursus selectedCursus;

    public EditCursusDialogFragment(CursusViewModel viewModel, Cursus cursus) {
        super();
        this.viewModel = viewModel;
        this.selectedCursus = cursus;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        DialogEditCursusBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_edit_cursus, null, false);
        binding.setViewModel(viewModel);
                builder.setView(binding.getRoot())
                .setPositiveButton(R.string.modifier, (dialog, id) -> viewModel.onClickUpdateCursus(this.selectedCursus))
                .setNegativeButton(R.string.annuler, (dialog, id) -> {
                });
        return builder.create();
    }

    public CursusViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(CursusViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
