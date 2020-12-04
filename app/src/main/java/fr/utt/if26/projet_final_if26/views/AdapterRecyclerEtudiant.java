package fr.utt.if26.projet_final_if26.views;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemEtudiantBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.EtudiantViewModel;

public class AdapterRecyclerEtudiant extends RecyclerView.Adapter<AdapterRecyclerEtudiant.EtudiantHolder> {

    private List<Etudiant> etudiantList;
    private EtudiantViewModel viewModel;
    public AdapterRecyclerEtudiant(List<Etudiant> etudiantList, EtudiantViewModel viewModel) {
        this.etudiantList = etudiantList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public EtudiantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEtudiantBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_etudiant, parent,false);
        return new EtudiantHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantHolder holder, int position) {
        holder.binding.setEtudiant(etudiantList.get(position));
        holder.binding.setViewModel(viewModel);
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return this.etudiantList.size();
    }

    class EtudiantHolder extends RecyclerView.ViewHolder {

        private ItemEtudiantBinding binding;

        public EtudiantHolder(ItemEtudiantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}
