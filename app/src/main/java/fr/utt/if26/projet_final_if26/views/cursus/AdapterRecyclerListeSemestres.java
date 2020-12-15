package fr.utt.if26.projet_final_if26.views.cursus;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemSemestreBinding;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;
import fr.utt.if26.projet_final_if26.viewmodels.CursusViewModel;

public class AdapterRecyclerListeSemestres extends RecyclerView.Adapter<AdapterRecyclerListeSemestres.SemestreHolder> {

    private List<Semestre> listeSemestres;
    private final CursusViewModel viewModel;
    private final CursusActivity cursusActivity;

    private int lastPosition = 0;


    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public AdapterRecyclerListeSemestres(List<Semestre> listeSemestres, CursusViewModel viewModel, CursusActivity cursusActivity) {

        this.viewModel = viewModel;
        this.cursusActivity = cursusActivity;
        this.listeSemestres = listeSemestres;
    }


    @NonNull
    @Override
    public SemestreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSemestreBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_semestre, parent, false);
        return new SemestreHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SemestreHolder holder, int position) {

        holder.binding.setSemestre(listeSemestres.get(position));
        holder.binding.setViewModel(viewModel);
        holder.binding.setCursusActivity(cursusActivity);
        String moduleText = " module";
        if (listeSemestres.get(position).getListeModules().size() > 1) moduleText += "s";
        holder.binding.semestreModulesNumberTv.setText(listeSemestres.get(position).getListeModules().size() + moduleText);
        if (position > 0) {
            holder.binding.layoutCollapsed.setVisibility(View.GONE);
        }
        holder.binding.executePendingBindings();


        holder.moduleRecyclerView.setLayoutManager(new LinearLayoutManager(holder.moduleRecyclerView.getContext()));

        AdapterRecyclerNestedModules adapterRecyclerListeModules = new AdapterRecyclerNestedModules(listeSemestres.get(position).getListeModules());
        holder.moduleRecyclerView.setAdapter(adapterRecyclerListeModules);
        holder.moduleRecyclerView.setRecycledViewPool(viewPool);
        holder.binding.getRoot().setOnClickListener(v -> {
            int visibility = holder.binding.layoutCollapsed.getVisibility();
            if (visibility == View.GONE) {
                holder.binding.layoutCollapsed.setVisibility(View.VISIBLE);
                holder.binding.recyclerViewModules.scheduleLayoutAnimation();
            } else {
                holder.binding.layoutCollapsed.setVisibility(View.GONE);
            }

        });


    }

    public List<Semestre> getSemestreCursus() {
        return listeSemestres;
    }

    public void setListeSemestres(List<Semestre> listeSemestres) {
        this.listeSemestres = listeSemestres;
    }

    @Override
    public int getItemCount() {
        return this.listeSemestres.size();
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
