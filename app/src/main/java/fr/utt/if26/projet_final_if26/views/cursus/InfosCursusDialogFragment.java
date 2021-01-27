package fr.utt.if26.projet_final_if26.views.cursus;

import android.app.Dialog;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.DialogInfosCursusBinding;
import fr.utt.if26.projet_final_if26.models.NombreCreditsCategorie;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class InfosCursusDialogFragment extends DialogFragment {

    private final CursusViewModel viewModel;
    private DialogInfosCursusBinding binding;

    private int undoneColor;
    private int doneColor;

    private ColorFilter doneFilter;
    private ColorFilter undoneFilter;

    public InfosCursusDialogFragment(CursusViewModel viewModel) {
        super();
        this.viewModel = viewModel;
        viewModel.getNombreCreditsCategorie().observe(this, this::updateProgressCredits);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        undoneColor = ContextCompat.getColor(context, R.color.undone);
        doneColor = ContextCompat.getColor(context, R.color.done);


        doneFilter = new PorterDuffColorFilter(doneColor, PorterDuff.Mode.MULTIPLY);
        undoneFilter = new PorterDuffColorFilter(undoneColor, PorterDuff.Mode.MULTIPLY);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_infos_cursus, null, false);
        binding.setViewModel(viewModel);
        builder.setView(binding.getRoot())
                .setPositiveButton(R.string.fermer, (dialog, id) -> {

                });
        return builder.create();
    }

    private void updateProgressCredits(NombreCreditsCategorie mcc) {

        binding.infosCsNumberTv.setText(Integer.toString(mcc.getCs()));
        binding.progressBarInfosCs.setMax(mcc.getMaxProgress().get(0));
        binding.progressBarInfosCs.setProgress(mcc.getCs());
        if (mcc.getCs() >= 24) {
            binding.infosCsNumberTv.setTextColor(doneColor);
            binding.progressBarInfosCs.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosCsNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosCs.getProgressDrawable().setColorFilter(undoneFilter);

        }

        binding.infosTmNumberTv.setText(Integer.toString(mcc.getTm()));
        binding.progressBarInfosTm.setMax(mcc.getMaxProgress().get(1));
        binding.progressBarInfosTm.setProgress(mcc.getTm());

        if (mcc.getTm() >= 24) {
            binding.infosTmNumberTv.setTextColor(doneColor);
            binding.progressBarInfosTm.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosTmNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosTm.getProgressDrawable().setColorFilter(undoneFilter);

        }

        binding.infosCstmNumberTv.setText(Integer.toString(mcc.getTm() + mcc.getCs()));
        binding.progressBarInfosCstm.setMax(84);
        binding.progressBarInfosCstm.setProgress(mcc.getCs() + mcc.getTm());


        if (mcc.getCs() + mcc.getTm() >= 84) {
            binding.infosCstmNumberTv.setTextColor(doneColor);
            binding.progressBarInfosCstm.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosCstmNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosCstm.getProgressDrawable().setColorFilter(undoneFilter);

        }

        binding.infosStNumberTv.setText(Integer.toString(mcc.getSt()));
        binding.progressBarInfosSt.setMax(mcc.getMaxProgress().get(2));
        binding.progressBarInfosSt.setProgress(mcc.getSt());

        if (mcc.getSt() >= 60) {
            binding.infosStNumberTv.setTextColor(doneColor);
            binding.progressBarInfosSt.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosStNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosSt.getProgressDrawable().setColorFilter(undoneFilter);

        }

        binding.infosEcNumberTv.setText(Integer.toString(mcc.getEc()));
        binding.progressBarInfosEc.setMax(mcc.getMaxProgress().get(3));
        binding.progressBarInfosEc.setProgress(mcc.getEc());
        if (mcc.getEc() >= 12) {
            binding.infosEcNumberTv.setTextColor(doneColor);
            binding.progressBarInfosEc.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosEcNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosEc.getProgressDrawable().setColorFilter(undoneFilter);

        }
        binding.infosMeNumberTv.setText(Integer.toString(mcc.getMe()));
        binding.progressBarInfosMe.setMax(mcc.getMaxProgress().get(4));
        binding.progressBarInfosMe.setProgress(mcc.getMe());

        if (mcc.getMe() >= 4) {
            binding.infosMeNumberTv.setTextColor(doneColor);
            binding.progressBarInfosMe.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosMeNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosMe.getProgressDrawable().setColorFilter(undoneFilter);

        }
        binding.progressBarInfosHt.setMax(mcc.getMaxProgress().get(5));
        binding.progressBarInfosHt.setProgress(mcc.getHt());
        binding.infosHtNumberTv.setText(Integer.toString(mcc.getHt()));

        if (mcc.getHt() >= 4) {
            binding.infosHtNumberTv.setTextColor(doneColor);
            binding.progressBarInfosHt.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosHtNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosHt.getProgressDrawable().setColorFilter(undoneFilter);

        }
        binding.infosMehtNumberTv.setText(Integer.toString(mcc.getMe() + mcc.getHt()));
        binding.progressBarInfosMeht.setMax(16);
        binding.progressBarInfosMeht.setProgress(mcc.getMe() + mcc.getHt());


        if (mcc.getHt() + mcc.getMe() >= 16) {
            binding.infosMehtNumberTv.setTextColor(doneColor);
            binding.progressBarInfosMeht.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosMehtNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosMeht.getProgressDrawable().setColorFilter(undoneFilter);

        }

        binding.infosTotalNumberTv.setText(Integer.toString(mcc.getTotal()));
        binding.progressBarInfosTotal.setMax(mcc.getMaxProgress().get(6));
        binding.progressBarInfosTotal.setProgress(mcc.getTotal());

        if (mcc.getTotal() >= 180) {
            binding.infosTotalNumberTv.setTextColor(doneColor);
            binding.progressBarInfosTotal.getProgressDrawable().setColorFilter(doneFilter);
        } else {
            binding.infosTotalNumberTv.setTextColor(undoneColor);
            binding.progressBarInfosTotal.getProgressDrawable().setColorFilter(undoneFilter);

        }

        binding.infosHpNumberTv.setText(Integer.toString(mcc.getHp()));
        binding.progressBarInfosHp.setMax(mcc.getMaxProgress().get(7));
        binding.progressBarInfosHp.setProgress(mcc.getHp());


    }
}
