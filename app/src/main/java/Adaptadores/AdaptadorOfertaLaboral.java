package Adaptadores;

import android.app.Activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.example.programacion5.menudrawertabas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PROGRAMACION5 on 07/12/2017.
 */

public class AdaptadorOfertaLaboral extends ArrayAdapter<List<List>> {
    private Activity activity;
    ArrayList<String> lista;



    public AdaptadorOfertaLaboral(FragmentActivity activity, ArrayList<String> lista) {
        super(activity, R.layout.ofertas);

        this.lista = lista;
        this.activity = activity;
    }



    static class ViewHolder {
        protected TextView texto;

    }

    public int getCount() { return lista.size();
    }

    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        // inflamos nuestra vista con el layout
        View view = null;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.ofertas, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.texto = (TextView) view.findViewById(R.id.parrafo);
        viewHolder.texto.setTextSize(20);
        // importante!!! establecemos el mensaje
        viewHolder.texto.setText(lista.get(position));


        return view;
    }



}
