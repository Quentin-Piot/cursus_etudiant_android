package fr.utt.if26.projet_final_if26.views.semestre;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemModuleHistoriqueBinding;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.viewmodels.SemestreViewModel;

public class AdapterRecyclerListeHistoriqueModules extends RecyclerView.Adapter<AdapterRecyclerListeHistoriqueModules.ModuleHolder> {

    private final SemestreViewModel viewModel;
    private List<Module> modules;
    private int numberAddedModules = 0;

    public AdapterRecyclerListeHistoriqueModules(List<Module> modules, SemestreViewModel viewModel) {
        this.modules = modules;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemModuleHistoriqueBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_module_historique, parent, false);
        return new ModuleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {

        holder.binding.setViewModel(viewModel);
        holder.binding.setModule(modules.get(position));
        if (numberAddedModules >= 8) holder.binding.button2.setVisibility(View.INVISIBLE);
        else if (holder.binding.button2.getVisibility() == View.INVISIBLE)
            holder.binding.button2.setVisibility(View.VISIBLE);
        holder.binding.executePendingBindings();

    }


    @Override
    public int getItemCount() {
        return this.modules.size();
    }

    public void setNumberAddedModules(int numberAddedModules) {
        this.numberAddedModules = numberAddedModules;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    static class ModuleHolder extends RecyclerView.ViewHolder {

        private final ItemModuleHistoriqueBinding binding;

        public ModuleHolder(ItemModuleHistoriqueBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }
}
