package com.example.programacion5.menudrawertabas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;


/**
 * Created by PROGRAMACION5 on 05/03/2018.
 */

public class Transicion {
    private static final Transicion animacion = new Transicion();

    public static Transicion getInstance() {
        return animacion;
    }

    public void transicionFragments(View v, final FragmentActivity activity){
        if (v == null) {
            return;
        }
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    FragmentManager m = activity.getSupportFragmentManager();
                    m.beginTransaction().replace(R.id.contenedor, new OfertasLaboral()).commit();
                    return true;
                }
                return false;
            }
        });
    }



}
