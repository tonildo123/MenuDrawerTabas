package Persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Contacts;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by PROGRAMACION5 on 04/12/2017.
 */

public class dbOfertaLaboral {
    private DataBaseApp ofertaLaboral;

    public dbOfertaLaboral(Activity activity) {
        ofertaLaboral = new DataBaseApp(activity, "DbOfertaLaboral", null, 1);
    }
    public void guardar(ArrayList<String> laboralOf, String nombre) {
        if(laboralOf != null){
            SQLiteDatabase db = ofertaLaboral.getWritableDatabase();

            Cursor fila = db.rawQuery("select * from "+ nombre, null);


            boolean b;
            if(!fila.moveToFirst()) {
                b = false;
            } else {
                b = true;
            }

            for (int i = 0; i < laboralOf.size(); i++) {
                ContentValues registro = new ContentValues();
                registro.put("contenido", laboralOf.get(i));
                Date fecha = new Date();
                String dia = "";
                if (fecha.getDate() < 10) {
                    dia = dia.concat("0" + fecha.getDate());
                } else {
                    dia = String.valueOf(fecha.getDate());
                }
                String mes = "";
                if (fecha.getMonth() < 9) {
                    mes = mes.concat("0" + (fecha.getMonth() + 1));
                } else {
                    mes = String.valueOf((fecha.getMonth() + 1));
                }
                String s = (fecha.getYear() + 1900) + "-" + mes + "-" + dia;
                registro.put("modificacion", s);
                if (b) {
                    db.update("ofertaLaboral", registro, "_id=" + (i + 1), null);
                } else {
                    db.insert("ofertaLaboral", null, registro);
                }

            }

            db.close();
        }
    }
    public ArrayList levantar(String nombre) {
        SQLiteDatabase db = ofertaLaboral.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from "+ nombre , null);
        ArrayList contactGuia = new ArrayList();

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{

            while(fila.moveToNext()){

                contactGuia.add(fila.getString(1));
                }
            db.close();
            return contactGuia;
    }



    }

    public Date getModificacion(String nombre){
        SQLiteDatabase db = ofertaLaboral.getWritableDatabase();
        Cursor fila = db.rawQuery("select modificacion from "+ nombre , null);

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = null;
            String parsefecha = new String(fila.getString(0));
            try {

                fecha = formatoDelTexto.parse(parsefecha);

            } catch (ParseException ex) {

                ex.printStackTrace();

            }
            db.close();
            return fecha;
        }
    }

}
