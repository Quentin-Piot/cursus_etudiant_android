package fr.utt.if26.projet_final_if26.views.cursus;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemNestedModuleBinding;
import fr.utt.if26.projet_final_if26.models.entities.Module;

public class AdapterRecyclerNestedModules extends RecyclerView.Adapter<AdapterRecyclerNestedModules.ModuleHolder> {

    private final List<Module> modules;

    public AdapterRecyclerNestedModules(List<Module> modules) {
        this.modules = modules;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNestedModuleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_nested_module, parent, false);
        return new ModuleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {

        holder.binding.setModule(modules.get(position));
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return this.modules.size();
    }

    static class ModuleHolder extends RecyclerView.ViewHolder {

        private final ItemNestedModuleBinding binding;

        public ModuleHolder(ItemNestedModuleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }
}
