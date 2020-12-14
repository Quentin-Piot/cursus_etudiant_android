package fr.utt.if26.projet_final_if26.views.semestre;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemHistoriqueModuleBinding;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.viewmodels.SemestreViewModel;

public class AdapterRecyclerListeHistoriqueModules extends RecyclerView.Adapter<AdapterRecyclerListeHistoriqueModules.ModuleHolder> {

    private List<Module> modules;
    private final SemestreViewModel viewModel;

    public AdapterRecyclerListeHistoriqueModules(List<Module> modules, SemestreViewModel viewModel) {
        this.modules = modules;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoriqueModuleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_historique_module, parent, false);
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

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    static class ModuleHolder extends RecyclerView.ViewHolder {

        private final ItemHistoriqueModuleBinding binding;

        public ModuleHolder(ItemHistoriqueModuleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }
}
