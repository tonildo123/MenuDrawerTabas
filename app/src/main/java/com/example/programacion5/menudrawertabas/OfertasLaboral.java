package com.example.programacion5.menudrawertabas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import Adaptadores.AdaptadorOfertaLaboral;
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

    private View v;
    private String titul = " OFERTA LABORAL ";


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

        dbOferta = new dbOfertaLaboral(this.getActivity());

        obetenerOfertaLaboral();
        cargarVista(v);
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }


    private void cargarVista(View v) {


            lista = dbOferta.levantar("ofertaLaboral");
            listView = (ListView) v.findViewById(R.id.list);
            listAdapter = new AdaptadorOfertaLaboral(this.getActivity(), lista);
            listView.setAdapter(listAdapter);

    }

    private void obetenerOfertaLaboral() {
        Date fechaactual = new Date();

        if( dbOferta.levantar("ofertaLaboral") == null || (fechaactual.getTime()-dbOferta.getModificacion("ofertaLaboral").getTime()) > 604800000){
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    lista = serv.getInstance().laboralOf("https://clasificados.lagaceta.com.ar/categoria/137/pedidos-empleos");
                    dbOferta.guardar(lista, "ofertaLaboral");
                }
            });
            hilo.start();
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}}

}
