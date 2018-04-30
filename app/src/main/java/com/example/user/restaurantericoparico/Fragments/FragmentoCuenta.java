package com.example.user.restaurantericoparico.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.restaurantericoparico.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentoCuenta extends Fragment {
    private static final String TAG = "FragmentoCuenta";
    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    public FragmentoCuenta() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_cuenta, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);
            //Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));
            pestanas.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(pestanas);
    }

    /*private void poblarViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FragmentoPerfil(), getString(R.string.titulo_tab_perfil));
        adapter.addFragment(new FragmentoDirecciones(), getString(R.string.titulo_tab_direcciones));
        adapter.addFragment(new FragmentoTarjetas(), getString(R.string.titulo_tab_tarjetas));
        viewPager.setAdapter(adapter);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(appBar!=null){
            appBar.removeView(pestanas);
        }

    }

    private class sliderAdapter extends FragmentPagerAdapter {

        final  String tabs[]={getString(R.string.titulo_tab_perfil), getString(R.string.titulo_tab_direcciones), getString(R.string.titulo_tab_tarjetas)};

        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    FragmentoPerfil fragmentoPerfil = new FragmentoPerfil();
                    return fragmentoPerfil;

                case 1:
                    FragmentoDirecciones fragmentoDirecciones = new FragmentoDirecciones();
                    return fragmentoDirecciones;

                case 2:
                    FragmentoTarjetas fragmentoTarjetas = new FragmentoTarjetas();
                    return fragmentoTarjetas;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

}
