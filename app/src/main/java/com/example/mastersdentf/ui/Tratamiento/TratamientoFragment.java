package com.example.mastersdentf.ui.Tratamiento;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;
import com.example.mastersdentf.R;
import com.example.mastersdentf.ServicoDetailsFragment;
import com.example.mastersdentf.adapters.ServicioAdaptador;
import com.example.mastersdentf.helpers.QueueUtils;
import com.example.mastersdentf.models.Servicios;

import java.util.ArrayList;

public class TratamientoFragment extends Fragment {
    ListView serviciosList;
    ServicioAdaptador servicioAdaptador;
    QueueUtils.QueueObject queue = null;
    ArrayList<Servicios> items;
    ImageLoader queueImage=null;
    private TratamientoViewModel mViewModel;

    public static TratamientoFragment newInstance() {
        return new TratamientoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.tratamiento_fragment, container, false);
        queue=QueueUtils.getInstance(this.getContext());
        queueImage=queue.getImageLoader();
        items=new ArrayList<Servicios>();
        Servicios.injectServicosFromCloud(queue,items,this);
        servicioAdaptador=new ServicioAdaptador(this.getContext(),items,queueImage);
        serviciosList=root.findViewById(R.id.lista);
        serviciosList.setAdapter(servicioAdaptador);
        serviciosList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                ServicoDetailsFragment o=new ServicoDetailsFragment();
                o.serviciosObject=items.get(position);
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,o).addToBackStack(null).commit();
            }
        });
        return root;
    }
    public void refreshList(){
        if (this.servicioAdaptador!=null){
            this.servicioAdaptador.notifyDataSetChanged();
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TratamientoViewModel.class);
        // TODO: Use the ViewModel
    }

}