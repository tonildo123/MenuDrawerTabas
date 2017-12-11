package com.example.programacion5.menudrawertabas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import Persistencia.dbOfertaLaboral;
import Servicios.servicioOfertaLaboral;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfertasLaboral extends Fragment {
    private servicioOfertaLaboral serv;



    private ArrayList<String> lista = new ArrayList();
    private ListView listView;
    private AdaptadorOfertaLaboral listAdapter;
    private dbOfertaLaboral dbOferta;


    private String laboral;
    private TextView titulo;
//    private WebView web;

    private View v;


    String titul = " OFERTA LABORAL ";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_ofertas_laboral, container, false);;
        listView = (ListView) v.findViewById(R.id.list);


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
        for(int i=0; i < lista.size(); i++){
            dbOferta.guardar("ofertaLaboral", lista.get(i));
        }

        if(lista != null){
            listView = (ListView) v.findViewById(R.id.list);
            listAdapter = new AdaptadorOfertaLaboral(this.getActivity(), lista);
            listView.setAdapter(listAdapter);
        }




    }

    private void obetenerOfertaLaboral() {
        Date fechaactual = new Date();

        if( dbOferta.levantar("ofertaLaboral") == null || (fechaactual.getTime()-dbOferta.getModificacion("ofertaLaboral").getTime()) > 604800000){
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    lista = serv.getInstance().laboralOf("https://clasificados.lagaceta.com.ar/mercado-laboral");
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
