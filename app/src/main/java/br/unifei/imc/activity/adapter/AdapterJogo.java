package br.unifei.imc.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.RecyclerView;

import br.unifei.imc.R;

import java.util.ArrayList;
import java.util.List;

import br.unifei.imc.jogos.Games;

public class AdapterJogo extends RecyclerView.Adapter<AdapterJogo.MyViewHolder> {

    private List<Games> listaJogos;

    public AdapterJogo(List<Games> lista) {
        this.listaJogos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Games game = listaJogos.get(position);
        holder.nome.setText(game.getNome());
        holder.valor.setText(game.getValor().toString());
        holder.desc.setText(game.getDescricao());
        holder.fabricante.setText(game.getFabricante());
        holder.qtd.setText(Integer.toString(game.getQtd()));


    }

    @Override
    public int getItemCount() {
        return listaJogos.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        //TextView plataforma;
        TextView nome;
        TextView valor;
        TextView desc;
        TextView fabricante;
        TextView qtd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNomeJogoLista);
            valor = itemView.findViewById(R.id.textPrecoJogoLista);
            desc = itemView.findViewById(R.id.textDescJogoLista);
            fabricante = itemView.findViewById(R.id.textFabJogoLista);
            qtd = itemView.findViewById(R.id.textQtdJogoLista);
        }
    }

}
