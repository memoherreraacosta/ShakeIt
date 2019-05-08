package mx.itesm.shakeit;

/**
 * Created by CLN-BRA on 26/02/2019.
 */

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CLN-BRA on 18/02/2019.
 */

public class CuponesAdapter extends BaseAdapter {

    private ArrayList<Cupon> source;
    private Activity activity;

    public CuponesAdapter (ArrayList<Cupon>source, Activity activity){
        this.source = source;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public Object getItem(int position) {
        return source.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //view - the actual specific row
        if(convertView == null){
            //inflate - from xml to java object
            convertView = activity.getLayoutInflater().inflate(R.layout.layout, null);
        }

        TextView tvInformacion = convertView.findViewById(R.id.tvInformacion);

        Cupon ElCupon = source.get(position);

        return convertView;
    }
}

