package com.example.mastersdentf.models;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mastersdentf.ServicoDetailsFragment;
import com.example.mastersdentf.helpers.QueueUtils;
import com.example.mastersdentf.ui.Tratamiento.TratamientoFragment;

public class Servicios {
    public int id;
    public String name;
    public String description;
    public String price;
    public String url_image;
    public Servicios(int _id,String _first_name, String _price, String _url_image, String _description){
        this.id=_id;
        this.name=_first_name;
        this.description=_description;
        this.price=_price;
        this.url_image=_url_image;
    }
    public static ArrayList getCollection(){
        ArrayList<Servicios> collection=new ArrayList<>();
        collection.add(new Servicios(1,"Pepe","","Perez",""));
        collection.add(new Servicios(2,"Maria","","Mesa",""));
        collection.add(new Servicios(3,"Alex","","Quispe",""));
        return collection;
    }



    public static void injectServicioFromCloud(final QueueUtils.QueueObject o,
                                             final Servicios servicio,
                                             final ServicoDetailsFragment _interface){
        String url = "https://d87a16a4ec5f.ngrok.io/api/auth/servicio/"+servicio.id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("object")) {

                            try {
                                JSONObject objeto = response.getJSONObject("object");
                                servicio.name=objeto.getString("nombre");
                                servicio.description=objeto.getString("description");
                                servicio.price=objeto.getString("price");
                                servicio.url_image=objeto.getString("image");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refresh(); // Esta función debemos implementarla
                            // en nuestro activity
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int a = 0;
                        a++;
                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }

    public static void injectServicosFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Servicios> servicio,
                                               final TratamientoFragment _interface) {
        String url = "https://d87a16a4ec5f.ngrok.io/api/auth/servicio";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("objects")) {

                            try {
                                JSONArray list = response.getJSONArray("objects");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    servicio.add(new Servicios(o.getInt("id"),o.getString("nombre"),o.getString("price"),
                                            o.getString("image"),o.getString("description")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList(); // Esta función debemos implementarla
                            // en nuestro activity
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int a = 0;
                        a++;
                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }
}