package Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PROGRAMACION5 on 05/12/2017.
 */

public class DataBaseApp extends SQLiteOpenHelper {
    private String sqlCreateOfertaLaboral = "CREATE TABLE ofertaLaboral(_id INTEGER PRIMARY KEY,contenido TEXT,modificacion TEXT)";

    public DataBaseApp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateOfertaLaboral);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
