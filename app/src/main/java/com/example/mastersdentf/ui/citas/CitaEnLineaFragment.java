package com.example.mastersdentf.ui.citas;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mastersdentf.R;

public class CitaEnLineaFragment extends Fragment {

    private CitaEnLineaViewModel mViewModel;

    public static CitaEnLineaFragment newInstance() {
        return new CitaEnLineaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cita_en_linea_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CitaEnLineaViewModel.class);
        // TODO: Use the ViewModel
    }

}