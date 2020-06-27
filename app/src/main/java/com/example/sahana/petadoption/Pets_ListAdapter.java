package com.example.sahana.petadoption;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Pets_ListAdapter extends ArrayAdapter {


    List list = new ArrayList();

    public Pets_ListAdapter
            (Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView cust,txteventname, txteventdate, txtselectevent, txtselectcategory, txtpeople;
        Button button;
    }

    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        Pets_ListAdapter.LayoutHandler layoutHandler;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.rows, parent, false);
            layoutHandler = new Pets_ListAdapter.LayoutHandler();
            layoutHandler.cust = row.findViewById(R.id.txtnmae);
            layoutHandler.txteventname = row.findViewById(R.id.txtdes);
            layoutHandler.txteventdate = row.findViewById(R.id.txtbreed);
            layoutHandler.txtselectevent = row.findViewById(R.id.txtgender);
            layoutHandler.txtselectcategory = row.findViewById(R.id.txtphone);
            layoutHandler.txtpeople = row.findViewById(R.id.txtweight);




            row.setTag(layoutHandler);
        } else {
            layoutHandler = (Pets_ListAdapter.LayoutHandler) row.getTag();


        }

        Dataprovider_Pets DPC = (Dataprovider_Pets) this.getItem(position);
        layoutHandler.cust.setText(DPC.getName());
        layoutHandler.txteventname.setText(DPC.getDes());
        layoutHandler.txteventdate.setText(DPC.getBreed());
        layoutHandler.txtselectevent.setText(DPC.getGender());
        layoutHandler.txtselectcategory.setText(DPC.getPhone());
        layoutHandler.txtpeople.setText(DPC.getWeight());


        return row;
    }

}













































