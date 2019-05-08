package mx.itesm.shakeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ListaLocations extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView list;
    private List<Location> restaurantes;
    private String[] keysL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locations_add);

        list = findViewById(R.id.listaRes);
        list.setOnItemClickListener(this);

        new FirebaseDatabaseHelper().leerLocations(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Location> locations, List<String> keys) {
                restaurantes = locations;
                CustomAdapterLocation ca = new CustomAdapterLocation(restaurantes, ListaLocations.this);
                list.setAdapter(ca);

                keysL = new String[keys.size()];
                for(int i = 0; i < keys.size(); i++){
                    keysL[i] = keys.get(i);
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
        Intent i = new Intent(this,Editar.class);
        i.putExtra("pos",position);
        i.putExtra("id",keysL[position]);
        startActivity(i);

    }
}
