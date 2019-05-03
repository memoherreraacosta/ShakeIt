package mx.itesm.shakeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CuponListItem extends AppCompatActivity {

    private TextView promo, nombre, lat, lng;
    private Button b;
    private RecyclerView mRecyclerView;

    private String prom, name, latt, lngg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupon_list_item2);

        promo = findViewById(R.id.tPromACLI);
        nombre = findViewById(R.id.tRestACLI);
        lat = findViewById(R.id.tLatACLI);
        lng = findViewById(R.id.tLonACLI);
        b = findViewById(R.id.bMapACLI);

        mRecyclerView = findViewById(R.id.recyclerView);

        new FirebaseDatabaseHelper().leerCupones(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Cupon> cupones, List<String> keys, List<Location> locations) {
                new RecyclerView_Config().setConfig(mRecyclerView, CuponListItem.this, cupones, keys, locations);
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
        this.name = this.nombre.getText()+"";
        this.prom = this.promo.getText()+"";
        this.latt = this.lat.getText()+"";
        this.lngg = this.lng.getText()+"";

    }

    public void changeMap(View v){
        Intent i = new Intent(this, MapsActivity.class);

        i.putExtra("latitud", "20.7346349");
        i.putExtra("longitud", "-103.4555114");
        i.putExtra("restaurante", "Starbucks Tec");
        /*
        i.putExtra("latitud", this.latt);
        i.putExtra("longitud", this.lngg);
        i.putExtra("nombre", this.name);
        */
        startActivity(i);
    }
}
