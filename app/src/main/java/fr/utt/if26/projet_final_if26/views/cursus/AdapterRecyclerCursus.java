package fr.utt.if26.projet_final_if26.views.cursus;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemCursusBinding;
import fr.utt.if26.projet_final_if26.models.entities.Cursus;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class AdapterRecyclerCursus extends RecyclerView.Adapter<AdapterRecyclerCursus.CursusHolder> {

    private List<Cursus> cursusList;
    private CursusViewModel viewModel;

    public AdapterRecyclerCursus(List<Cursus> cursusList, CursusViewModel viewModel) {
        this.cursusList = cursusList;
        this.viewModel = viewModel;
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
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return this.cursusList.size();
    }

    class CursusHolder extends RecyclerView.ViewHolder {

        private ItemCursusBinding binding;

        public CursusHolder(ItemCursusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}
