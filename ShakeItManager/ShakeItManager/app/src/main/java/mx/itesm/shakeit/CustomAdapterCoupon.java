package mx.itesm.shakeit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterCoupon extends BaseAdapter {

    private List<Cupon> cupones;
    private Activity activity;

    public CustomAdapterCoupon(List<Cupon> cupones, Activity activity) {
        this.cupones = cupones;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return cupones.size();
    }

    @Override
    public Object getItem(int position) {
        return cupones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row,null);
        }
        TextView info = convertView.findViewById(R.id.info);

        Cupon cupon = cupones.get(position);
        info.setText(cupon.getInformacion());
        return convertView;
    }
}
