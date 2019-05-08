package mx.itesm.shakeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class ListaCupones extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView list;
    private List<Cupon> cupones;
    private List<Location> restaurantes;
    private int pos;
    private String keyL;
    private String[] keysC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cupones);

        list = findViewById(R.id.listCupones);
        list.setOnItemClickListener(this);
        Intent i = getIntent();
        pos = i.getIntExtra("pos",0);
        new FirebaseDatabaseHelper().leerLocations(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Location> locations, List<String> keys) {
                restaurantes = locations;
                cupones = restaurantes.get(pos).getCupones();
                CustomAdapterCoupon ca = new CustomAdapterCoupon(cupones,ListaCupones.this);
                list.setAdapter(ca);

                keyL = restaurantes.get(pos).getID();
                keysC = new String[cupones.size()];
                for(int i = 0; i < cupones.size(); i++){
                    keysC[i] = cupones.get(i).getId();
                }
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this,InformacionCupon.class);
        i.putExtra("id",keyL);
        i.putExtra("idC",keysC[position]);
        startActivity(i);
    }
}
