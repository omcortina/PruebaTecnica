package com.example.pruebatecnica.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebatecnica.Models.Playlist;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Services.AddCancion;
import com.example.pruebatecnica.Services.ListarCanciones;

public class AgregarCancion extends AppCompatActivity {
    public static Activity my_activity;
    public static EditText txt_nombre_cancion, txt_artista_cancion;
    public Button btn_buscar, btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cancion);
        txt_nombre_cancion = findViewById(R.id.txt_nombre_cancion);
        txt_artista_cancion = findViewById(R.id.txt_artista);
        my_activity = this;
        btn_buscar = (Button) findViewById(R.id.btn_buscar);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText search = (EditText) findViewById(R.id.search_cancion);
                String nombre_cancion = search.getText().toString();
                AddCancion service = new AddCancion(AgregarCancion.this, nombre_cancion);
                service.execute();
            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre_cancion = txt_nombre_cancion.getText().toString();
                String artista = txt_artista_cancion.getText().toString();
                Playlist playlist = new Playlist();
                playlist.Nombre = nombre_cancion;
                playlist.Artista = artista;
                playlist.Album = "";
                playlist.Save(AgregarCancion.this);
                ListarCanciones service = new ListarCanciones(AgregarCancion.this);
                service.execute();
                Toast.makeText(AgregarCancion.this, "Se agrego la cancion", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AgregarCancion.this, ListaCanciones.class);
                startActivity(intent);
            }
        });
    }
}
