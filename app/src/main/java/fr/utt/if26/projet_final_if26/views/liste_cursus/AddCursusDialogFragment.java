package fr.utt.if26.projet_final_if26.views.liste_cursus;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.DialogAddCursusBinding;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class AddCursusDialogFragment extends DialogFragment {

    private CursusViewModel viewModel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle mArgs = getArguments();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        DialogAddCursusBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_cursus, null, false);
        binding.setViewModel(viewModel);
                builder.setView(binding.getRoot())
                .setPositiveButton(R.string.ajouter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       viewModel.onClickAddEtudiant();
                    }
                })
                .setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public CursusViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(CursusViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
