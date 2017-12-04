package Persistencia;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PROGRAMACION5 on 04/12/2017.
 */

public class dbOfertaLaboral {
    private DataBaseApp ofertaLaboral;

    public dbOfertaLaboral(Activity activity) {

        ofertaLaboral = new DataBaseApp(activity, "dbOfertaLaboral", null, 1);
    }
    public String levantar(String ofertaLaboral) {
        SQLiteDatabase db = this.ofertaLaboral.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from ofertaLaboral" , null);

        if(!fila.moveToFirst()){
            db.close();
            return null;
        }else{

            String contactGuia = new String (fila.getString(1));
            db.close();
            return contactGuia;
        }

    }
    public void guardar(String laboralOf) {
        if(laboralOf != null){
            SQLiteDatabase db = ofertaLaboral.getWritableDatabase();
            ContentValues registro = new ContentValues();
            Cursor fila = db.rawQuery("select * from ofertaLaboral", null);


            boolean b;
            if(!fila.moveToFirst()){
                b=false;
            }else{
                b=true;
            }
            registro.put("contenido",laboralOf);
            Date fecha = new Date();
            String dia = "";
            if(fecha.getDate() < 10){
                dia = dia.concat("0"+fecha.getDate());
            }else{
                dia = String.valueOf(fecha.getDate());
            }
            String mes = "";
            if(fecha.getMonth() < 9){
                mes = mes.concat("0"+(fecha.getMonth()+1));
            }else{
                mes = String.valueOf((fecha.getMonth()+1));
            }
            String s = (fecha.getYear()+1900)+"-"+mes+"-"+dia;
            registro.put("modificacion", s);
            if(b){
                db.update("ofertaLaboral",registro,"_id=1",null);
            }else{
                db.insert("ofertaLaboral", null, registro);
            }
            db.close();
        }
    }
    public Date getModificacion(){
        SQLiteDatabase db = ofertaLaboral.getWritableDatabase();
        Cursor fila = db.rawQuery("select modificacion from ofertaLaboral" , null);

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
