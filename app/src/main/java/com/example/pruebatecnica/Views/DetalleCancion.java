package com.example.pruebatecnica.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pruebatecnica.Models.Playlist;
import com.example.pruebatecnica.R;

public class DetalleCancion extends AppCompatActivity {
    private Playlist playlist;
    private TextView txt_detalle_nombre, txt_detalle_artista, txt_detalle_oyentes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cancion);
        txt_detalle_nombre = findViewById(R.id.txt_detalle_nombre);
        txt_detalle_artista = findViewById(R.id.txt_detalle_artista);
        txt_detalle_oyentes = findViewById(R.id.txt_detalle_oyentes);

        int id_cancion = getIntent().getIntExtra("id_cancion", 0);
        if(id_cancion != 0){
            playlist = Playlist.Find(this, id_cancion);
            txt_detalle_nombre.setText(playlist.getNombre());
            txt_detalle_artista.setText(playlist.getArtista());
            txt_detalle_oyentes.setText(playlist.getOyentes());
        }
    }
}
