package com.example.programacion5.menudrawertabas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.programacion5.menudrawertabas.Transicion;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaTerrenos extends Fragment {


    public ListaTerrenos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_terrenos, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }
}
