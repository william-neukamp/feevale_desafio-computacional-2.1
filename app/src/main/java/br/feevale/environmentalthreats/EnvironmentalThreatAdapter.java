package br.feevale.environmentalthreats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EnvironmentalThreatAdapter extends BaseAdapter {
    LayoutInflater inflater;
    EnvironmentalThreatSQLiteDatabase db;

    public EnvironmentalThreatAdapter(){
    }

    public EnvironmentalThreatAdapter(Context context, EnvironmentalThreatSQLiteDatabase db){
        this.inflater = LayoutInflater.from(context);
        this.db = db;
    }

    @Override
    public int getCount() {
        return this.db.selectThreats().size();
    }

    @Override
    public Object getItem(int position) {
        return this.db.selectThreats().get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.db.selectThreats().get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.threat_list_item, null);

        TextView txtDesc = view.findViewById(R.id.txtDescData);
        TextView txtDate = view.findViewById(R.id.txtDateData);

        txtDesc.setText(db.selectThreats().get(position).getDescription());
        txtDate.setText(db.selectThreats().get(position).getDate());

        return view;
    }
}
