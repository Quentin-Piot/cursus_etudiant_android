package fr.utt.if26.projet_final_if26.views.cursus;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemSemestreBinding;
import fr.utt.if26.projet_final_if26.generated.callback.OnClickListener;
import fr.utt.if26.projet_final_if26.models.entities.Module;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class AdapterRecyclerListeSemestres extends RecyclerView.Adapter<AdapterRecyclerListeSemestres.SemestreHolder> {

    private final List<Semestre> semestres;
    private final CursusViewModel viewModel;
    private final CursusActivity cursusActivity;


    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public AdapterRecyclerListeSemestres(List<Semestre> semestres, CursusViewModel viewModel, CursusActivity cursusActivity) {
        this.semestres = semestres;
        this.viewModel = viewModel;
        this.cursusActivity = cursusActivity;
    }


    @NonNull
    @Override
    public SemestreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSemestreBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_semestre, parent, false);
        return new SemestreHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SemestreHolder holder, int position) {

        holder.binding.setSemestre(semestres.get(position));
        holder.binding.setViewModel(viewModel);
        holder.binding.layoutCollapsed.setVisibility(View.GONE);
        holder.binding.executePendingBindings();

        List<Module> modules = new ArrayList<>();
        modules.add(new Module("da", "da", "da", 6, 1));
        modules.add(new Module("da", "da", "da", 6, 1));

        holder.moduleRecyclerView.setLayoutManager(new LinearLayoutManager(holder.moduleRecyclerView.getContext()));
        AdapterRecyclerListeModules adapterRecyclerListeModules = new AdapterRecyclerListeModules(modules);
        holder.moduleRecyclerView.setAdapter(adapterRecyclerListeModules);
        holder.moduleRecyclerView.setRecycledViewPool(viewPool);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = holder.binding.layoutCollapsed.getVisibility();
                if (visibility == View.GONE) {
                    holder.binding.layoutCollapsed.setVisibility(View.VISIBLE);
                    holder.binding.recyclerViewModules.scheduleLayoutAnimation();
                } else {
                    holder.binding.layoutCollapsed.setVisibility(View.GONE);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return this.semestres.size();
    }

    class SemestreHolder extends RecyclerView.ViewHolder {

        private final ItemSemestreBinding binding;
        private RecyclerView moduleRecyclerView;


        public SemestreHolder(ItemSemestreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            moduleRecyclerView = binding.recyclerViewModules;

        }


    }
}
