package fr.utt.if26.projet_final_if26.views.homepage;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemEtudiantBinding;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;
import fr.utt.if26.projet_final_if26.viewmodels.AccueilViewModel;

public class AdapterRecyclerListeEtudiants extends RecyclerView.Adapter<AdapterRecyclerListeEtudiants.EtudiantHolder> {

    private final AccueilViewModel viewModel;
    private List<Etudiant> etudiantList;

    public AdapterRecyclerListeEtudiants(List<Etudiant> etudiantList, AccueilViewModel viewModel) {
        this.etudiantList = etudiantList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public EtudiantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEtudiantBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_etudiant, parent, false);
        return new EtudiantHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantHolder holder, int position) {
        holder.binding.setEtudiant(etudiantList.get(position));
        holder.binding.setViewModel(viewModel);
        holder.binding.executePendingBindings();

    }

    public void setEtudiantList(List<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }

    @Override
    public int getItemCount() {
        return this.etudiantList.size();
    }

    class EtudiantHolder extends RecyclerView.ViewHolder {

        private final ItemEtudiantBinding binding;

        public EtudiantHolder(ItemEtudiantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}
