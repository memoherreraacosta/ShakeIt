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

public class CustomAdapterLocation extends BaseAdapter {
    private List<Location> locationList;
    private Activity activity;

    public CustomAdapterLocation(List<Location> locationList, Activity activity) {
        this.locationList = locationList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int position) {
        return locationList.get(position);
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

        Location location = locationList.get(position);
        info.setText(location.getRestaurante());
        return convertView;
    }
}
