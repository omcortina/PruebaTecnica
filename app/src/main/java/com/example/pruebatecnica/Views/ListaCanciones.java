package com.example.pruebatecnica.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pruebatecnica.Models.Playlist;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Services.ListarCanciones;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaCanciones extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static Activity activity_main;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.btn_add_cancion);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activity_main = this;
        ListarCanciones servicio = new ListarCanciones(ListaCanciones.this);
        servicio.execute();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(ListaCanciones.this, AgregarCancion.class);
                startActivity(inten);
            }
        });
    }
}
