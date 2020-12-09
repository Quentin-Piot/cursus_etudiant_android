package fr.utt.if26.projet_final_if26.views.cursus;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemModuleBinding;
import fr.utt.if26.projet_final_if26.databinding.ItemSemestreBinding;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class AdapterRecyclerListeModules extends RecyclerView.Adapter<AdapterRecyclerListeModules.ModuleHolder  > {

    private final List<Module> modules;

    public AdapterRecyclerListeModules(List<Module> modules) {
        this.modules = modules;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemModuleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_module, parent, false);
        return new ModuleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {

        //holder.binding.recyclerViewModules.setVisibility(View.GONE);
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return this.modules.size();
    }

    class ModuleHolder extends RecyclerView.ViewHolder {

        private final ItemModuleBinding binding;

        public ModuleHolder(ItemModuleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }
}
