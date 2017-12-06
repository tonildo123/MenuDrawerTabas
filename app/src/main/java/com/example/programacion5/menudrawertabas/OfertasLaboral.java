package com.example.programacion5.menudrawertabas;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import Persistencia.dbOfertaLaboral;
import Servicios.servicioOfertaLaboral;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfertasLaboral extends Fragment {
    private servicioOfertaLaboral serv;

    private dbOfertaLaboral dbOferta;
    private String laboral;
    private TextView titulo;
    private WebView web;
    private ArrayList<String> lista = new ArrayList<>();
    private View v;


    String titul = " OFERTA LABORAL ";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_ofertas_laboral, container, false);;

        titulo = (TextView)v.findViewById(R.id.textView7);

        titulo.setText(titul);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
//        laboral = dbOferta.levantar("ofertaLaboral");
        dbOferta = new dbOfertaLaboral(this.getActivity());
        obetenerOfertaLaboral();
        cargarVista(v);
    }



    private void cargarVista(View v) {

    }

    private void obetenerOfertaLaboral() {
        Date fechaactual = new Date();
        if( dbOferta.levantar("ofertaLaboral") == null || (fechaactual.getTime()-dbOferta.getModificacion("ofertaLaboral").getTime()) > 604800000){
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    lista = serv.getInstance().laboralOf(serv.getInstance().getUrlOfertaLaboral());
                }
            });
            hilo.start();
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
