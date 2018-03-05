package com.example.programacion5.menudrawertabas;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contacto extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_contacto, container, false);

        View contenedor = (View) container.getParent();
        appBar = (AppBarLayout) contenedor.findViewById(R.id.appbar);
        // retorna la vista del fragmento asociado
        tabs = new TabLayout(getActivity());
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        tabs.setBackgroundColor(Color.parseColor("#42a5f5"));
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#bbdefb"));
        // inserta el tab en el appbar
        appBar.addView(tabs);
        viewPager = (ViewPager) vista.findViewById(R.id.pager);
        ViewPagerAdapter paginaAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(paginaAdapter);
        tabs.setupWithViewPager(viewPager);
        // retornamos la vista cargada
        return vista;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appBar.removeView(tabs);
    }
    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        String[] titulo = {"Shark Informatica ", "Shark Studio"};

        @Override
        public Fragment getItem(int position) {
            // instanciamos los fragmentos de la clase tabs para crear los objetos
            switch (position) {

                case 0: SharkDesign tdos = new SharkDesign();
                    return tdos;
                case 1: SharkStudio tuno = new SharkStudio();
                    return tuno;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulo[position];
        }
    }


}
