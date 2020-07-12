package com.example.pruebatecnica.Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pruebatecnica.Models.Playlist;
import com.example.pruebatecnica.Views.AgregarCancion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddCancion extends AsyncTask<Void,Void,String> {
    private Context Context;
    private ProgressDialog progressDialog;
    private  boolean error;
    private String cancion_nombre;
    public Playlist playlist = new Playlist();

    public AddCancion(Context Context, String cancion) {
        this.Context = Context;
        this.cancion_nombre = cancion;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(Context, "Canciones", "buscando cancion...");
    }

    @Override
    protected String doInBackground(Void... params) {
        String uri = "http://ws.audioscrobbler.com/2.0/?method=track.search&track="+cancion_nombre+"&api_key=b284db959637031077380e7e2c6f2775&format=json";
        URL url = null;
        try {
            url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestProperty("Accept", "*/*");
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();
            os.close();

            int responseCode=urlConnection.getResponseCode();
            if(responseCode== HttpURLConnection.HTTP_OK){
                BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb= new StringBuffer("");
                String linea="";
                while ((linea=in.readLine())!= null){
                    sb.append(linea);
                    break;
                }
                in.close();
                String json = "";
                json = sb.toString();
                JSONObject jo = null;
                jo = new JSONObject(json);

                JSONObject json_canciones = null;
                json_canciones = new JSONObject(json);
                json_canciones = jo.getJSONObject("results");
                JSONObject jo_trackmatches = json_canciones.getJSONObject("trackmatches");

                JSONArray array_canciones = jo_trackmatches.getJSONArray("track");
                for (int i=0; i<array_canciones.length();i++){

                    JSONObject cancion = array_canciones.getJSONObject(i);
                    playlist.setNombre(cancion.getString("name"));
                    playlist.setArtista(cancion.getString("artist"));
                    playlist.setOyentes(cancion.getString("listeners"));
                    return "ok";
                }
                this.error = false;

            }
            else{
                Toast.makeText(Context,"Ocurrio un error al procesar la solicitud",Toast.LENGTH_LONG).show();
                return "Ocurrio un error al procesar la peticion";
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
            error = true;
            return "Error de ruta: "+e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            error = true;
            return "Error de conexion a la ruta: "+e.getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
            error = true;
            return "Error con los parametros del envio: "+e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            error = true;
            return "La URL no es valida";
        }
        error = true;
        return "Ocurrió un error al realizar la solicitud";
    }

    @Override
    protected void onPostExecute(String mensaje) {
        super.onPostExecute(mensaje);
        progressDialog.dismiss();
        if (mensaje.equals("ok")){
            AgregarCancion.my_activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AgregarCancion.txt_nombre_cancion.setText(playlist.getNombre());
                    AgregarCancion.txt_artista_cancion.setText(playlist.getArtista());
                    AgregarCancion.txt_oyentes.setText(playlist.getOyentes());
                }
            });
        }else{
            Toast.makeText(Context,mensaje , Toast.LENGTH_LONG).show();
        }

    }

}
