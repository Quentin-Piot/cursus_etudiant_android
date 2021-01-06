package fr.utt.if26.projet_final_if26.views.etudiant;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class AdapterRecyclerListeCursus extends RecyclerView.Adapter<AdapterRecyclerListeCursus.CursusHolder> {

    private final EtudiantViewModel viewModel;
    private final EtudiantActivity etudiantActivity;
    private List<Cursus> cursusList;

    public AdapterRecyclerListeCursus(List<Cursus> cursusList, EtudiantViewModel viewModel, EtudiantActivity etudiantActivity) {
        this.cursusList = cursusList;
        this.viewModel = viewModel;
        this.etudiantActivity = etudiantActivity;
    }

    @NonNull
    @Override
    public CursusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCursusBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cursus, parent, false);
        return new CursusHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CursusHolder holder, int position) {
        holder.binding.setCursus(cursusList.get(position));
        holder.binding.setViewModel(viewModel);
        holder.binding.setActivity(etudiantActivity);
        holder.binding.executePendingBindings();

    }

    public List<Cursus> getCursusList() {
        return cursusList;
    }

    public void setCursusList(List<Cursus> cursusList) {
        this.cursusList = cursusList;
    }

    @Override
    public int getItemCount() {
        return this.cursusList.size();
    }

    class CursusHolder extends RecyclerView.ViewHolder {

        private final ItemCursusBinding binding;

        public CursusHolder(ItemCursusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}
