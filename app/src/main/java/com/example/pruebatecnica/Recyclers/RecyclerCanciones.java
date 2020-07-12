package com.example.pruebatecnica.Recyclers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.Models.Playlist;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Views.DetalleCancion;

import java.util.List;

public class RecyclerCanciones extends RecyclerView.Adapter<RecyclerCanciones.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_nombre_cancion, txt_artista;
        private CardView card_cancion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombre_cancion = (TextView) itemView.findViewById(R.id.txt_nombre_cancion);
            txt_artista = (TextView) itemView.findViewById(R.id.txt_artista);
            card_cancion = itemView.findViewById(R.id.card_cancion);
        }
    }

    public List<Playlist> listaPlaylist;
    public Context context;

    public RecyclerCanciones(List<Playlist> listaPlaylist, Context context) {
        this.listaPlaylist = listaPlaylist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Playlist playlist = listaPlaylist.get(position);
        holder.txt_nombre_cancion.setText(playlist.getNombre());
        holder.txt_artista.setText(playlist.getArtista());
        holder.card_cancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_cancion = playlist.getId();
                Intent intent_detalle = new Intent(context, DetalleCancion.class);
                intent_detalle.putExtra("id_cancion", id_cancion);
                context.startActivity(intent_detalle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPlaylist.size();
    }
}
