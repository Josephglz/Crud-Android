package com.example.practica4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

public class MyCustomAdapter extends BaseAdapter implements Filterable {

    ArrayList<Platillo> platillos;
    ArrayList<Platillo> fPlatillos;
    Context context;
    ValueFilter valueFilter;

    MyCustomAdapter(ArrayList<Platillo> platillos, Context context)
    {
        this.platillos = platillos;
        this.fPlatillos = platillos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return platillos.size();
    }

    @Override
    public Object getItem(int i) {
        return platillos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        getFilter();
        View v = inflater.inflate(R.layout.custom_listview, viewGroup, false);
        TextView txtname = v.findViewById(R.id.txt_lvName);
        TextView txtdesc = v.findViewById(R.id.txt_lvDescripcion);
        TextView txtprice = v.findViewById(R.id.txt_lvPrice);
        ImageView ivFoto = v.findViewById(R.id.iv_lvFoto);

        txtname.setText(platillos.get(i).getNombre());
        txtdesc.setText(platillos.get(i).getDescripcion());
        txtprice.setText(String.valueOf(platillos.get(i).getPrecio()));
        new AsyncTaskLoadImage(ivFoto).execute(platillos.get(i).getFoto());
        return v;
    }

    @Override
    public Filter getFilter() {
        if(valueFilter == null)
        {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            FilterResults results = new FilterResults();
            if(charSequence != null && charSequence.length() > 0)
            {
                ArrayList<Platillo> filterList = new ArrayList<>();
                for(int i = 0; i < platillos.size(); i++)
                {
                    if((platillos.get(i).getNombre().toUpperCase())
                        .contains(charSequence.toString().toUpperCase()))
                    {
                        filterList.add(platillos.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            else
            {
                results.count = fPlatillos.size();
                results.values = fPlatillos;
            }
            return results;
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            platillos = (ArrayList<Platillo>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}

