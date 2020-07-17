package com.example.mastersdentf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mastersdentf.R;
import com.example.mastersdentf.models.Servicios;

public class ServicioAdaptador extends ArrayAdapter<Servicios> {
    Context context;
    ImageLoader queue;
    private class ViewHolder {
        TextView first_name;
        TextView price;
        TextView description;
        NetworkImageView image;
        private ViewHolder() {
        }
    }
    public ServicioAdaptador(Context context, List<Servicios> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue=_queue;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Servicios rowItem = (Servicios) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_servicios, null);
            holder = new ViewHolder();
            holder.first_name = (TextView) convertView.findViewById(R.id.name);
            holder.image=(NetworkImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.first_name.setText(rowItem.name);
        holder.image.setImageUrl(rowItem.url_image,queue);
        return convertView;
    }

}
