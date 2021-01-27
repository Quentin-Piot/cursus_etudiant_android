package fr.utt.if26.projet_final_if26.views.cursus.adapters;


import android.graphics.Color;
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
    private final String etudiantProgramme;

    public AdapterRecyclerNestedModules(List<Module> modules, String etudiantProgramme) {
        this.modules = modules;
        this.etudiantProgramme = etudiantProgramme;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNestedModuleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_nested_module, parent, false);
        return new ModuleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {

        Module actualModule = modules.get(position);
        if (!actualModule.getProgramme().equals("TB") && !actualModule.getProgramme().equals(etudiantProgramme)) {
            actualModule.setProgramme("HP");
            holder.binding.tvCetegorieNested.setTextColor(Color.GRAY);
            holder.binding.tvCreditsNested.setTextColor(Color.GRAY);
            holder.binding.tvParcoursNested.setTextColor(Color.GRAY);
            holder.binding.tvSigleNested.setTextColor(Color.GRAY);

        }
        holder.binding.setModule(actualModule);
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
