package fr.utt.if26.projet_final_if26.views.cursus;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.databinding.ItemSemestreHistoriqueBinding;
import fr.utt.if26.projet_final_if26.models.entities.Semestre;

public class AdapterRecyclerHistoriqueSemestres extends RecyclerView.Adapter<AdapterRecyclerHistoriqueSemestres.SemestreHistoriqueHolder> {

    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final AddSemestreActivity activity;
    private List<Semestre> listeSemestres;


    public AdapterRecyclerHistoriqueSemestres(List<Semestre> listeSemestres, AddSemestreActivity activity) {

        this.listeSemestres = listeSemestres;
        this.activity = activity;
    }


    @NonNull
    @Override
    public SemestreHistoriqueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSemestreHistoriqueBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_semestre_historique, parent, false);
        return new SemestreHistoriqueHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SemestreHistoriqueHolder holder, int position) {

        holder.binding.setSemestre(listeSemestres.get(position));
        holder.binding.executePendingBindings();
        holder.binding.getRoot().setOnClickListener(v -> activity.onClickHistoriqueSemestre(listeSemestres.get(position).getLabel()));
    }

    public void setListeSemestres(List<Semestre> listeSemestres) {
        this.listeSemestres = listeSemestres;
    }

    @Override
    public int getItemCount() {
        return this.listeSemestres.size();
    }

    static class SemestreHistoriqueHolder extends RecyclerView.ViewHolder {

        private final ItemSemestreHistoriqueBinding binding;


        public SemestreHistoriqueHolder(ItemSemestreHistoriqueBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }
}
