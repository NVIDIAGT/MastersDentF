package com.example.mastersdentf;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mastersdentf.helpers.QueueUtils;
import com.example.mastersdentf.models.Servicios;

public class ServicoDetailsFragment extends Fragment {
    QueueUtils.QueueObject queue = null;
    ImageLoader queueImage=null;
    NetworkImageView imgFoto;
    TextView x,y,z;
    private ServicoDetailsViewModel mViewModel;
    public Servicios serviciosObject;
    public static ServicoDetailsFragment newInstance() {
        return new ServicoDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.servico_details_fragment, container, false);

        queue = QueueUtils.getInstance(this.getContext());
        queueImage=queue.getImageLoader();
        Servicios.injectServicioFromCloud(queue,serviciosObject,this);
        x=root.findViewById(R.id.txtNombre);
        y=root.findViewById(R.id.txtprice);
        z=root.findViewById(R.id.txtdescription);
        imgFoto=root.findViewById(R.id.imgFoto);
        return root;
    }
    public void refresh(){
        x.setText(serviciosObject.name);
        y.setText(serviciosObject.price);
        z.setText(serviciosObject.description);
        imgFoto.setImageUrl(serviciosObject.url_image,queueImage);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ServicoDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}