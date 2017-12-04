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

import Persistencia.dbOfertaLaboral;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfertasLaboral extends Fragment {
    private dbOfertaLaboral dbOferta;
    private String laboral;
    private TextView titulo;
    private WebView web;


    String titul = " OFERTA LABORAL ";
    public OfertasLaboral() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_ofertas_laboral, container, false);;

        titulo = (TextView)v.findViewById(R.id.textView7);
        dbOferta = new dbOfertaLaboral(this.getActivity());
        laboral = dbOferta.levantar("ofertaLaboral");
        if(laboral != null){
            cargarVista(v);
        }

        titulo.setText(titul);


        // Inflate the layout for this fragment
        return v;
    }

    private void cargarVista(View v) {

        web = (WebView)v.findViewById(R.id.webGaceta);
        WebSettings settings = web.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setDefaultFontSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            String base64 = null;
            try {
                base64 = Base64.encodeToString(laboral.getBytes("UTF-8"), Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            web.loadData(base64, "text/html; charset=UTF-8", "base64");
        } else {
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
            web.loadData(header + laboral, "text/html; charset=UTF-8", null);
        }


    }

}
