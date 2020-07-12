package com.example.pruebatecnica.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pruebatecnica.BD.Database;
import com.example.pruebatecnica.Config.Config;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int Id;
    private String Nombre, Artista, Album;
    private String Oyentes;


    public Playlist() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String artista) {
        Artista = artista;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getOyentes() {
        return Oyentes;
    }

    public void setOyentes(String oyentes) {
        Oyentes = oyentes;
    }

    public void Save(Context context){
        Database bd = new Database(context, Config.database_name, null, 1);
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre_cancion", this.Nombre);
        registro.put("artista", this.Artista);
        registro.put("album", this.Album);
        registro.put("oyentes", this.Oyentes);
        db.insert("Playlist", null, registro);
        db.close();
    }

    public static Playlist Find(Context context, int id){
        Database bd = new Database(context, Config.database_name, null, 1);
        SQLiteDatabase db = bd.getWritableDatabase();

        String sql = "select * from Playlist where id_playlist="+id;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            Playlist playlist = new Playlist();
            playlist.Id = Integer.parseInt(cursor.getString(0));
            playlist.Nombre = cursor.getString(1);
            playlist.Artista = cursor.getString(2);
            playlist.Album = cursor.getString(3);
            playlist.Oyentes = cursor.getString(4);
            return playlist;
        }
        db.close();
        return null;
    }

    public static List<Playlist> FindAll(Context context){
        Database bd = new Database(context, Config.database_name, null, 1);
        SQLiteDatabase db = bd.getWritableDatabase();
        List<Playlist> lista = new ArrayList<>();

        String sql = "select * from Playlist";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do{
                Playlist playlist = new Playlist();
                playlist.Id = Integer.parseInt(cursor.getString(0));
                playlist.Nombre = cursor.getString(1);
                playlist.Artista = cursor.getString(2);
                playlist.Album = cursor.getString(3);
                playlist.Oyentes = cursor.getString(4);
                lista.add(playlist);
            }while(cursor.moveToNext());
            db.close();
            return lista;
        }
        db.close();
        return null;
    }
}
