package fr.utt.if26.projet_final_if26.views;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.projet_final_if26.R;
import fr.utt.if26.projet_final_if26.models.entities.Etudiant;

public class AdapterRecyclerEtudiant extends RecyclerView.Adapter<AdapterRecyclerEtudiant.EtudiantHolder> {

    private List<Etudiant> etudiantList;

    public AdapterRecyclerEtudiant(List<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }

    @NonNull
    @Override
    public EtudiantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_etudiant, parent, false);
        return new EtudiantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantHolder holder, int position) {
        holder.display(this.etudiantList.get(position));

    }

    @Override
    public int getItemCount() {
        return this.etudiantList.size();
    }

    class EtudiantHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvFirstName;

        public EtudiantHolder(@NonNull View itemView) {
            super(itemView);

            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvFirstName = (TextView) itemView.findViewById(R.id.tv_firstname);

        }

        public void display(Etudiant etudiant) {

            this.tvName.setText(etudiant.getNom());
            this.tvFirstName.setText(etudiant.getPrenom());

        }
    }
}
