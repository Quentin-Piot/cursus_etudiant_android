package fr.utt.if26.projet_final_if26.views.semestre;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemModuleBinding;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.viewmodels.SemestreViewModel;

public class AdapterRecyclerListeModules extends RecyclerView.Adapter<AdapterRecyclerListeModules.ModuleHolder  > {

    private final List<Module> modules;
    private final SemestreViewModel viewModel;

    public AdapterRecyclerListeModules(List<Module> modules, SemestreViewModel viewModel) {
        this.modules = modules;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemModuleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_module, parent, false);
        return new ModuleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {

        holder.binding.setViewModel(viewModel);
        holder.binding.setModule(modules.get(position));

        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return this.modules.size();
    }

    static class ModuleHolder extends RecyclerView.ViewHolder {

        private final ItemModuleBinding binding;

        public ModuleHolder(ItemModuleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }
}
