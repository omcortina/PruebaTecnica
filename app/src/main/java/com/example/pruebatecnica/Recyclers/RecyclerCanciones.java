package com.example.pruebatecnica.Recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.Models.Playlist;
import com.example.pruebatecnica.R;

import java.util.List;

public class RecyclerCanciones extends RecyclerView.Adapter<RecyclerCanciones.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_nombre_cancion, txt_artista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombre_cancion = (TextView) itemView.findViewById(R.id.txt_nombre_cancion);
            txt_artista = (TextView) itemView.findViewById(R.id.txt_artista);
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
        Playlist playlist = listaPlaylist.get(position);
        holder.txt_nombre_cancion.setText(playlist.getNombre());
        holder.txt_artista.setText(playlist.getArtista());
    }

    @Override
    public int getItemCount() {
        return listaPlaylist.size();
    }
}
